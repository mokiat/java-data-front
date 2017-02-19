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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import com.mokiat.data.front.error.WFException;

/**
 * The {@link IOBJScanner} interface provides methods
 * through which an OBJ file can be parsed.
 * <p>
 * Loading of the file is performed through the usage
 * of parsing events that are raised for each interesting
 * section (e.g. vertex declaration, normal declaration, etc.) of 
 * the file.
 * <p>
 * Though it does not provide any OBJ representation on completion, 
 * this interface provides a mechanism to process the file with 
 * a very small memory footprint. 
 * 
 * @author Momchil Atanasov
 * 
 */
public interface IOBJScanner {
	
	/**
	 * Scans the file specified through the {@link InputStream} and
	 * passes any events to the {@link IOBJScannerHandler} instance.
	 * @param in stream from which to read the file
	 * @param handler handler to be notified of events
	 * @throws WFException if the OBJ file is corrupt.
	 * @throws IOException if an I/O error occurs.
	 */
	public void scan(InputStream in, IOBJScannerHandler handler) throws WFException, IOException;
	
	/**
	 * Scans the file specified through the {@link BufferedReader} and
	 * passes any events to the {@link IOBJScannerHandler} instance.
	 * @param reader reader from which the file will be read
	 * @param handler handler to be notified of events
	 * @throws WFException if the OBJ file is corrupt
	 * @throws IOException if an I/O error occurs.
	 */
	public void scan(BufferedReader reader, IOBJScannerHandler handler) throws WFException, IOException;

}
