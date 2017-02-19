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

import org.junit.Test;

import com.mokiat.data.front.error.WFCorruptException;

public class MTLParserErrorTest extends AbstractMTLParserTest {
	
	@Test(expected = WFCorruptException.class)
	public void testAmbientColorNoMaterial() throws Exception {
		fixture.parseMTL("error_ambient_color_no_material.mtl", parser);
	}

	@Test(expected = WFCorruptException.class)
	public void testDiffuseColorNoMaterial() throws Exception {
		fixture.parseMTL("error_diffuse_color_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testSpecularColorNoMaterial() throws Exception {
		fixture.parseMTL("error_specular_color_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testTransmissionColorNoMaterial() throws Exception {
		fixture.parseMTL("error_transmission_color_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testSpecularExponentNoMaterial() throws Exception {
		fixture.parseMTL("error_specular_exponent_no_material.mtl", parser);
	}

	@Test(expected = WFCorruptException.class)
	public void testDissolveNoMaterial() throws Exception {
		fixture.parseMTL("error_dissolve_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testAmbientTextureNoMaterial() throws Exception {
		fixture.parseMTL("error_ambient_texture_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testDiffuseTextureNoMaterial() throws Exception {
		fixture.parseMTL("error_diffuse_texture_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testSpecularTextureNoMaterial() throws Exception {
		fixture.parseMTL("error_specular_texture_no_material.mtl", parser);
	}

	@Test(expected = WFCorruptException.class)
	public void testSpecularExponentTextureNoMaterial() throws Exception {
		fixture.parseMTL("error_specular_exponent_texture_no_material.mtl", parser);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testDissolveTextureNoMaterial() throws Exception {
		fixture.parseMTL("error_dissolve_texture_no_material.mtl", parser);
	}
	
}
