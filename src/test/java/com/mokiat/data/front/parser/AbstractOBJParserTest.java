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

import static org.junit.Assert.assertEquals;

import com.mokiat.data.front.parser.IOBJParser;
import com.mokiat.data.front.parser.OBJDataReference;
import com.mokiat.data.front.parser.OBJModel;
import com.mokiat.data.front.parser.OBJNormal;
import com.mokiat.data.front.parser.OBJParser;
import com.mokiat.data.front.parser.OBJTexCoord;
import com.mokiat.data.front.parser.OBJVertex;
import com.mokiat.data.front.parser.OBJTexCoord.Type;
import com.mokiat.data.front.test.WFResourceFixture;

public abstract class AbstractOBJParserTest {
	
	private static final float FLOAT_MARGIN = 0.00001f;
	
	protected final WFResourceFixture fixture = new WFResourceFixture();
	protected final IOBJParser parser = new OBJParser();
	protected OBJModel model;

	protected void assertVertex(OBJVertex vertex, float x, float y, float z) {
		assertEquals(x, vertex.x, FLOAT_MARGIN);
		assertEquals(y, vertex.y, FLOAT_MARGIN);
		assertEquals(z, vertex.z, FLOAT_MARGIN);
	}

	protected void assertNormal(OBJNormal normal, float x, float y, float z) {
		assertEquals(x, normal.x, FLOAT_MARGIN);
		assertEquals(y, normal.y, FLOAT_MARGIN);
		assertEquals(z, normal.z, FLOAT_MARGIN);
	}

	protected void assertTexCoord(OBJTexCoord texCoord, Float u, Float v, Float w) {
		assertEquals(u, texCoord.u, FLOAT_MARGIN);
		if (v != null) {
			assertEquals(v, texCoord.v, FLOAT_MARGIN);
		} else {
			assertEquals(Type.TYPE_1D, texCoord.type);
			return;
		}
		if (w != null) {
			assertEquals(w, texCoord.w, FLOAT_MARGIN);
			assertEquals(Type.TYPE_3D, texCoord.type);
		} else {
			assertEquals(Type.TYPE_2D, texCoord.type);
		}
	}

	protected void assertReference(OBJDataReference reference, int vertexIndex, int texCoordIndex, int normalIndex) {
		assertEquals(vertexIndex, reference.vertexIndex);
		assertEquals(texCoordIndex, reference.texCoordIndex);
		assertEquals(normalIndex, reference.normalIndex);
	}

}
