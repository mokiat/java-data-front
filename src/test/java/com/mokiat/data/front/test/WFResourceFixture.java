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

package com.mokiat.data.front.test;

import java.io.IOException;
import java.io.InputStream;

import com.mokiat.data.front.parser.IMTLParser;
import com.mokiat.data.front.parser.IOBJParser;
import com.mokiat.data.front.parser.MTLLibrary;
import com.mokiat.data.front.parser.OBJModel;
import com.mokiat.data.front.scanner.IMTLScanner;
import com.mokiat.data.front.scanner.IMTLScannerHandler;
import com.mokiat.data.front.scanner.IOBJScanner;
import com.mokiat.data.front.scanner.IOBJScannerHandler;
import com.mokiat.data.front.scanner.MTLScanner;
import com.mokiat.data.front.scanner.OBJScanner;

public class WFResourceFixture {

	private static final String RESOURCE_PACKAGE = "/com/mokiat/data/front/test/";

	public WFResourceFixture() {
		super();
	}

	public void scanOBJ(String name, IOBJScannerHandler handler) throws IOException {
		final InputStream in = getOBJScannerResource(name);
		try {
			final IOBJScanner scanner = new OBJScanner();
			scanner.scan(in, handler);
		} finally {
			in.close();
		}
	}

	public OBJModel parseOBJ(String name, IOBJParser parser) throws IOException {
		final InputStream in = getOBJParserResource(name);
		try {
			return parser.parse(in);
		} finally {
			in.close();
		}
	}

	public void scanMTL(String name, IMTLScannerHandler handler) throws IOException {
		final InputStream in = getMTLScannerResource(name);
		try {
			final IMTLScanner scanner = new MTLScanner();
			scanner.scan(in, handler);
		} finally {
			in.close();
		}
	}

	public MTLLibrary parseMTL(String name, IMTLParser parser) throws IOException {
		final InputStream in = getMTLParserResource(name);
		try {
			return parser.parse(in);
		} finally {
			in.close();
		}
	}

    private InputStream getOBJScannerResource(String name) {
    	return getResource(RESOURCE_PACKAGE + "scanner/obj/" + name);
    }

    private InputStream getOBJParserResource(String name) {
    	return getResource(RESOURCE_PACKAGE + "parser/obj/" + name);
    }

    private InputStream getMTLScannerResource(String name) {
    	return getResource(RESOURCE_PACKAGE + "scanner/mtl/" + name);
    }

    private InputStream getMTLParserResource(String name) {
    	return getResource(RESOURCE_PACKAGE + "parser/mtl/" + name);
    }

    private InputStream getResource(String path) {
        final InputStream in = getClass().getResourceAsStream(path);
        if (in == null) {
            throw new IllegalStateException("Resource not found: " + path);
        }
        return in;
    }


}
