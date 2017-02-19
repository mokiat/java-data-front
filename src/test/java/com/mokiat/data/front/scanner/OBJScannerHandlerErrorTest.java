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

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.common.IFastInt;
import com.mokiat.data.front.error.WFException;
import com.mokiat.data.front.scanner.IOBJScannerHandler;
import com.mokiat.data.front.test.WFResourceFixture;

public class OBJScannerHandlerErrorTest {
	
	private static final WFException EXCEPTION = new WFException();
	
	private final WFResourceFixture fixture = new WFResourceFixture();
	private IOBJScannerHandler handler;
	
	@Before
	public void setUp() throws IOException {
		handler = mock(IOBJScannerHandler.class);
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testCommentException() throws Exception {
		doThrow(EXCEPTION).when(handler).onComment(
				Mockito.anyString());
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testVertexException() throws Exception {
		doThrow(EXCEPTION).when(handler).onVertex(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testTexCoordException() throws Exception {
		doThrow(EXCEPTION).when(handler).onTextureCoordinate(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testNormalException() throws Exception {
		doThrow(EXCEPTION).when(handler).onNormal(
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class), 
				Mockito.any(IFastFloat.class));
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testObjectException() throws Exception {
		doThrow(EXCEPTION).when(handler).onObject(
				Mockito.anyString());
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testFaceBeginException() throws Exception {
		doThrow(EXCEPTION).when(handler).onFaceBegin();
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testFaceEndException() throws Exception {
		doThrow(EXCEPTION).when(handler).onFaceEnd();
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testDataReferenceException() throws Exception {
		doThrow(EXCEPTION).when(handler).onDataReference(
				Mockito.any(IFastInt.class),
				Mockito.any(IFastInt.class),
				Mockito.any(IFastInt.class));
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testMaterialLibraryException() throws Exception {
		doThrow(EXCEPTION).when(handler).onMaterialLibrary(
				Mockito.anyString());
		scanBasicValidOBJFile();
	}
	
	@Test(expected = WFException.class)
	public void testMaterialReferenceException() throws Exception {
		doThrow(EXCEPTION).when(handler).onMaterialReference(
				Mockito.anyString());
		scanBasicValidOBJFile();
	}
	

	private void scanBasicValidOBJFile() throws IOException {
		fixture.scanOBJ("valid_basic.obj", handler);
	}

}
