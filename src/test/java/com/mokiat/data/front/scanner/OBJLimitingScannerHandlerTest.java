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
import com.mokiat.data.front.common.IFastInt;
import com.mokiat.data.front.common.OBJLimits;
import com.mokiat.data.front.error.WFSizeException;
import com.mokiat.data.front.scanner.IOBJScannerHandler;
import com.mokiat.data.front.scanner.OBJLimitingScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class OBJLimitingScannerHandlerTest {
	
	private static final int FAIL_THRESHOLD = 0;
	private static final int SUCCEED_THRESHOLD = 100;

	private final WFResourceFixture fixture = new WFResourceFixture();
	private final OBJLimits limits = new OBJLimits();
	private OBJLimitingScannerHandler handler;
	private IOBJScannerHandler delegate;
	
	@Before
	public void setUp() throws Exception {
		delegate = mock(IOBJScannerHandler.class);
		handler = new OBJLimitingScannerHandler(delegate, limits);
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFSizeException.class)
	public void testCommentLimit() throws Exception {
		limits.maxCommentCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testCommentDelegate() throws Exception {
		limits.maxCommentCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onComment(
				Mockito.anyString());
	}

	@Test(expected = WFSizeException.class)
	public void testVertexLimit() throws Exception {
		limits.maxVertexCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testVertexDelegate() throws Exception {
		limits.maxVertexCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onVertex(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}
	
	@Test(expected = WFSizeException.class)
	public void testTexCoordLimit() throws Exception {
		limits.maxTexCoordCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testTexCoordDelegate() throws Exception {
		limits.maxTexCoordCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onTextureCoordinate(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}

	@Test(expected = WFSizeException.class)
	public void testNormalLimit() throws Exception {
		limits.maxNormalCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testNormalDelegate() throws Exception {
		limits.maxNormalCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onTextureCoordinate(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
	}

	@Test(expected = WFSizeException.class)
	public void testObjectLimit() throws Exception {
		limits.maxObjectCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testObjectDelegate() throws Exception {
		limits.maxObjectCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onObject(
				Mockito.anyString());
	}
	
	@Test(expected = WFSizeException.class)
	public void testFaceLimit() throws Exception {
		limits.maxFaceCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testFaceBeginDelegate() throws Exception {
		limits.maxFaceCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onFaceBegin();
	}
	
	@Test
	public void testFaceEndDelegate() throws Exception {
		limits.maxFaceCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onFaceEnd();
	}
	
	@Test(expected = WFSizeException.class)
	public void testDataReferenceLimit() throws Exception {
		limits.maxDataReferenceCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testDataReferenceDelegate() throws Exception {
		limits.maxDataReferenceCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onDataReference(
				Mockito.any(IFastInt.class), 
				Mockito.any(IFastInt.class), 
				Mockito.any(IFastInt.class));
	}
	
	@Test(expected = WFSizeException.class)
	public void testMaterialLibraryLimit() throws Exception {
		limits.maxMaterialLibraryCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testMaterialLibraryDelegate() throws Exception {
		limits.maxMaterialLibraryCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onMaterialLibrary(
				Mockito.anyString());
	}
	
	@Test(expected = WFSizeException.class)
	public void testMaterialReferenceLimit() throws Exception {
		limits.maxMaterialReferenceCount = FAIL_THRESHOLD;
		scanBasicValidOBJFile();
	}
	
	@Test
	public void testMaterialReferenceDelegate() throws Exception {
		limits.maxMaterialReferenceCount = SUCCEED_THRESHOLD;
		scanBasicValidOBJFile();
		verify(delegate, atLeastOnce()).onMaterialReference(
				Mockito.anyString());
	}
	
	
	private void scanBasicValidOBJFile() throws Exception {
		fixture.scanOBJ("valid_basic.obj", handler);
	}
	
}
