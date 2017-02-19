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
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mokiat.data.front.parser.OBJDataReference;
import com.mokiat.data.front.parser.OBJFace;
import com.mokiat.data.front.parser.OBJMesh;
import com.mokiat.data.front.parser.OBJNormal;
import com.mokiat.data.front.parser.OBJObject;
import com.mokiat.data.front.parser.OBJTexCoord;
import com.mokiat.data.front.parser.OBJVertex;

public class OBJParserBasicTest extends AbstractOBJParserTest {
	
	@Before
	public void setUp() throws Exception {
		model = fixture.parseOBJ("valid_basic.obj", parser);
	}
	
	@Test
	public void testMaterialLibraries() {
		final List<String> libraries = model.getMaterialLibraries();
		assertEquals("Wrong library count.", 1, libraries.size());
		assertEquals("Wrong library filename.", "materials.mtl", libraries.get(0));
	}
	
	@Test
	public void testVertices() {
		final List<OBJVertex> vertices = model.getVertices();
		assertEquals("Wrong vertex count.", 4, vertices.size());
		final Iterator<OBJVertex> iterator = vertices.iterator();
		assertVertex(iterator.next(), 0.1f, 0.2f, 0.3f);
		assertVertex(iterator.next(), 0.4f, 0.5f, 0.6f);
		assertVertex(iterator.next(), 0.7f, 0.8f, 0.9f);
		assertVertex(iterator.next(), 1.0f, 0.9f, 0.8f);
	}
	
	@Test
	public void testNormals() {
		final List<OBJNormal> normals = model.getNormals();
		assertEquals("Wrong normal count.", 3, normals.size());
		final Iterator<OBJNormal> iterator = normals.iterator();
		assertNormal(iterator.next(), 0.0f, 1.0f, 0.0f);
		assertNormal(iterator.next(), 1.0f, 0.0f, 0.0f);
		assertNormal(iterator.next(), 0.0f, 0.0f, 1.0f);
	}
	
	@Test
	public void testTexCoords() {
		final List<OBJTexCoord> texCoords = model.getTexCoords();
		assertEquals("Wrong texture coordinate count.", 4, texCoords.size());
		final Iterator<OBJTexCoord> iterator = texCoords.iterator();
		assertTexCoord(iterator.next(), 0.1f, 0.2f, 0.3f);
		assertTexCoord(iterator.next(), 0.4f, 0.5f, 0.6f);
		assertTexCoord(iterator.next(), 0.7f, 0.8f, 0.9f);
		assertTexCoord(iterator.next(), 1.0f, 0.9f, 0.8f);
	}
	
	@Test
	public void testObjects() {
		final List<OBJObject> objects = model.getObjects();
		assertEquals("Wrong object count.", 1, objects.size());
		final OBJObject object = objects.get(0);
		assertNotNull("Object should not be null.", object);
		assertEquals("Wrong object name.", "MyObject", object.getName());
	}
	
	@Test
	public void testMeshes() {
		final OBJObject firstObject = model.getObjects().get(0);
		final List<OBJMesh> meshes = firstObject.getMeshes();
		assertEquals("There should be one mesh.", 1, meshes.size());
		final OBJMesh mesh = firstObject.getMeshes().get(0);
		assertNotNull("Mesh should not be null.", mesh);
		assertEquals("Wrong mesh material name.", "BlueMaterial", mesh.getMaterialName());
	}
	
	@Test
	public void testFaces() {
		final OBJObject firstObject = model.getObjects().get(0);
		final OBJMesh mesh = firstObject.getMeshes().get(0);
		final List<OBJFace> faces = mesh.getFaces();
		assertEquals("There should be 2 faces.", 2, faces.size());
	}
	
	@Test
	public void testDataReferences() {
		final OBJObject firstObject = model.getObjects().get(0);
		final OBJMesh mesh = firstObject.getMeshes().get(0);
		final OBJFace firstFace = mesh.getFaces().get(0);
		final List<OBJDataReference> references = firstFace.getReferences();
		assertEquals("There should be 3 references.", 3, references.size());
		assertReference(references.get(0), 0, 3, 0);
		assertReference(references.get(1), 1, 0, 0);
		assertReference(references.get(2), 2, 2, 1);
	}

	
}
