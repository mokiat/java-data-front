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

/**
 * This interface is used to provide an integer value.
 * <p>
 * <strong>Note:</strong> You should not try and hold on to
 * instances of this interface as they are reused for performance.
 * 
 * @author Momchil Atanasov
 * 
 */
public interface IFastInt {
	
	/**
	 * Returns the integer value
	 * @return an integer value
	 */
	public int get();

}
