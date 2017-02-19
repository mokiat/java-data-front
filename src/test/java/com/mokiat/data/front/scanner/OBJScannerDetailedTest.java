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

import org.junit.Test;

import com.mokiat.data.front.stub.OBJContentScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class OBJScannerDetailedTest {
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final OBJContentScannerHandler handler = new OBJContentScannerHandler();
	private int index = 0;
	
	@Test
	public void testComments() throws Exception {
		fixture.scanOBJ("valid_comments.obj", handler);
		handler.assertCommentCount(7);
		handler.assertComment(index++, "Comment at file start");
		handler.assertComment(index++, "Comment that is right next to special char");
		handler.assertComment(index++, "This comment uses");
		handler.assertComment(index++, "two lines");
		handler.assertComment(index++, "");
		handler.assertComment(index++, "Previous comment was empty. This one contain the # character twice.");
		handler.assertComment(index++, "Comment at file end");
	}
	
	@Test
	public void testVertices() throws Exception {
		fixture.scanOBJ("valid_vertices.obj", handler);
		handler.assertVertexCount(2);
		handler.assertVertex(index++, 1.0f, 1.0f, -1.0f, null);
		handler.assertVertex(index++, -1.0f, -1.0f, 1.0f, 0.5f);
	}
	
	@Test
	public void testTexCoords() throws Exception {
		fixture.scanOBJ("valid_texcoords.obj", handler);
		handler.assertTexCoordCount(3);
		handler.assertTexCoord(index++, 1.6f, null, null);
		handler.assertTexCoord(index++, 0.0f, -0.5f, null);
		handler.assertTexCoord(index++, -0.2f, 1.4f, 3.0f);
	}
	
	@Test
	public void testNormals() throws Exception {
		fixture.scanOBJ("valid_normals.obj", handler);
		handler.assertNormalCount(2);
		handler.assertNormal(index++, 1.0f, 0.0f, 0.0f);
		handler.assertNormal(index++, -1.0f, -1.0f, 1.0f);
	}
	
	@Test
	public void testObjects() throws Exception {
		fixture.scanOBJ("valid_objects.obj", handler);
		handler.assertObjectCount(2);
		handler.assertObject(index++, "FirstObject");
		handler.assertObject(index++, "LastObject");
	}
	
	@Test
	public void testFaces() throws Exception {
		fixture.scanOBJ("valid_faces.obj", handler);
		handler.assertFaceBeginCount(3);
		handler.assertFaceEndCount(3);
	}
	
	@Test
	public void testDataReferences() throws Exception {
		fixture.scanOBJ("valid_data_references.obj", handler);
		handler.assertDataReferencesCount(12);
		
		handler.assertDataReference(index++, 1, null, null);
		handler.assertDataReference(index++, 2, null, null);
		handler.assertDataReference(index++, 3, null, null);
		
		handler.assertDataReference(index++, 2, 1, null);
		handler.assertDataReference(index++, 3, 2, null);
		handler.assertDataReference(index++, 4, 3, null);
		
		handler.assertDataReference(index++, 4, null, 5);
		handler.assertDataReference(index++, 6, null, 7);
		handler.assertDataReference(index++, 8, null, 9);
		
		handler.assertDataReference(index++, 1, 2, 3);
		handler.assertDataReference(index++, 2, 3, 4);
		handler.assertDataReference(index++, 3, 4, 5);
	}
	
	@Test
	public void testMaterialLibraries() throws Exception {
		fixture.scanOBJ("valid_material_libraries.obj", handler);
		handler.assertMaterialLibraryCount(3);
		handler.assertMaterialLibrary(index++, "valid.mtl");
		handler.assertMaterialLibrary(index++, "extension01.mtl");
		handler.assertMaterialLibrary(index++, "extension02.mtl");
	}
	
	@Test
	public void testMaterialReferences() throws Exception {
		fixture.scanOBJ("valid_material_references.obj", handler);
		handler.assertMaterialReferenceCount(2);
		handler.assertMaterialReference(index++, null);
		handler.assertMaterialReference(index++, "MyMaterial");
	}
	
	@Test
	public void testLogicalLine() throws Exception {
		fixture.scanOBJ("valid_logical_line.obj", handler);
		handler.assertVertexCount(2);
		handler.assertVertex(index++, -1.0f, 1.0f, -2.0f, null);
		handler.assertVertex(index++, -1.0f, -1.0f, 1.0f, 2.0f);
		index = 0;
		handler.assertObjectCount(3);
		handler.assertObject(index++, "HelloWorld");
		handler.assertObject(index++, "NonNewLine");
		handler.assertObject(index++, "EndsWithNewLine");
	}
	
	@Test
	public void testUnknownCommand() throws Exception {
		fixture.scanOBJ("valid_unknown_command.obj", handler);
	}
	
}
