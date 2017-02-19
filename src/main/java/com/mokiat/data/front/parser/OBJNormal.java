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
 * This class represents a normal.
 *
 * @author Momchil Atanasov
 * 
 */
public class OBJNormal {

    /**
     * Specifies the X coordinate.
     */
    public float x = 0.0f;
    
    /**
     * Specifies the Y coordinate.
     */
    public float y = 0.0f;
    
    /**
     * Specifies the Z coordinate.
     */
    public float z = 1.0f;

    /**
     * Creates a new default {@link OBJNormal}.
     * <p>
     * By default this normal has zero length
     * and points at the center of the
     * coordinate system.
     */
    public OBJNormal() {
        super();
    }

    /**
     * Creates a new {@link OBJNormal} with the 
     * specified coordinates.
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param z the Z coordinate
     */
    public OBJNormal(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * A copy-constructor that creates a new {@link OBJNormal}
     * that is a copy of an existing {@link OBJNormal}.
     * @param other normal to be copied
     */
    public OBJNormal(OBJNormal other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OBJNormal)) {
            return false;
        }
        final OBJNormal other = (OBJNormal) obj;
        return (floatToRawIntBits(x) == floatToRawIntBits(other.x))
                && (floatToRawIntBits(y) == floatToRawIntBits(other.y))
                && (floatToRawIntBits(z) == floatToRawIntBits(other.z));
    }

    @Override
    public int hashCode() {
        int result = floatToRawIntBits(x);
        result = result * 31 + floatToRawIntBits(y);
        result = result * 31 + floatToRawIntBits(z);
        return result;
    }
}
