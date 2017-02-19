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

import com.mokiat.data.front.common.FastFloat;
import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.error.WFCorruptException;

/**
 * This class helps in parsing OBJ and MTL files by extracting element
 * declarations (e.g. vertex, normal, material).
 *
 * @author Momchil Atanasov
 * 
 */
class WFScanCommand {
	
	private static final int FAST_FLOAT_POOL_SIZE = 4;
    private static final String WHITE_SPACE_PATTERN = "[\\s]+";
    private static final String COMMENT_SEPARATOR = "#";
    private static final int COMMENT_SEPARATOR_LENGTH = COMMENT_SEPARATOR.length();
    private static final String LINE_EXTENSION = "\\";
    
    private final FastFloat[] fastFloats = new FastFloat[FAST_FLOAT_POOL_SIZE];
	private final StringBuilder logicalLineBuilder = new StringBuilder();
    private String logicalLine;
    private String[] segments;

    public WFScanCommand() {
    	for (int i = 0; i < FAST_FLOAT_POOL_SIZE; ++i) {
    		fastFloats[i] = new FastFloat(); 
    	}
    }
    
	public boolean parse(BufferedReader reader) throws IOException {
		final String line = readLogicalLine(reader);
		if (line == null) {
			return false;
		}
        logicalLine = line.trim();
        segments = logicalLine.split(WHITE_SPACE_PATTERN);
        return true;
	}
	
	public boolean isEmpty() {
		return (segments[0].isEmpty());
    }

	public boolean isComment() {
		return segments[0].startsWith(COMMENT_SEPARATOR);
	}

    public String getComment() {
    	final int commentIndex = logicalLine.indexOf(COMMENT_SEPARATOR);
    	final String commentSegment = logicalLine.substring(commentIndex + COMMENT_SEPARATOR_LENGTH); 
    	return commentSegment.trim();
    }
    
	public boolean isCommand(String commandName) {
		return commandName.equals(segments[0]);
	}

    public int getParameterCount() {
        return Math.max(0, segments.length - 1);
    }
    
    public int getLastParamIndex() {
    	return getParameterCount() - 1;
    }

    public String getStringParam(int index) {
        return segments[index + 1];
    }

    public float getFloatParam(int index) throws WFCorruptException {
        try {
            return Float.parseFloat(getStringParam(index));
        } catch (NumberFormatException ex) {
            throw new WFCorruptException("Could not parse float value.");
        }
    }
    
	public IFastFloat getFastFloat(int index) throws WFCorruptException {
		final int segmentIndex = (index + 1);
		if (segmentIndex >= segments.length) {
			return null;
		}
		final FastFloat fastFloat = fastFloats[index];
		fastFloat.set(getFloatParam(index));
		return fastFloat;
	}

    private String readLogicalLine(BufferedReader reader) throws IOException {
    	// Try and read a single line
    	String line = reader.readLine();
    	if (line == null) {
    		return null;
    	}
    	if (!line.endsWith(LINE_EXTENSION)) {
    		return line;
    	}
    	
    	// If line extension character is available, we have to build a logical line
    	logicalLineBuilder.setLength(0);
    	while ((line != null) && (line.endsWith(LINE_EXTENSION))) {
    		final String lineContent = line.substring(0, line.length() - 1);
    		logicalLineBuilder.append(lineContent);
    		line = reader.readLine();
    	}
    	if (line != null) {
    		logicalLineBuilder.append(line);
    	}
    	return logicalLineBuilder.toString();
	}

    
}
