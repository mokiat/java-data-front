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
import com.mokiat.data.front.error.WFCorruptException;
import com.mokiat.data.front.error.WFException;

/**
 * Internal class that performs the MTL scanning.
 * 
 * @author Momchil Atanasov
 *
 */
class MTLScanRunner {
	
	private static final String COMMAND_MATERIAL = "newmtl";
    private static final String COMMAND_AMBIENT_COLOR = "Ka";
    private static final String COMMAND_DIFFUSE_COLOR = "Kd";
    private static final String COMMAND_SPECULAR_COLOR = "Ks";
    private static final String COMMAND_TRANSMISSION_COLOR = "Tf";
    private static final String COMMAND_DISSOLVE = "d";
    private static final String COMMAND_SPECULAR_EXPONENT = "Ns";
    private static final String COMMAND_AMBIENT_TEXTURE = "map_Ka";
    private static final String COMMAND_DIFFUSE_TEXTURE = "map_Kd";
    private static final String COMMAND_SPECULAR_TEXTURE = "map_Ks";
    private static final String COMMAND_SPECULAR_EXPONENT_TEXTURE = "map_Ns";
    private static final String COMMAND_DISSOLVE_TEXTURE = "map_d";

	private final IMTLScannerHandler handler;
	private final WFScanCommand command = new WFScanCommand();
	private final MTLScanColor color = new MTLScanColor(); 

	public MTLScanRunner(IMTLScannerHandler handler) {
		this.handler = handler;
	}

	public void run(BufferedReader reader) throws WFException, IOException {
		while (command.parse(reader)) {
            if (command.isEmpty()) {
                continue;
            } else if (command.isComment()) {
				processComment(command);
			} else if (command.isCommand(COMMAND_MATERIAL)) {
            	processMaterial(command);
            } else if (command.isCommand(COMMAND_AMBIENT_COLOR)) {
            	processAmbientColor(command);
            } else if (command.isCommand(COMMAND_DIFFUSE_COLOR)) {
            	processDiffuseColor(command);
            } else if (command.isCommand(COMMAND_SPECULAR_COLOR)) {
            	processSpecularColor(command);
            } else if (command.isCommand(COMMAND_TRANSMISSION_COLOR)) {
            	processTransmissionColor(command);
            } else if (command.isCommand(COMMAND_DISSOLVE)) {
            	processDissolve(command);
            } else if (command.isCommand(COMMAND_SPECULAR_EXPONENT)) {
            	processSpecularExponent(command);
            } else if (command.isCommand(COMMAND_AMBIENT_TEXTURE)) {
            	processAmbientTexture(command);
            } else if (command.isCommand(COMMAND_DIFFUSE_TEXTURE)) {
            	processDiffuseTexture(command);
            } else if (command.isCommand(COMMAND_SPECULAR_TEXTURE)) {
            	processSpecularTexture(command);
            } else if (command.isCommand(COMMAND_SPECULAR_EXPONENT_TEXTURE)) {
            	processSpecularExponentTexture(command);
            } else if (command.isCommand(COMMAND_DISSOLVE_TEXTURE)) {
            	processDissolveTexture(command);
            }
		}
	}

	private void processComment(WFScanCommand command) throws WFException {
		handler.onComment(command.getComment());
	}
	
	private void processMaterial(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing material name.");
		}
		handler.onMaterial(command.getStringParam(0));
	}
	
	private void processAmbientColor(WFScanCommand command) throws WFException {
		color.process(command);
		if (color.isRGB()) {
	        handler.onAmbientColorRGB(color.getR(), color.getG(), color.getB());
		}
	}
	
	private void processDiffuseColor(WFScanCommand command) throws WFException {
		color.process(command);
		if (color.isRGB()) {
			handler.onDiffuseColorRGB(color.getR(), color.getG(), color.getB());
		}
	}
	
	private void processSpecularColor(WFScanCommand command) throws WFException {
		color.process(command);
		if (color.isRGB()) {
			handler.onSpecularColorRGB(color.getR(), color.getG(), color.getB());
		}
	}
	
	private void processTransmissionColor(WFScanCommand command) throws WFException {
		color.process(command);
		if (color.isRGB()) {
			handler.onTransmissionColorRGB(color.getR(), color.getG(), color.getB());
		}
	}
	
	private void processDissolve(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing dissolve factor.");
		}
		final IFastFloat factor = command.getFastFloat(command.getLastParamIndex());
		handler.onDissolve(factor);
	}
	
	private void processSpecularExponent(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing specular exponent amount.");
		}
		final IFastFloat amount = command.getFastFloat(command.getLastParamIndex());
		handler.onSpecularExponent(amount);
	}

	private void processAmbientTexture(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing ambient texture filename.");
		}
		final String filename = getTextureFilename(command);
		handler.onAmbientTexture(filename);
	}

	private void processDiffuseTexture(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing diffuse texture filename.");
		}
		final String filename = getTextureFilename(command);
		handler.onDiffuseTexture(filename);
	}
	
	private void processSpecularTexture(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing specular texture filename.");
		}
		final String filename = getTextureFilename(command);
		handler.onSpecularTexture(filename);
	}

	private void processSpecularExponentTexture(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing specular exponent texture filename.");
		}
		final String filename = getTextureFilename(command);
		handler.onSpecularExponentTexture(filename);
	}
	
	private void processDissolveTexture(WFScanCommand command) throws WFException {
		if (command.getParameterCount() == 0) {
			throw new WFCorruptException("Missing dissolve texture filename.");
		}
		final String filename = getTextureFilename(command);
		handler.onDissolveTexture(filename);
	}

	private String getTextureFilename(WFScanCommand command) {
		return command.getStringParam(command.getLastParamIndex());
	}
	
}
