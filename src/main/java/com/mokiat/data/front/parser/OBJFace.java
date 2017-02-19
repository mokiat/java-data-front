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
 * Represents a single face in an OBJ model.
 *
 * @author Momchil Atanasov
 * 
 */
public class OBJFace {

    private final List<OBJDataReference> references = new ArrayList<OBJDataReference>(4);
    
    /**
     * Creates a new default {@link OBJFace}.
     * <p>
     * By default a face has no references. In general this is an invalid face
     * as a face should have at least three references to make a triangle.
     */
    public OBJFace() {
        super();
    }

    /**
     * Returns a non-null writable list of references.
     * <p>
     * Each reference contains indices to data elements
     * (vertices, normals, etc.).
     * @return a non-null writable list of {@link OBJDataReference}
     * instances.
     */
    public List<OBJDataReference> getReferences() {
        return references;
    }
    
    /**
     * A helper methods that returns whether this face
     * has any vertex data.
     * <p>
     * This method checks the first data reference for
     * vertex index. If no references are available, then
     * this method returns <code>false</code>.
     * <p>
     * In general, you should never have to call this method
     * as each valid face should have at least vertex data.
     * @return <code>true</code> if this face has vertex data,
     * <code>false</code> otherwise.
     */
    public boolean hasVertices() {
    	if (references.isEmpty()) {
    		return false;
    	}
    	return references.get(0).hasVertexIndex();
    }
    
    /**
     * A helper method that returns whether this face
     * has any normal data.
     * <p>
     * This method checks the first data reference for
     * normal index. If no references are available, then
     * this method returns <code>false</code>.
     * @return <code>true</code> if this face has normal data,
     * <code>false</code> otherwise.
     */
    public boolean hasNormals() {
    	if (references.isEmpty()) {
    		return false;
    	}
    	return references.get(0).hasNormalIndex();
    }
    
    /**
     * A helper method that returns whether this face
     * has any texture coordinate data.
     * <p>
     * This method checks the first data reference for
     * texture coordinate index. If no references are available,
     * then this method returns <code>false</code>.
     * @return <code>true</code> if this face has texture
     * coordinate data, <code>false</code> otherwise.
     */
    public boolean hasTextureCoordinates() {
    	if (references.isEmpty()) {
    		return false;
    	}
    	return references.get(0).hasTexCoordIndex();
    }

}
