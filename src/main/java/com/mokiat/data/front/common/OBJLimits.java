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

package com.mokiat.data.front.common;

import com.mokiat.data.front.error.WFSizeException;

/**
 * The {@link OBJLimits} class can be used to
 * specify parsing limits for OBJ resources.
 * <p>
 * Properly configuring an instance of this class and 
 * passing it to parsers allows one to prevent 
 * {@link OutOfMemoryError} errors. 
 * 
 * @author Momchil Atanasov
 *
 */
public class OBJLimits {
	
	public static final int DEFAULT_MAX_COUNT = 65536;

	/**
	 * Specifies the maximum number of comments that can be
	 * parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxCommentCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of vertices that can be 
	 * parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxVertexCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of texture coordinates that
	 * can be parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxTexCoordCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of normals that can be parsed
	 * before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxNormalCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of objects that can be
	 * parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxObjectCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of faces that can be
	 * parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxFaceCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of data references that
	 * can be parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxDataReferenceCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of material libraries that
	 * can be parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxMaterialLibraryCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of material references that
	 * can be parsed before an {@link WFSizeException} is thrown.
	 * <p>
	 * By default this value is equal to {@value #DEFAULT_MAX_COUNT}.
	 */
	public int maxMaterialReferenceCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Creates a new instance of {@link OBJLimits}.
	 */
	public OBJLimits() {
		super();
	}
	
}
