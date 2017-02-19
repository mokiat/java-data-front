/*
 * Copyright (C) mokiat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mokiat.data.front.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an OBJ 3D model.
 * <p>
 * This class represents a single OBJ resource.
 * 
 * @author Momchil Atanasov
 * 
 */
public class OBJModel {

    private final List<OBJVertex> vertices = new ArrayList<OBJVertex>();
    private final List<OBJTexCoord> texCoords = new ArrayList<OBJTexCoord>();
    private final List<OBJNormal> normals = new ArrayList<OBJNormal>();
    private final List<OBJObject> objects = new ArrayList<OBJObject>();
    private final List<String> materialLibraries = new ArrayList<String>();

    /**
     * Creates a new empty {@link OBJModel}.
     */
    public OBJModel() {
        super();
    }

    /**
     * Returns a list of vertices.
     * @return non-null writable list of {@link OBJVertex} 
     * instances.
     */
    public List<OBJVertex> getVertices() {
        return vertices;
    }

    /**
     * A helper method that returns the vertex for the
     * specified {@link OBJDataReference}.
     * @param reference the data reference
     * @return the referred vertex as an {@link OBJVertex} instance.
     * @throws ArrayIndexOutOfBoundsException if the vertex index in the
     * reference is invalid
     */
    public OBJVertex getVertex(OBJDataReference reference) {
        return vertices.get(reference.vertexIndex);
    }

    /**
     * Returns a list of texture coordinates.
     * @return non-null writable list of {@link OBJTexCoord}
     * instances.
     */
    public List<OBJTexCoord> getTexCoords() {
        return texCoords;
    }

    /**
     * A helper method that returns the texture coordinate
     * for the specified {@link OBJDataReference}.
     * @param reference the data reference
     * @return the referred texture coordinate as an {@link OBJTexCoord}
     * instance.
     * @throws ArrayIndexOutOfBoundsException if the texture coordinate 
     * index in the reference is invalid
     */
    public OBJTexCoord getTexCoord(OBJDataReference reference) {
        return texCoords.get(reference.texCoordIndex);
    }

    /**
     * Returns a list of normals.
     * @return non-null list of {@link OBJNormal} instances
     */
    public List<OBJNormal> getNormals() {
        return normals;
    }

    /**
     * A helper method that returns the normal for the
     * specified {@link OBJDataReference}.
     * @param reference the data reference
     * @return the referred normal as an {@link OBJNormal} instance
     * @throws ArrayIndexOutOfBoundsException if the normal index 
     * in the reference is invalid
     */
    public OBJNormal getNormal(OBJDataReference reference) {
        return normals.get(reference.normalIndex);
    }

    /**
     * Returns a list of OBJ objects.
     * @return non-null writable list of {@link OBJObject} 
     * instances
     */
    public List<OBJObject> getObjects() {
        return objects;
    }

    /**
     * A helper method that returns the OBJ object in this model 
     * with the specified name.
     * <p>
     * If the requested object cannot be found, 
     * then <code>null</code> is returned.
     * @param name name of the requested object
     * @return an instance of {@link OBJObject} or <code>null</code>, 
     * if it cannot be found.
     */
    public OBJObject getObject(String name) {
        for (OBJObject object : objects) {
            if (name.equals(object.getName())) {
                return object;
            }
        }
        return null;
    }

    /**
     * Returns a list of material library references.
     * @return a non-null writable list of material library 
     * references.
     */
    public List<String> getMaterialLibraries() {
        return materialLibraries;
    }
}
