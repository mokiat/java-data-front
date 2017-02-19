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

import static org.junit.Assert.assertEquals;

import com.mokiat.data.front.parser.IMTLParser;
import com.mokiat.data.front.parser.MTLColor;
import com.mokiat.data.front.parser.MTLLibrary;
import com.mokiat.data.front.parser.MTLParser;
import com.mokiat.data.front.test.WFResourceFixture;

public class AbstractMTLParserTest {
	
	protected static final float FLOAT_MARGIN = 0.00001f;
	
	protected final WFResourceFixture fixture = new WFResourceFixture();
	protected final IMTLParser parser = new MTLParser();
	protected MTLLibrary library;

	protected void assertColor(MTLColor color, float r, float g, float b) {
		assertEquals(r, color.r, FLOAT_MARGIN);
		assertEquals(g, color.g, FLOAT_MARGIN);
		assertEquals(b, color.b, FLOAT_MARGIN);
	}
	
}
