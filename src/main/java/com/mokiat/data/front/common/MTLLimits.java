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
 * The {@link MTLLimits} class can be used to
 * specify parsing limits for MTL resources.
 * <p>
 * Properly configuring an instance of this class
 * and passing it to parsers allows one to prevent
 * {@link OutOfMemoryError} errors.
 * 
 * @author Momchil Atanasov
 *
 */
public class MTLLimits {
	
	private static final int DEFAULT_MAX_COUNT = 65536;
	
	/**
	 * Specifies the maximum number of comments that can be 
	 * parsed before an {@link WFSizeException} is thrown.
	 */
	public int maxCommentCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Specifies the maximum number of materials that can be
	 * parsed before an {@link WFSizeException} is thrown.
	 */
	public int maxMaterialCount = DEFAULT_MAX_COUNT;
	
	/**
	 * Creates a new {@link MTLLimits} instance.
	 */
	public MTLLimits() {
		super();
	}
	
}
