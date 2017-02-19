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

import org.junit.Before;
import org.junit.Test;

import com.mokiat.data.front.stub.OBJContentScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class OBJScannerBasicTest {
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final OBJContentScannerHandler handler = new OBJContentScannerHandler();
	private int index = 0;
	
	@Before
	public void setUp() throws Exception {
		fixture.scanOBJ("valid_basic.obj", handler);
	}
	
	@Test
	public void testComment() {
		handler.assertCommentCount(1);
		handler.assertComment(index++, "This is the beginning of this OBJ file.");
	}
	
	@Test
	public void testVertices() {
		handler.assertVertexCount(4);
		handler.assertVertex(index++, -1.0f, 1.0f, -1.0f, 1.0f);
		handler.assertVertex(index++, -1.0f, -1.0f, 1.0f, 0.9f);
		handler.assertVertex(index++, 1.0f, -1.0f, -1.0f, 0.8f);
		handler.assertVertex(index++, 1.0f, 1.0f, 1.0f, 0.7f);
	}
	
	@Test
	public void testTexCoords() {
		handler.assertTexCoordCount(4);
		handler.assertTexCoord(index++, 0.0f, 0.0f, 0.0f);
		handler.assertTexCoord(index++, 1.0f, 1.0f, 0.5f);
		handler.assertTexCoord(index++, 1.0f, 0.0f, 1.0f);
		handler.assertTexCoord(index++, 0.0f, 1.0f, 0.3f);
	}
	
	@Test
	public void testNormals() {
		handler.assertNormalCount(3);
		handler.assertNormal(index++, 0.0f, 1.0f, 0.0f);
		handler.assertNormal(index++, 1.0f, 0.0f, 0.0f);
		handler.assertNormal(index++, 0.0f, 0.0f, 1.0f);
	}
	
	@Test
	public void testObjects() {
		handler.assertObjectCount(1);
		handler.assertObject(index++, "MyObject");
	}
	
	@Test
	public void testFaces() {
		handler.assertFaceBeginCount(1);
		handler.assertFaceEndCount(1);
	}
	
	@Test
	public void testDataReferences() {
		handler.assertDataReferencesCount(3);
		handler.assertDataReference(index++, 1, 4, 1);
		handler.assertDataReference(index++, 2, 1, 1);
		handler.assertDataReference(index++, 3, 3, 2);
	}
	
	@Test
	public void testMaterialLibraries() {
		handler.assertMaterialLibraryCount(1);
		handler.assertMaterialLibrary(index++, "valid_basic.mtl");
	}
	
	@Test
	public void testMaterialReferences() {
		handler.assertMaterialReferenceCount(1);
		handler.assertMaterialReference(index++, "BlueMaterial");
	}

}
