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

import java.util.List;

import org.junit.Test;

import com.mokiat.data.front.parser.MTLMaterial;

public class MTLParserDetailedTest extends AbstractMTLParserTest {
	
	@Test
	public void testMultipleMaterials() throws Exception {
		library = fixture.parseMTL("valid_multiple_materials.mtl", parser);
		final List<MTLMaterial> materials = library.getMaterials(); 
		assertEquals("Wrong number of materials.", 2, materials.size());
		assertEquals("Wrong material name.", "FirstMaterial", materials.get(0).getName());
		assertEquals("Wrong material name.", "SecondMaterial", materials.get(1).getName());
	}
	
	@Test
	public void testFindMaterial() throws Exception {
		library = fixture.parseMTL("valid_multiple_materials.mtl", parser);
		
		final MTLMaterial firstMaterial = library.getMaterial("FirstMaterial");
		assertNotNull("Material should not be null.", firstMaterial);
		assertEquals("Wrong material name.", "FirstMaterial", firstMaterial.getName());
		
		final MTLMaterial secondMaterial = library.getMaterial("SecondMaterial");
		assertNotNull("Material should not be null.", secondMaterial);
		assertEquals("Wrong material name.", "SecondMaterial", secondMaterial.getName());
	}

}
