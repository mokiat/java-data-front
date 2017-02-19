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
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.mokiat.data.front.parser.IOBJParser;
import com.mokiat.data.front.parser.OBJDataReference;
import com.mokiat.data.front.parser.OBJFace;
import com.mokiat.data.front.parser.OBJMesh;
import com.mokiat.data.front.parser.OBJModel;
import com.mokiat.data.front.parser.OBJObject;
import com.mokiat.data.front.parser.OBJParser;
import com.mokiat.data.front.parser.OBJTexCoord;
import com.mokiat.data.front.test.WFResourceFixture;

public class OBJParserDetailedTest extends AbstractOBJParserTest {
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final IOBJParser parser = new OBJParser();
	private OBJModel model;
	
	@Test
	public void testTexCoords() throws Exception {
		model = fixture.parseOBJ("valid_tex_coords.obj", parser);
		final List<OBJTexCoord> texCoords = model.getTexCoords();
		assertTexCoord(texCoords.get(0), 0.1f, null, null);
		assertTexCoord(texCoords.get(1), 0.4f, 0.5f, null);
		assertTexCoord(texCoords.get(2), 0.7f, 0.8f, 0.9f);
	}
	
	@Test
	public void testNoMesh() throws Exception {
		model = fixture.parseOBJ("valid_no_mesh.obj", parser);
		
		final OBJObject object = model.getObjects().get(0);
		final List<OBJMesh> meshes = object.getMeshes();
		assertEquals("Wrong mesh count.", 1, meshes.size());
		
		final OBJMesh mesh = meshes.get(0);
		assertNotNull("Mesh should not be null.", mesh);
		assertNull("Mesh should not have material name.", mesh.getMaterialName());
	}
	
	@Test
	public void testNoObject() throws Exception {
		model = fixture.parseOBJ("valid_no_object.obj", parser);
		
		final OBJObject object = model.getObjects().get(0);
		assertNotNull("Object should not be null.", object);
		assertEquals("Object should have default name.", "Default", object.getName());
		
		final List<OBJMesh> meshes = object.getMeshes();
		assertEquals("One mesh expected.", 1, meshes.size());
		assertEquals("Wrong mesh material name.", "Red", meshes.get(0).getMaterialName());
	}
	
	@Test
	public void testNoObjectAndNoMesh() throws Exception {
		model = fixture.parseOBJ("valid_no_object_no_mesh.obj", parser);
		
		final OBJObject object = model.getObjects().get(0);
		assertNotNull("Object should not be null.", object);
		assertEquals("Object should have default name.", "Default", object.getName());

		final List<OBJMesh> meshes = object.getMeshes();
		assertEquals("One mesh expected.", 1, meshes.size());
		assertNull("Mesh should not have material name.", meshes.get(0).getMaterialName());
	}
	
	@Test
	public void testNegativeIndices() throws Exception {
		model = fixture.parseOBJ("valid_negative_indices.obj", parser);
		
		final OBJObject object = model.getObjects().get(0);
		final OBJMesh mesh = object.getMeshes().get(0);
		final OBJFace face = mesh.getFaces().get(0);
		
		final List<OBJDataReference> references = face.getReferences();
		assertReference(references.get(0), 0, 3, 0);
		assertReference(references.get(1), 1, 0, 0);
		assertReference(references.get(2), 2, 2, 1);
	}
	
	@Test
	public void testFindObject() throws Exception {
		model = fixture.parseOBJ("valid_objects.obj", parser);
		
		final OBJObject first = model.getObject("First");
		assertNotNull("Object should not be null.", first);
		assertEquals("Wrong object name.", "First", first.getName());
		
		final OBJObject second = model.getObject("Second");
		assertNotNull("Object should not be null.", second);
		assertEquals("Wrong object name.", "Second", second.getName());
	}
	

}
