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
 * Represents a single mesh within an OBJ scene.
 * <p>
 * The OBJ specification does not include the concept of 
 * meshes, but since different faces can have different materials 
 * applied to them, this seemed like a good way to group them.
 * <p>
 * Each mesh contains a list of faces that have the same material 
 * applied to them.
 *
 * @author Momchil Atanasov
 * 
 */
public class OBJMesh {

    private final List<OBJFace> faces = new ArrayList<OBJFace>();
    private String materialName = null;

    /**
     * Creates a new empty {@link OBJMesh}.
     */
    public OBJMesh() {
        super();
    }

    /**
     * Sets the name of the material that is used
     * by this mesh.
     * @param materialName name of a material or <code>null</code>,
     * if this mesh has no material at all.
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * Returns the name of the material used by this mesh.
     * <p>
     * If the returned value is <code>null</code>, then this 
     * mesh uses no material.
     * @return the name of a referenced material or <code>null</code>,
     * if this mesh has no material
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * Returns a writable non-null list of faces that 
     * this mesh is comprised of.
     * @return non-null writable list of {@link OBJFace} instances.
     */
    public List<OBJFace> getFaces() {
        return faces;
    }
}
