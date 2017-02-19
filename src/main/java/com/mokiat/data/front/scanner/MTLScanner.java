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
import java.io.InputStreamReader;
import java.io.Reader;

import com.mokiat.data.front.error.WFException;

/**
 * Default implementation of the {@link IMTLScanner}
 * interface.
 * 
 * @author Momchil Atanasov
 *
 */
public class MTLScanner implements IMTLScanner {
	
	/**
	 * Creates a new instance of {@link MTLScanner}.
	 */
	public MTLScanner() {
		super();
	}

	@Override
	public void scan(InputStream in, IMTLScannerHandler handler) throws WFException, IOException {
		final Reader reader = new InputStreamReader(in);
		scan(new BufferedReader(reader), handler);
	}

	@Override
	public void scan(BufferedReader reader, IMTLScannerHandler handler) throws WFException, IOException {
		final MTLScanRunner runner = new MTLScanRunner(handler);
		runner.run(reader);
	}

}
