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

import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.error.WFCorruptException;

/**
 * Internal class that is used to parse color definitions.
 * 
 * @author Momchil Atanasov
 *
 */
class MTLScanColor {
	
    private static final String SPECTRAL_COLOR = "spectral";
    private static final String XYZ_COLOR = "xyz";
	
	private IFastFloat r;
	private IFastFloat g;
	private IFastFloat b;
	
	public MTLScanColor() {
		super();
	}

	public void process(WFScanCommand command) throws WFCorruptException {
        if (command.getParameterCount() == 0) {
            throw new WFCorruptException("Insufficient color data.");
        }
        if (SPECTRAL_COLOR.equals(command.getStringParam(0))) {
            return;
        }
        if (XYZ_COLOR.equals(command.getStringParam(0))) {
            return;
        }
        r = command.getFastFloat(0);
        if (command.getParameterCount() < 3) {
        	g = command.getFastFloat(0);
        	b = command.getFastFloat(0);
        } else {
        	g = command.getFastFloat(1);
        	b = command.getFastFloat(2);
        }
	}

	public boolean isRGB() {
		return (r != null && g != null && b != null);
	}

	public IFastFloat getR() {
		return r;
	}

	public IFastFloat getG() {
		return g;
	}

	public IFastFloat getB() {
		return b;
	}

}
