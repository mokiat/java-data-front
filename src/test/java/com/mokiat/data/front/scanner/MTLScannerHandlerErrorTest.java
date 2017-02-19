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

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.error.WFException;
import com.mokiat.data.front.scanner.IMTLScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class MTLScannerHandlerErrorTest {
	
	private static final WFException EXCEPTION = new WFException();
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private IMTLScannerHandler handler;
	
	@Before
	public void setUp() throws Exception {
		handler = mock(IMTLScannerHandler.class);
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testCommentException() throws Exception {
		doThrow(EXCEPTION).when(handler).onComment(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testMaterialException() throws Exception {
		doThrow(EXCEPTION).when(handler).onMaterial(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testAmbientColorRGBException() throws Exception {
		doThrow(EXCEPTION).when(handler).onAmbientColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanValidBasicMTLFile();
	}

	@Test(expected = WFException.class)
	public void testDiffuseColorRGBException() throws Exception {
		doThrow(EXCEPTION).when(handler).onDiffuseColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testSpecularColorRGBException() throws Exception {
		doThrow(EXCEPTION).when(handler).onSpecularColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testTransmissionColorRGBException() throws Exception {
		doThrow(EXCEPTION).when(handler).onTransmissionColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testDissolveException() throws Exception {
		doThrow(EXCEPTION).when(handler).onDissolve(
				Mockito.any(IFastFloat.class));
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testSpecularExponentException() throws Exception {
		doThrow(EXCEPTION).when(handler).onSpecularExponent(
				Mockito.any(IFastFloat.class));
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testAmbientTextureException() throws Exception {
		doThrow(EXCEPTION).when(handler).onAmbientTexture(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testDiffuseTextureException() throws Exception {
		doThrow(EXCEPTION).when(handler).onDiffuseTexture(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testSpecularTextureException() throws Exception {
		doThrow(EXCEPTION).when(handler).onSpecularTexture(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testSpecularExponentTextureException() throws Exception {
		doThrow(EXCEPTION).when(handler).onSpecularExponentTexture(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}
	
	@Test(expected = WFException.class)
	public void testDissolveTextureException() throws Exception {
		doThrow(EXCEPTION).when(handler).onDissolveTexture(
				Mockito.anyString());
		scanValidBasicMTLFile();
	}

	private void scanValidBasicMTLFile() throws Exception {
		fixture.scanMTL("valid_basic.mtl", handler);
	}

}
