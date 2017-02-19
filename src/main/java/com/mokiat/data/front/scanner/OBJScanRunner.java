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

import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.common.IFastInt;
import com.mokiat.data.front.error.WFCorruptException;
import com.mokiat.data.front.error.WFException;

/**
 * Internal class that performs the OBJ scanning.
 * 
 * @author Momchil Atanasov
 * 
 */
class OBJScanRunner {
	
    private static final String COMMAND_VERTEX = "v";
    private static final String COMMAND_TEXCOORD = "vt";
    private static final String COMMAND_NORMAL = "vn";
    private static final String COMMAND_OBJECT = "o";
    private static final String COMMAND_FACE = "f";
    private static final String COMMAND_MATERIAL_REF = "usemtl";
    private static final String COMMAND_MATERIAL_LIB = "mtllib";
	
	private final IOBJScannerHandler handler;
	private final WFScanCommand command = new WFScanCommand();
	private final OBJScanDataReference dataReference = new OBJScanDataReference();

	public OBJScanRunner(IOBJScannerHandler handler) {
		this.handler = handler;
	}
	
	public void run(BufferedReader reader) throws WFException, IOException {
		while (command.parse(reader)) {
            if (command.isEmpty()) {
                continue;
            } else if (command.isComment()) {
				processComment(command);
			} else if (command.isCommand(COMMAND_VERTEX)) {
            	processVertex(command);
            } else if (command.isCommand(COMMAND_TEXCOORD)) {
            	processTexCoord(command);
            } else if (command.isCommand(COMMAND_NORMAL)) {
            	processNormal(command);
            } else if (command.isCommand(COMMAND_OBJECT)) {
            	processObject(command);
            } else if (command.isCommand(COMMAND_FACE)) {
            	processFace(command);
            } else if (command.isCommand(COMMAND_MATERIAL_LIB)) {
            	processMaterialLibrary(command);
            } else if (command.isCommand(COMMAND_MATERIAL_REF)) {
            	processMaterialReference(command);
            }
		}
	}

	private void processComment(WFScanCommand command) throws WFException {
		handler.onComment(command.getComment());
	}

	private void processVertex(WFScanCommand command) throws WFException {
        if (command.getParameterCount() < 3) {
            throw new WFCorruptException("Insufficient vertex data.");
        }
        final IFastFloat x = command.getFastFloat(0);
        final IFastFloat y = command.getFastFloat(1);
        final IFastFloat z = command.getFastFloat(2);
        final IFastFloat w = command.getFastFloat(3);
        handler.onVertex(x, y, z, w);
	}
	
	private void processTexCoord(WFScanCommand command) throws WFException {
        if (command.getParameterCount() == 0) {
            throw new WFCorruptException("Insufficient texture coordinate data.");
        }
        final IFastFloat u = command.getFastFloat(0);
        final IFastFloat v = command.getFastFloat(1);
        final IFastFloat w = command.getFastFloat(2);
        handler.onTextureCoordinate(u, v, w);
	}

	private void processNormal(WFScanCommand command) throws WFException {
        if (command.getParameterCount() < 3) {
            throw new WFCorruptException("Insufficient normal data.");
        }
        final IFastFloat x = command.getFastFloat(0);
        final IFastFloat y = command.getFastFloat(1);
        final IFastFloat z = command.getFastFloat(2);
        handler.onNormal(x, y, z);
	}

	private void processObject(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing object name.");	
		}
        final String name = command.getStringParam(0).trim();
        handler.onObject(name);
	}

	private void processFace(WFScanCommand command) throws WFException {
		handler.onFaceBegin();
        for (int i = 0; i < command.getParameterCount(); ++i) {
        	dataReference.parse(command.getStringParam(i));
            final IFastInt vertexIndex = dataReference.getVertexIndex();
            final IFastInt texCoordIndex = dataReference.getTexCoordIndex();
            final IFastInt normalIndex = dataReference.getNormalIndex();
            handler.onDataReference(vertexIndex, texCoordIndex, normalIndex);
        }
		handler.onFaceEnd();
	}

	private void processMaterialLibrary(WFScanCommand command) throws WFException {
        for (int i = 0; i < command.getParameterCount(); ++i) {
            final String materialLibraryPath = command.getStringParam(i);
            handler.onMaterialLibrary(materialLibraryPath);
        }
	}

	private void processMaterialReference(WFScanCommand command) throws WFException {
        if (command.getParameterCount() > 0) {
        	final String name = command.getStringParam(0).trim();
        	handler.onMaterialReference(name);
        } else {
        	handler.onMaterialReference(null);
        }
	}

}
