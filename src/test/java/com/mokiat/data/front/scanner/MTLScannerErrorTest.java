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

import com.mokiat.data.front.error.WFCorruptException;
import com.mokiat.data.front.stub.MTLContentScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class MTLScannerErrorTest {
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final MTLContentScannerHandler handler = new MTLContentScannerHandler();
	
	@Test(expected = WFCorruptException.class)
	public void testMissingMaterialName() throws Exception {
		fixture.scanMTL("error_missing_material_name.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingAmbientColorData() throws Exception {
		fixture.scanMTL("error_missing_ambient_color_data.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingDiffuseColorData() throws Exception {
		fixture.scanMTL("error_missing_diffuse_color_data.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingSpecularColorData() throws Exception {
		fixture.scanMTL("error_missing_specular_color_data.mtl", handler);
	}

	@Test(expected = WFCorruptException.class)
	public void testMissingTransmissionColorData() throws Exception {
		fixture.scanMTL("error_missing_transmission_color_data.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingDissolveData() throws Exception {
		fixture.scanMTL("error_missing_dissolve_data.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingSpecularExponentData() throws Exception {
		fixture.scanMTL("error_missing_specular_exponent_data.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingAmbientTextureFilename() throws Exception {
		fixture.scanMTL("error_missing_ambient_texture_filename.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingDiffuseTextureFilename() throws Exception {
		fixture.scanMTL("error_missing_diffuse_texture_filename.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingSpecularTextureFilename() throws Exception {
		fixture.scanMTL("error_missing_specular_texture_filename.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingSpecularExponentTextureFilename() throws Exception {
		fixture.scanMTL("error_missing_specular_exponent_texture_filename.mtl", handler);
	}
	
	@Test(expected = WFCorruptException.class)
	public void testMissingDissolveTextureFilename() throws Exception {
		fixture.scanMTL("error_missing_dissolve_texture_filename.mtl", handler);
	}

}
