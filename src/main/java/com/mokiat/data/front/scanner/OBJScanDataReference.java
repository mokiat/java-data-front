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

package com.mokiat.data.front.scanner;

import com.mokiat.data.front.common.FastInt;
import com.mokiat.data.front.common.IFastInt;
import com.mokiat.data.front.error.WFCorruptException;

/**
 * Internal class that is used to parse face data references.
 *
 * @author Momchil Atanasov
 * 
 */
class OBJScanDataReference {

	private final FastInt vertexIndex = new FastInt();
	private final FastInt texCoordIndex = new FastInt();
	private final FastInt normalIndex = new FastInt();
	private boolean hasTexCoordIndex = false;
	private boolean hasNormalIndex = false;

    public OBJScanDataReference() {
    	super();
	}

	public void parse(String segment) throws WFCorruptException {
        final String[] references = segment.split("/");
        vertexIndex.set(parseInt(references[0]));
        hasTexCoordIndex = (references.length >= 2) && !references[1].isEmpty();
        if (hasTexCoordIndex) {
        	texCoordIndex.set(parseInt(references[1]));
        }
        hasNormalIndex = (references.length >= 3) && !references[2].isEmpty();
        if (hasNormalIndex) {
        	normalIndex.set(parseInt(references[2]));
        }
	}
	
	public IFastInt getVertexIndex() {
		return vertexIndex;
	}
	
	public IFastInt getTexCoordIndex() {
		return hasTexCoordIndex ? texCoordIndex : null;
	}
	
	public IFastInt getNormalIndex() {
		return hasNormalIndex ? normalIndex : null;
	}

    private static int parseInt(String text) throws WFCorruptException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new WFCorruptException("Could not parse int value.", ex);
        }
    }

}
