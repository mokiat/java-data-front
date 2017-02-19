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
import com.mokiat.data.front.error.WFException;

/**
 * Users should implement this interface and pass it
 * to the {@link IOBJScanner} methods in order to receive
 * events during the parsing of an OBJ file.
 * 
 * @author Momchil Atanasov
 * 
 */
public interface IOBJScannerHandler {
	
	/**
	 * Called when a comment section (#) has been read.
	 * @param comment the parsed comment
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onComment(String comment) throws WFException;
	
	/**
	 * Called when a vertex definition (v) has been read.
	 * @param x X coordinate, will not be <code>null</code>.
	 * @param y Y coordinate, will not be <code>null</code>.
	 * @param z Z coordinate, will not be <code>null</code>.
	 * @param w optional W coordinate, will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onVertex(IFastFloat x, IFastFloat y, IFastFloat z, IFastFloat w) throws WFException;
	
	/**
	 * Called when a normal definition (vn) has been read.
	 * <p>
	 * The normal values will be just as in the parsed OBJ file
	 * and it is up to the user to normalize them if desired.
	 * @param x X coordinate, will not be <code>null</code>.
	 * @param y Y coordinate, will not be <code>null</code>.
	 * @param z Z coordinate, will not be <code>null</code>.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onNormal(IFastFloat x, IFastFloat y, IFastFloat z) throws WFException;
	
	/**
	 * Called when a texture coordinate (vt) definition has been read.
	 * @param u U coordinate, will not be <code>null</code>.
	 * @param v optional V coordinate, will be <code>null</code> if unavailable. 
	 * @param w optional W coordinate, will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onTextureCoordinate(IFastFloat u, IFastFloat v, IFastFloat w) throws WFException;
	
	/**
	 * Called when an object definition (o) has been read.
	 * <p>
	 * Any surface elements (e.g. faces) that follow should be
	 * considered part of this object.
	 * @param objectName name of the object.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onObject(String objectName) throws WFException;
	
	/**
	 * Called when a face definition (f) is being read.
	 * <p>
	 * You can expect the {@link #onDataReference(IFastInt, IFastInt, IFastInt)}
	 * to be called a number of times. Each such data reference should be considered
	 * part of this face.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onFaceBegin() throws WFException;
	
	/**
	 * Called when the reading of a face definition has completed.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onFaceEnd() throws WFException;
	
	/**
	 * Called when a data reference segment has been read.
	 * <p>
	 * These segments reference vertices, texture coordinates and normals
	 * through the use of indices.
	 * <strong>Note:</strong> Positive indices start from 1 and negative indices are
	 * allowed, where -1 means the last read until this moment referenced element.
	 * @param vertexIndex index of the referenced vertex, will not be <code>null</code>.
	 * @param texCoordIndex optional index of the referenced texture coordinate,
	 * will be <code>null</code> if unavailable.
	 * @param normalIndex optional index of the referenced normal coordinate,
	 * will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onDataReference(IFastInt vertexIndex, IFastInt texCoordIndex, IFastInt normalIndex) throws WFException;
	
	/**
	 * Called when a filename from a material library declaration (mtllib) 
	 * has been read.
	 * <p>
	 * Resolving and loading of the given library file is left to the user.
	 * @param libraryFilename filename of a material library to be used.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onMaterialLibrary(String libraryFilename) throws WFException;

	/**
	 * Called when a material reference (usemtl) has been read.
	 * <p>
	 * Per specification, it is allowed for this method to provide 
	 * a <code>null</code> material name. In this case a default white material
	 * should be used.
	 * @param materialName name of the material to use for subsequent
	 * elements
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onMaterialReference(String materialName) throws WFException;

}
