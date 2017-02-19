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
import com.mokiat.data.front.common.MTLLimits;
import com.mokiat.data.front.error.WFException;
import com.mokiat.data.front.error.WFSizeException;

/**
 * This implementation of the {@link IMTLScannerHandler} assures
 * that some events do not exceed a custom specified threshold.
 * Should that happen, an {@link WFSizeException} is thrown.
 * <p>
 * Most common usage of this class is to assure that during loading
 * the file is within reasonable size and that the application
 * will not fail with an {@link OutOfMemoryError}.
 * <p>
 * THis class implements the decorator pattern, so you need
 * to specify another handler to which all events will be forwarded
 * after they have passed a threshold check.
 * 
 * @author Momchil Atanasov
 *
 */
public class MTLLimitingScannerHandler implements IMTLScannerHandler {
	
	
	private final IMTLScannerHandler delegate;
	private final MTLLimits limits;
	private int commentCount = 0;
	private int materialCount = 0;

	
	/**
	 * Creates a new {@link MTLLimitingScannerHandler} with the specified
	 * delegate {@link IMTLScannerHandler} and {@link MTLLimits}.
	 * @param delegate handler to which events will be delegated on success.
	 * @param limits object specifying the scanning limits after which
	 * an {@link WFSizeException} is thrown.
	 * @see MTLLimits
	 */
	public MTLLimitingScannerHandler(IMTLScannerHandler delegate, MTLLimits limits) {
		this.delegate = delegate;
		this.limits = limits;
	}
	
	@Override
	public void onComment(String comment) throws WFException {
		commentCount++;
		if (commentCount > limits.maxCommentCount) {
			throw new WFSizeException("Too many comments.");
		}
		delegate.onComment(comment);
	}

	@Override
	public void onMaterial(String name) throws WFException {
		materialCount++;
		if (materialCount > limits.maxMaterialCount) {
			throw new WFSizeException("Too many materials.");
		}
		delegate.onMaterial(name);
	}

	@Override
	public void onAmbientColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException {
		delegate.onAmbientColorRGB(r, g, b);
	}

	@Override
	public void onDiffuseColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException {
		delegate.onDiffuseColorRGB(r, g, b);
	}

	@Override
	public void onSpecularColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException {
		delegate.onSpecularColorRGB(r, g, b);
	}

	@Override
	public void onTransmissionColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException {
		delegate.onTransmissionColorRGB(r, g, b);
	}

	@Override
	public void onDissolve(IFastFloat amount) throws WFException {
		delegate.onDissolve(amount);
	}

	@Override
	public void onSpecularExponent(IFastFloat amount) throws WFException {
		delegate.onSpecularExponent(amount);
	}

	@Override
	public void onAmbientTexture(String filename) throws WFException {
		delegate.onAmbientTexture(filename);
	}

	@Override
	public void onDiffuseTexture(String filename) throws WFException {
		delegate.onDiffuseTexture(filename);
	}

	@Override
	public void onSpecularTexture(String filename) throws WFException {
		delegate.onSpecularTexture(filename);
	}

	@Override
	public void onSpecularExponentTexture(String filename) throws WFException {
		delegate.onSpecularExponentTexture(filename);
	}

	@Override
	public void onDissolveTexture(String filename) throws WFException {
		delegate.onDissolveTexture(filename);
	}


}
