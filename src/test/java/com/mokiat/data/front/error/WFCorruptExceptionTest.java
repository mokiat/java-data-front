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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.mokiat.data.front.error.WFCorruptException;

public class WFCorruptExceptionTest extends AbstractWFExceptionTest {
	
	@Test
	public void testDefaultException() {
		final WFCorruptException ex = new WFCorruptException();
		assertNull(ex.getMessage());
		assertNull(ex.getCause());
	}
	
	@Test
	public void testExceptionWithMessage() {
		final WFCorruptException ex = new WFCorruptException(MESSAGE);
		assertEquals(MESSAGE, ex.getMessage());
	}
	
	@Test
	public void testExceptionWithCause() {
		final WFCorruptException ex = new WFCorruptException(CAUSE);
		assertSame(CAUSE, ex.getCause());
	}
	
	@Test
	public void testExceptionWithMessageAndCause() {
		final WFCorruptException ex = new WFCorruptException(MESSAGE, CAUSE);
		assertEquals(MESSAGE, ex.getMessage());
		assertSame(CAUSE, ex.getCause());
	}

}
