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

import com.mokiat.data.front.stub.MTLContentScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class MTLScannerBasicTest {
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final MTLContentScannerHandler handler = new MTLContentScannerHandler();
	private int index = 0;
	
	@Before
	public void setUp() throws Exception {
		fixture.scanMTL("valid_basic.mtl", handler);
	}
	
	@Test
	public void testComment() {
		handler.assertCommentCount(1);
		handler.assertComment(index++, "This is the beginning of this MTL file.");
	}
	
	@Test
	public void testMaterial() {
		handler.assertMaterialCount(1);
		handler.assertMaterial(index++, "MyMaterial");
	}
	
	@Test
	public void testAmbientColorRGB() {
		handler.assertAmbientColorRGBCount(1);
		handler.assertAmbientColorRGB(index++, 0.8f, 0.5f, 0.2f);
	}
	
	@Test
	public void testDiffuseColorRGB() {
		handler.assertDiffuseColorRGBCount(1);
		handler.assertDiffuseColorRGB(index++, 0.1f, 0.4f, 0.7f);
	}
	
	@Test
	public void testSpecularColorRGB() {
		handler.assertSpecularColorRGBCount(1);
		handler.assertSpecularColorRGB(index++, 0.3f, 0.2f, 1.0f);
	}
	
	@Test
	public void testTransmissionColorRGB() {
		handler.assertTransmissionColorRGBCount(1);
		handler.assertTransmissionColorRGB(index++, 0.6f, 0.7f, 0.8f);
	}
	
	@Test
	public void testDissolve() {
		handler.assertDissolveCount(1);
		handler.assertDissolve(index++, 0.4f);
	}
	
	@Test
	public void testSpecularExponent() {
		handler.assertSpecularExponentCount(1);
		handler.assertSpecularExponent(index++, 330.0f);
	}
	
	@Test
	public void testAmbientTexture() {
		handler.assertAmbientTextureCount(1);
		handler.assertAmbientTexture(index++, "textures/ambient.bmp");
	}
	
	@Test
	public void testDiffuseTexture() {
		handler.assertDiffuseTextureCount(1);
		handler.assertDiffuseTexture(index++, "textures/diffuse.bmp");
	}
	
	@Test
	public void testSpecularTexture() {
		handler.assertSpecularTextureCount(1);
		handler.assertSpecularTexture(index++, "textures/specular.bmp");
	}
	
	@Test
	public void testSpecularExponentTexture() {
		handler.assertSpecularExponentTextureCount(1);
		handler.assertSpecularExponentTexture(index++, "textures/specular_exponent.bmp");
	}
	
	@Test
	public void testDissolveTexture() {
		handler.assertDissolveTextureCount(1);
		handler.assertDissolveTexture(index++, "textures/dissolve.bmp");
	}
	

}
