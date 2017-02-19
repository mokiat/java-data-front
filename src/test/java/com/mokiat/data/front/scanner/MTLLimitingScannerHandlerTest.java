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

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.common.MTLLimits;
import com.mokiat.data.front.error.WFSizeException;
import com.mokiat.data.front.scanner.IMTLScannerHandler;
import com.mokiat.data.front.scanner.MTLLimitingScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class MTLLimitingScannerHandlerTest {
	
	private static final int FAIL_THRESHOLD = 0;
	private static final int SUCCEED_THRESHOLD = 100;
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private final MTLLimits limits = new MTLLimits();
	private IMTLScannerHandler delegate;
	private MTLLimitingScannerHandler handler;
	
	
	@Before
	public void setUp() throws Exception {
		delegate = mock(IMTLScannerHandler.class);
		handler = new MTLLimitingScannerHandler(delegate, limits);
		scanBasicValidMTLFile();
	}

	@Test(expected = WFSizeException.class)
	public void testCommentLimit() throws Exception {
		limits.maxCommentCount = FAIL_THRESHOLD;
		scanBasicValidMTLFile();
	}
	
	@Test
	public void testCommentDelegate() throws Exception {
		limits.maxCommentCount = SUCCEED_THRESHOLD;
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onComment(
				Mockito.anyString());
	}

	@Test(expected = WFSizeException.class)
	public void testMaterialLimit() throws Exception {
		limits.maxMaterialCount = FAIL_THRESHOLD;
		scanBasicValidMTLFile();
	}
	
	@Test
	public void testMaterialDelegate() throws Exception {
		limits.maxMaterialCount = SUCCEED_THRESHOLD;
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onMaterial(
				Mockito.anyString());
	}
	
	@Test
	public void testAmbientColorRGBDelegate() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onAmbientColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}
	
	@Test
	public void testDiffuseColorRGBDelegate() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onDiffuseColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}
	
	@Test
	public void testSpecularColorRGBDelegate() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onSpecularColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}
	
	@Test
	public void testTransmissionColorRGBDelegate() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onTransmissionColorRGB(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}
	
	@Test
	public void testDissolve() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onDissolve(
				Mockito.any(IFastFloat.class));
	}
	
	@Test
	public void testSpecularExponent() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onSpecularExponent(
				Mockito.any(IFastFloat.class));
	}
	
	@Test
	public void testAmbientTexture() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onAmbientTexture(
				Mockito.anyString());
	}
	
	@Test
	public void testDiffuseTexture() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onDiffuseTexture(
				Mockito.anyString());
	}
	
	@Test
	public void testSpecularTexture() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onSpecularTexture(
				Mockito.anyString());
	}
	
	@Test
	public void testSpecularExponentTexture() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onSpecularExponentTexture(
				Mockito.anyString());
	}
	
	@Test
	public void testDissolveTexture() throws Exception {
		scanBasicValidMTLFile();
		verify(delegate, atLeastOnce()).onDissolveTexture(
				Mockito.anyString());
	}

	private void scanBasicValidMTLFile() throws Exception {
		fixture.scanMTL("valid_basic.mtl", handler);
	}

}
