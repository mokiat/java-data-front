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

import org.junit.Before;
import org.junit.Test;

import com.mokiat.data.front.parser.MTLMaterial;

public class MTLParserBasicTest extends AbstractMTLParserTest {
	
	private MTLMaterial material;
	
	@Before
	public void setUp() throws Exception {
		library = fixture.parseMTL("valid_basic.mtl", parser);
		assertNotNull("Library should not be null.", library);
		assertEquals("There should be one material.", 1, library.getMaterials().size());
		material = library.getMaterials().get(0);
		assertNotNull("Material should not be null.", material);
	}
	
	@Test
	public void testName() {
		assertEquals("Wrong material name.", "TestMaterial", material.getName());
	}
	
	@Test
	public void testAmbientColor() {
		assertColor(material.getAmbientColor(), 1.0f, 0.5f, 0.1f);
	}
	
	@Test
	public void testDiffuseColor() {
		assertColor(material.getDiffuseColor(), 0.5f, 0.7f, 0.3f);
	}
	
	@Test
	public void testSpecularColor() {
		assertColor(material.getSpecularColor(), 0.2f, 0.4f, 0.8f);
	}

	@Test
	public void testTransmissionColor() {
		assertColor(material.getTransmissionColor(), 0.3f, 1.0f, 0.4f);
	}

	@Test
	public void testSpecularExponent() {
		assertEquals("Wrong specular exponent.", 650.0f, material.getSpecularExponent(), FLOAT_MARGIN);
	}
	
	@Test
	public void testDissolve() {
		assertEquals("Wrong dissolve.", 0.7f, material.getDissolve(), FLOAT_MARGIN);
	}
	
	@Test
	public void testAmbientTexture() {
		assertEquals("Wrong ambient texture.", "ambient.png", material.getAmbientTexture());
	}
	
	@Test
	public void testDiffuseTexture() {
		assertEquals("Wrong diffuse texture.", "diffuse.png", material.getDiffuseTexture());
	}
	
	@Test
	public void testSpecularTexture() {
		assertEquals("Wrong specular texture.", "specular.png", material.getSpecularTexture());
	}
	
	@Test
	public void testSpecularExponentTexture() {
		assertEquals("Wrong specular exponent texture.", "specular_exponent.png", material.getSpecularExponentTexture());
	}

	@Test
	public void testDissolveTexture() {
		assertEquals("Wrong dissolve texture.", "dissolve.png", material.getDissolveTexture());
	}

}
