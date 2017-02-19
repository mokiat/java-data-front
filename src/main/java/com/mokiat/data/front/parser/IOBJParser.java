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

package com.mokiat.data.front.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import com.mokiat.data.front.common.OBJLimits;
import com.mokiat.data.front.error.WFException;

/**
 * The {@link IOBJParser} interface provides methods
 * through which users can parse OBJ wavefront 
 * resources.
 * 
 * @author Momchil Atanasov
 *
 */
public interface IOBJParser {
	
	/**
	 * Sets the limits that need to be followed
	 * during parsing.
	 * <p>
	 * If <code>null</code> is specified, then no
	 * constraints will be applied.
	 * @param limits the limits to follow during parsing
	 */
	public void setLimits(OBJLimits limits);
	
	/**
	 * Returns the limits that are applied during parsing.
	 * <p>
	 * If <code>null</code> is returned, then no limits
	 * have been set.
	 * @return an instance of {@link OBJLimits} or <code>null</code>
	 * if no constraints should be applied.
	 */
	public OBJLimits getLimits();
	
	/**
	 * Parses a 3D model (OBJ) resource from
	 * the specified {@link InputStream}.
	 * @param in stream from which to parse the model
	 * @return an instance of {@link OBJModel}
	 * @throws WFException if an error occurs during resource parsing
	 * @throws IOException if an I/O error occurs
	 */
	public OBJModel parse(InputStream in) throws WFException, IOException;

	/**
	 * Parses a 3D model (OBJ) resource from the
	 * specified {@link BufferedReader}.
	 * @param reader reader from which to parse the model
	 * @return an instance of {@link OBJModel}
	 * @throws WFException if an error occurs during resource parsing
	 * @throws IOException if an I/O error occurs
	 */
	public OBJModel parse(BufferedReader reader) throws WFException, IOException;
}
