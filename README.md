java-data-front
===============

A Java library for reading and writing Wavefront 3D model resources (OBJ, MTL).

**Note: ** Though planned in the future, it is not possible to serialize OBJ or MTL resources at the moment.

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

I am not sure what is the exact history of these file formats, but I believe they were first introduced by the Wavefront Technologies company for their 3D software. Now they are available in practically all 3D modeling softwares.

## Parsing OBJs

Using this library is meant to be easy and straightforward. All you need to do is instantiate an `OBJParser` and pass it an `InputStream` to your OBJ resource.

**Example: **

```java
// Open a stream to your OBJ resource
final InputStream in = new FileInputStream("example.obj");

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
```

Let's look at the API in a bit more detail.

So parsing is straightforward. You saw that in the previous code snippet. What's important to keep in mind is that you can have any InputStream. This means that you could open your model from a Java resource or a remote HTTP resource.

**Example: **

```java
final URL url = new URL("http://www.example.org/models/example.obj");
final InputStream in = url.openStream();
final IOBJParser parser = new OBJParser();
final OBJModel model = parser.parse(in);
// You know what to do next...
```

No matter where you load the OBJ resource from, you always get the same `OBJModel` model representation.

As you've seen above, we used the `getVertices()`, `getNormals()`, and `getTexCoords()` methods. These give you access to all of the vertices, normals and texture coordinates respectively that were defined in the OBJ resource. Since these are shared between objects, that's why they are defined on the root `OBJModel` element.

Additionally, you have the `getMaterialLibraries()` method. This one provides you a list of all the material dependencies that were declared in the OBJ resource. The method returns a list of strings. You will need to use the `MTLParser` to load these resources. It is up to you to locate those resources, though. Since the parser has no idea from where the original OBJ resource is being parsed, it cannot know how to resolve relative dependencies (could be file, could be URL, etc.).

The most interesting of methods is the `getObjects()` method which lists all of the objects that are defined in the OBJ resource. These are the entities you would usually iterate through to get the mesh data.

**Example: **

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

**Example: **

```java
final OBJMesh mesh = ...; // You already know how to get this.
final String materialName = mesh.getMaterialName();
final List<OBJFace> faces = mesh.getFaces();
```

You would use the `materialName` to select the proper material to use for rendering the list of `OBJFace` instances. You will need to locate the material in one of the `OBJMaterial` resources that you have previously loaded as instructed by `getMaterialLibraries()`.

Now that you know how to get to the distinct faces and which material to use, what's left is to get their mesh data.

**Example: **

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

A face can be defined by arbitrary number of vertices, as long as they are more than three. This is why each has the `getReferences()` method that returns a list of `OBJDataReference` objects. This object represents a single vertex and allows you to locate the positional, normal and texture coordinate information for that vertex. This happens through the usage for indices that point at the master data (i.e. `getVertices()`, `getNormals()`, `getTexCoords()`). There are helper methods like `hasNormalIndex()` that help you determine if the vertex has a normal declared and `getNormal` that automatically locates the `OBJNormal` instance for you.


## Parsing MTLs
Parsing material libraries is performed in the same way as objects. All you need to do is instantiate an `MTLParser` and pass it an `InputStream` to your MTL resource.

**Example:**

```java
final InputStream in = new FileInputStream("example.mtl");
final IMTLParser parser = new MTLParser();
final MTLLibrary library = parser.parse(in);
for (MTLMaterial material : library.getMaterials()) {
	System.out.println(MessageFormat.format("Material with name ``{0}``.", material.getName()));
}
```

The `MTLMaterial` object represents a material that is defined in the MTL resource. You can have many of these defined in a single resource. Each of these has a name and some generic material data like diffuse color, ambient color, specular color, etc.

**Example: **

```java
final MTLMaterial material = ...; // You already know how to get this.
final MTLColor diffuseColor = material.getDiffuseColor();
final MTLColor ambientColor = material.getAmbientColor();
final MTLColor specularColor = material.getSpecularColor();
```

As you can see, parsing MTL resources is not that different from parsing OBJ resources. You wouldn't usually parse MTL resources on their own, though. Most often you would use the information from the OBJ resource to locate the MTL resources and load them.

**Example:**

```java
final IOBJParser objParser = new OBJParser();
final IMTLParser mtlParser = new MTLParser();

final InputStream objStream = ...; // Depends on your use case.
final OBJModel model = objParser.parse(objStream);
for (String libraryReference : model.getMaterialLibraries()) {
	final InputStream mtlStream = ...; // You will need to resolve this based on `libraryReference`
    final MTLLibrary library = mtlParser.parse(mtlStream);
    // Do something with the library. Maybe store it for later usage.
}
```

## Notes

This library was implemented to support other projects of mine. As such, it supports only a subset of the OBJ and MTL specifications (which are large). The features that are provided should be sufficient for most use cases.

Though not shown in the code snippets above, it is important that you always close any `InputStream` objects as that is not done by the API. I have omitted that on purpose to keep the snippets simple to read.

If you want to see an application that already uses this library, take a look at **[ModelViewer3D](https://play.google.com/store/apps/details?id=com.momchil_atanasov.android.modelviewer)**.

## License

The source code is provided to you under the license in `LICENSE` file.

The documents in the `documents` folder, however, are not released under this license. These documents are not my ownership and have been copy-pasted from sources on the internet. They are specifications that were used to guarantee the correctness of the API. Since it is difficult to find an official specification, these resources have been added to the repository for locking in the specification.

These documents are not a fundamental part of the source code and are not included in the final binary, so most likely they should not be an issue.
