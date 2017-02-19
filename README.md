java-data-front
===============

[![Build Status](https://travis-ci.org/mokiat/java-data-front.svg?branch=master)](https://travis-ci.org/mokiat/java-data-front)

A Java library for reading Wavefront 3D model resources (OBJ, MTL).

The OBJ and MTL file formats are one of the most popular 3D model formats used at the moment. This is mainly due to their simplicity.

OBJ files are used to describe the coordinates, connections and shapes that make up a 3D model.

```
v -1.0 1.0 0.0
v -1.0 -1.0 0.0
v 1.0 -1.0 0.0
v 1.0 1.0 0.0

o Rectangle
f 1 2 3
f 1 3 4
```

MTL files are optional and are present when a 3D model uses materials.

```
newmtl TestMaterial
Ka 1.0 0.5 0.1
Kd 0.5 0.7 0.3
Ks 0.2 0.4 0.8
Ns 650
d 0.7
map_Kd vehicle.png
```

The `obj` and `mtl` file formats were originally developed by the Wavefront Technologies company for their 3D visualizer software. Currently, they are available in practically all 3D modeling solutions.

## Loading OBJ resources

Using this library is meant to be easy and straightforward. All you need to do is instantiate an `OBJParser` and pass it an `InputStream` to your OBJ resource.

**Example:**

```java
// Open a stream to your OBJ resource
try (InputStream in = new FileInputStream("example.obj")) {
  // Create an OBJParser and parse the resource
  final IOBJParser parser = new OBJParser();
  final OBJModel model = parser.parse(in);

  // Use the model representation to get some basic info
  System.out.println(MessageFormat.format(
          "OBJ model has {0} vertices, {1} normals, {2} texture coordinates, and {3} objects.",
          model.getVertices().size(),
          model.getNormals().size(),
          model.getTexCoords().size(),
          model.getObjects().size()));  
}
```

When you parse an OBJ resource, you get a `OBJModel` representation.
We use the `getVertices`, `getNormals`, and `getTexCoords` methods to get access to all of the vertices, normals and texture coordinates respectively that are defined in the OBJ resource. Since these can be shared between multiple objects, their getter methods are defined on the root `OBJModel` element.

Additionally, you have the `getMaterialLibraries` method. It provides a list of all the material dependencies that were declared in the OBJ resource. The method returns a list of strings, representing resources that can be parsed via a `MTLParser` parser. It is up to your implementation to locate those resources and process them, as the `OBJParser` has no way of knowing from where the OBJ resource originates.

The `getObjects` method lists all of the objects that are defined in the OBJ resource. These are the entities you would usually iterate through to get the mesh data.

**Example:**

```java
for (OBJObject object : model.getObjects()) {
    for (OBJMesh mesh : object.getMeshes()) {
        for (OBJFace face : mesh.getFaces()) {
        	// You have reached a face.
        }
    }
}
```

One thing that does not exactly match the OBJ specification is the `OBJMesh` concept. This object is used to encapsulate any material dependencies of the object and corresponding mesh. To be more precise, it is possible that a single `OBJObject` has triangles with different materials. The `OBJMesh` is used to group these different material dependencies.

**Example:**

```java
final OBJMesh mesh = ...;
final String materialName = mesh.getMaterialName();
final List<OBJFace> faces = mesh.getFaces();
```

One would use the `materialName` value to select the proper material to use for rendering the list of `OBJFace` instances. The actual material would need to have been parsed in advance (as explained above) and probably stored in a map structure for easy access.

Knowing how to get to all faces and related materials, one needs a way to get the mesh data of each individual face.

**Example:**

```java
final OBJFace face = ...; // You already know how to get this.
for (OBJDataReference reference : face.getReferences()) {
    final OBJVertex vertex = model.getVertex(reference);
    System.out.println(MessageFormat.format(
    	"Vertex ({0}, {1}, {2})", vertex.x, vertex.y, vertex.z));
    if (reference.hasNormalIndex()) {
        final OBJNormal normal = model.getNormal(reference);
        System.out.println(MessageFormat.format(
        	"Normal ({0}, {1}, {2})", normal.x, normal.y, normal.z));
    }
    if (reference.hasTexCoordIndex()) {
    	final OBJTexCoord texCoord = model.getTexCoord(reference);
        System.out.println(MessageFormat.format(
        	"TexCoord ({0}, {1})", texCoord.u, texCoord.v));
    }
}
```

A face can be defined by arbitrary number of vertices, as long as they are more than three. This is why each face has the `getReferences` method that returns a list of `OBJDataReference` objects. This object represents a single vertex and allows you to locate the positional, normal and texture coordinate information for that vertex. This happens through the usage for indices that point at the master data (the one available through `getVertices`, `getNormals`, `getTexCoords`). There are helper methods like `hasNormalIndex` that help you determine if the vertex has a normal declared and `getNormal` that automatically locates the `OBJNormal` instance for you.


## Loading MTL resources

Parsing material libraries is performed in the same way as objects. All one needs to do is instantiate an `MTLParser` and pass it an `InputStream` to the MTL resource.

**Example:**

```java
try (InputStream in = new FileInputStream("example.mtl")) {
  final IMTLParser parser = new MTLParser();
  final MTLLibrary library = parser.parse(in);
  for (MTLMaterial material : library.getMaterials()) {
  	System.out.println(MessageFormat.format("Material with name `{0}`.", material.getName()));
  }  
}
```

The `MTLMaterial` object represents a material that is defined in the MTL resource. There can be a number of these defined in a single MTL resource. Each of these has a name and some generic material data like diffuse color, ambient color, specular color, etc.

**Example:**

```java
final MTLMaterial material = ...;
final MTLColor diffuseColor = material.getDiffuseColor();
final MTLColor ambientColor = material.getAmbientColor();
final MTLColor specularColor = material.getSpecularColor();
```

One would rarely parse MTL files separately. Often, this would be as part of the parsing of an OBJ file.

**Example:**

```java
final IOBJParser objParser = new OBJParser();
final IMTLParser mtlParser = new MTLParser();

final InputStream objStream = ...; // Depends on your use case.
final OBJModel model = objParser.parse(objStream);
for (String libraryReference : model.getMaterialLibraries()) {
	final InputStream mtlStream = ...; // You will need to resolve this based on `libraryReference` and the storage used
    final MTLLibrary library = mtlParser.parse(mtlStream);
    // Do something with the library. Maybe store it in a map for later usage.
}
```

## Setting Up

Even though this project relies on Maven for packaging, it has not been published to the central Maven repository. Following are a number of approaches to get the library imported in your project.

### JitPack

An amazing web page that allows one to import Maven projects directly from GitHub. It is ideal for publishing new and small projects like this one.
One only needs to add the following configuration in their `pom.xml` file to get the library included.

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependencies>
	<dependency>
		<groupId>com.github.mokiat</groupId>
		<artifactId>java-data-front</artifactId>
		<version>v2.0.0</version>
	</dependency>
</dependencies>
```

JitPack works with other packaging frameworks as well. Check the [official webpage](https://jitpack.io/) for more information.

### Packaging

If `JitPack` is not an option for your use case, then you could package the `jar` files into your project. They are available for download from the [Releases](https://github.com/mokiat/java-data-front/releases) section of the repository.


### Local Maven repository

You can use a set of commands to import the `jar` files into your local Maven repository. Following are two available approaches. (I find the first one to do the job)

* [http://maven.apache.org/plugins/maven-install-plugin/examples/custom-pom-installation.html](http://maven.apache.org/plugins/maven-install-plugin/examples/custom-pom-installation.html)
* [http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html](http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html)

## License

The source code is provided to you under the license in `LICENSE` file.

The documents in the `documents` folder, however, are not released under this license. These documents are not my ownership and have been copy-pasted from sources on the internet. They are specifications that were used to guarantee the correctness of the API. Since it is difficult to find an official specification, these resources have been added to the repository for locking in the specification.

These documents are not a fundamental part of the source code and are not included in the final binary, so most likely they should not be an issue.
