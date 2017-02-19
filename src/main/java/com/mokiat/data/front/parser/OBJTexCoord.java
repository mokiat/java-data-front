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

import static java.lang.Float.floatToRawIntBits;

/**
 * This class represents a texture coordinate.
 *
 * @author Momchil Atanasov
 * 
 */
public class OBJTexCoord {
	
	/**
	 * Type of the texture coordinate.
	 * <p>
	 * The type determines the dimensions of this
	 * texture coordinate.
	 * 
	 * @author Momchil Atanasov
	 *
	 */
	public enum Type {TYPE_1D, TYPE_2D, TYPE_3D};

    /**
     * Specifies the U coordinate.
     */
    public float u = 0.0f;
    
    /**
     * Specifies the V coordinate.
     */
    public float v = 0.0f;
    
    /**
     * Specifies the W coordinate.
     */
    public float w = 0.0f;
    
    /**
     * Specifies the type of this texture coordinate.
     * <p>
     * The type determines the dimensions of this
     * texture coordinate.
     */
    public Type type = Type.TYPE_2D;

    
    /**
     * Creates a new default {@link OBJTexCoord}.
     * <p>
     * By default this texture coordinate is in the
     * 2D space and points at the center of the 
     * coordinate system.
     */
    public OBJTexCoord() {
        super();
    }
    
    /**
     * Creates a new {@link OBJTexCoord} with
     * the specified 1D texture coordinates.
     * @param u the U coordinate
     */
    public OBJTexCoord(float u) {
    	this.u = u;
    	this.type = Type.TYPE_1D;
    }
    
    /**
     * Creates a new {@link OBJTexCoord} with the
     * specified 2D texture coordinates.
     * @param u the U coordinate.
     * @param v the V coordinate.
     */
    public OBJTexCoord(float u, float v) {
    	this.u = u;
    	this.v = v;
    	this.type = Type.TYPE_2D;
    }

    /**
     * Creates a new {@link OBJTexCoord} with the 
     * specified 3D texture coordinates.
     *
     * @param u the U coordinate
     * @param v the V coordinate
     * @param w the W coordinate
     */
    public OBJTexCoord(float u, float v, float w) {
        this.u = u;
        this.v = v;
        this.w = w;
        this.type = Type.TYPE_3D;
    }

    /**
     * Creates a new {@link OBJTexCoord}, a copy of the specified texture
     * coordinate.
     *
     * @param other texture coordinate to be copied.
     */
    public OBJTexCoord(OBJTexCoord other) {
        this.u = other.u;
        this.v = other.v;
        this.w = other.w;
        this.type = other.type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OBJTexCoord)) {
            return false;
        }
        final OBJTexCoord other = (OBJTexCoord) obj;
        return (floatToRawIntBits(u) == floatToRawIntBits(other.u))
                && (floatToRawIntBits(v) == floatToRawIntBits(other.v))
                && (floatToRawIntBits(w) == floatToRawIntBits(other.w))
                && (type == other.type);
    }

    @Override
    public int hashCode() {
        int result = floatToRawIntBits(u);
        result = result * 31 + floatToRawIntBits(v);
        result = result * 31 + floatToRawIntBits(w);
        result = result * 31 + type.hashCode();
        return result;
    }
}
