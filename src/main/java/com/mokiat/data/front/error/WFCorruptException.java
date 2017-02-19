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

package com.mokiat.data.front.error;

/**
 * This exception is used to indicate a corrupt or invalid
 * WaveFront resource.
 *
 * @author Momchil Atanasov
 * 
 */
public class WFCorruptException extends WFException {

	private static final long serialVersionUID = 1L;
	
	public WFCorruptException() {
		super();
	}
	
	public WFCorruptException(String message) {
		super(message);
	}

	public WFCorruptException(Throwable ex) {
		super(ex);
	}

	public WFCorruptException(String message, Throwable ex) {
		super(message, ex);
	}

}
