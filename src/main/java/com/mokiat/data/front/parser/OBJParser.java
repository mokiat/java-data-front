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

import com.mokiat.data.front.common.OBJLimits;
import com.mokiat.data.front.error.WFException;

/**
 * Default implementation of the {@link IOBJParser}
 * interface.
 * 
 * @author Momchil Atanasov
 *
 */
public class OBJParser implements IOBJParser {
	
	private OBJLimits limits;
	
	/**
	 * Creates a new instance of {@link OBJParser}.
	 */
	public OBJParser() {
		super();
	}
	
	@Override
	public void setLimits(OBJLimits limits) {
		this.limits = limits;
	}
	
	@Override
	public OBJLimits getLimits() {
		return limits;
	}

	@Override
	public OBJModel parse(InputStream in) throws WFException, IOException {
		final Reader reader = new InputStreamReader(in);
		return parse(new BufferedReader(reader));
	}

	@Override
	public OBJModel parse(BufferedReader reader) throws WFException, IOException {
		final OBJParseRunner runner = new OBJParseRunner();
		return runner.run(reader, getLimits());
	}

}
