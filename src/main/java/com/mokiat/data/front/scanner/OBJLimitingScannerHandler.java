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
import com.mokiat.data.front.common.IFastInt;
import com.mokiat.data.front.common.OBJLimits;
import com.mokiat.data.front.error.WFException;
import com.mokiat.data.front.error.WFSizeException;

/**
 * This implementation of the {@link IOBJScannerHandler} assures
 * that all events do not exceed a custom specified threshold.
 * Should that happen, an {@link WFSizeException} is thrown.
 * <p>
 * Most common usage of this class is to assure that during loading
 * the file is within reasonable size and that the application
 * will not fail with an {@link OutOfMemoryError}.
 * <p>
 * This class implements the decorator pattern, so you need
 * to specify another handler to which all events will be forwarded
 * after they have passed a threshold check.
 * 
 * @author Momchil Atanasov
 *
 */
public class OBJLimitingScannerHandler implements IOBJScannerHandler {
	
	
	private final IOBJScannerHandler delegate;
	private final OBJLimits limits;

	private int commentCount = 0;
	private int vertexCount = 0;
	private int texCoordCount = 0;
	private int normalCount = 0;
	private int objectCount = 0;
	private int faceCount = 0;
	private int dataReferenceCount = 0;
	private int materialLibraryCount = 0;
	private int materialReferenceCount = 0;

	/**
	 * Creates a new {@link OBJLimitingScannerHandler} with the specified
	 * delegate {@link IOBJScannerHandler} and {@link OBJLimits}.
	 * @param delegate handler to which events will be delegated on success.
	 * @param limits object specifying the scanning limits after which
	 * an {@link WFSizeException} is thrown.
	 * @see OBJLimits
	 */
	public OBJLimitingScannerHandler(IOBJScannerHandler delegate, OBJLimits limits) {
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
	public void onVertex(IFastFloat x, IFastFloat y, IFastFloat z, IFastFloat w) throws WFException {
		vertexCount++;
		if (vertexCount > limits.maxVertexCount) {
			throw new WFSizeException("Too many vertices.");
		}
		delegate.onVertex(x, y, z, w);
	}

	@Override
	public void onNormal(IFastFloat x, IFastFloat y, IFastFloat z) throws WFException {
		normalCount++;
		if (normalCount > limits.maxNormalCount) {
			throw new WFSizeException("Too many normals.");
		}
		delegate.onNormal(x, y, z);
	}

	@Override
	public void onTextureCoordinate(IFastFloat u, IFastFloat v, IFastFloat w) throws WFException {
		texCoordCount++;
		if (texCoordCount > limits.maxTexCoordCount) {
			throw new WFSizeException("Too many texture coordinates.");
		}
		delegate.onTextureCoordinate(u, v, w);
	}

	@Override
	public void onObject(String objectName) throws WFException {
		objectCount++;
		if (objectCount > limits.maxObjectCount) {
			throw new WFSizeException("Too many objects.");
		}
		delegate.onObject(objectName);
	}

	@Override
	public void onFaceBegin() throws WFException {
		faceCount++;
		if (faceCount > limits.maxFaceCount) {
			throw new WFSizeException("Too many faces.");
		}
		delegate.onFaceBegin();
	}

	@Override
	public void onFaceEnd() throws WFException {
		delegate.onFaceEnd();
	}

	@Override
	public void onDataReference(IFastInt vertexIndex, IFastInt texCoordIndex, IFastInt normalIndex) throws WFException {
		dataReferenceCount++;
		if (dataReferenceCount > limits.maxDataReferenceCount) {
			throw new WFSizeException("Too many data references.");
		}
		delegate.onDataReference(vertexIndex, texCoordIndex, normalIndex);
	}

	@Override
	public void onMaterialLibrary(String libraryFilename) throws WFException {
		materialLibraryCount++;
		if (materialLibraryCount > limits.maxMaterialLibraryCount) {
			throw new WFSizeException("Too many material libraries.");
		}
		delegate.onMaterialLibrary(libraryFilename);
	}

	@Override
	public void onMaterialReference(String materialName) throws WFException {
		materialReferenceCount++;
		if (materialReferenceCount > limits.maxMaterialReferenceCount) {
			throw new WFSizeException("Too many material references.");
		}
		delegate.onMaterialReference(materialName);
	}

}
