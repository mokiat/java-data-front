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

import com.mokiat.data.front.stub.MTLContentScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class MTLScannerDetailedTest {
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final MTLContentScannerHandler handler = new MTLContentScannerHandler();
	private int index = 0;
	
	@Test
	public void testAmbientColorsRGB() throws Exception {
		fixture.scanMTL("valid_ambient_colors.mtl", handler);
		handler.assertAmbientColorRGBCount(3);
		handler.assertAmbientColorRGB(index++, 0.3f, 0.3f, 0.3f);
		handler.assertAmbientColorRGB(index++, 0.2f, 0.2f, 0.2f);
		handler.assertAmbientColorRGB(index++, 0.5f, 0.6f, 0.7f);
	}
	
	@Test
	public void testDiffuseColorsRGB() throws Exception {
		fixture.scanMTL("valid_diffuse_colors.mtl", handler);
		handler.assertDiffuseColorRGBCount(3);
		handler.assertDiffuseColorRGB(index++, 0.3f, 0.3f, 0.3f);
		handler.assertDiffuseColorRGB(index++, 0.2f, 0.2f, 0.2f);
		handler.assertDiffuseColorRGB(index++, 0.5f, 0.6f, 0.7f);
	}
	
	@Test
	public void testSpecularColorsRGB() throws Exception {
		fixture.scanMTL("valid_specular_colors.mtl", handler);
		handler.assertSpecularColorRGBCount(3);
		handler.assertSpecularColorRGB(index++, 0.3f, 0.3f, 0.3f);
		handler.assertSpecularColorRGB(index++, 0.2f, 0.2f, 0.2f);
		handler.assertSpecularColorRGB(index++, 0.5f, 0.6f, 0.7f);
	}
	
	@Test
	public void testTransmissionColorsRGB() throws Exception {
		fixture.scanMTL("valid_transmission_colors.mtl", handler);
		handler.assertTransmissionColorRGBCount(3);
		handler.assertTransmissionColorRGB(index++, 0.3f, 0.3f, 0.3f);
		handler.assertTransmissionColorRGB(index++, 0.2f, 0.2f, 0.2f);
		handler.assertTransmissionColorRGB(index++, 0.5f, 0.6f, 0.7f);
	}

	@Test
	public void testUnsupportedColors() throws Exception {
		fixture.scanMTL("valid_unsupported_colors.mtl", handler);
	}
	
	@Test
	public void testUnknownCommand() throws Exception {
		fixture.scanMTL("valid_unknown_command.mtl", handler);
	}

}
