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
import java.io.InputStreamReader;
import java.io.Reader;

import com.mokiat.data.front.common.MTLLimits;
import com.mokiat.data.front.error.WFException;

/**
 * Default implementation of the {@link IMTLParser}
 * interface.
 * 
 * @author Momchil Atanasov
 *
 */
public class MTLParser implements IMTLParser {
	
	private MTLLimits limits;
	
	/**
	 * Creates a new {@link MTLParser} instance.
	 */
	public MTLParser() {
		super();
	}
	
	@Override
	public void setLimits(MTLLimits limits) {
		this.limits = limits;
	}
	
	@Override
	public MTLLimits getLimits() {
		return limits;
	}

	@Override
	public MTLLibrary parse(InputStream in) throws WFException, IOException {
		final Reader reader = new InputStreamReader(in);
		return parse(new BufferedReader(reader));
	}

	@Override
	public MTLLibrary parse(BufferedReader reader) throws WFException, IOException {
		final MTLParseRunner runner = new MTLParseRunner();
		return runner.run(reader, getLimits());
	}

}
