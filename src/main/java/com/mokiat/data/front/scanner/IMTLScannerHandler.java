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
import com.mokiat.data.front.error.WFException;

/**
 * Users should implement this interface and pass it to
 * the {@link IMTLScanner} methods in order to receive events
 * during the parsing of an MTL file.
 * 
 * @author Momchil Atanasov
 * 
 */
public interface IMTLScannerHandler {
	
	/**
	 * Called when a comment section (#) has been read.
	 * @param comment the comment.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onComment(String comment) throws WFException;
	
	/**
	 * Called when a material definition (newmtl) has been read.
	 * <p>
	 * The material name will not be <code>null</code>.
	 * @param name name of the material
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onMaterial(String name) throws WFException;

	/**
	 * Called when an ambient color definition (Ka) has been read
	 * and it specifies an RGB model of the color.
	 * <p>
	 * The specification allows for the MTL resource to specify only
	 * Red component in which case the others should be considered the
	 * same. Depending on the implementation of this interface, it is
	 * possible that it will always do this resolution on itself or
	 * it may decide to leave it to the user.
	 * <br>
	 * The former provides easier usage, whereas the latter provides
	 * unaltered resource input.
	 * @param r the Red value, will not be <code>null</code>.
	 * @param g the Green value, will be <code>null</code> if unavailable.
	 * @param b the Blue value, will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onAmbientColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException;
	
	/**
	 * Called when a diffuse color definition (Kd) has been read
	 * and it specifies an RGB model of the color.
	 * <p>
	 * The specification allows for the MTL resource to specify only
	 * Red component in which case the others should be considered the
	 * same. Depending on the implementation of this interface, it is
	 * possible that it will always do this resolution on itself or
	 * it may decide to leave it to the user.
	 * <br>
	 * The former provides easier usage, whereas the latter provides
	 * unaltered resource input.
	 * @param r the Red value, will not be <code>null</code>.
	 * @param g the Green value, will be <code>null</code> if unavailable.
	 * @param b the Blue value, will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onDiffuseColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException;
	
	/**
	 * Called when a specular reflectivity color definition (Ks) has been read
	 * and it specifies an RGB model of the color.
	 * <p>
	 * The specification allows for the MTL resource to specify only
	 * Red component in which case the others should be considered the
	 * same. Depending on the implementation of this interface, it is
	 * possible that it will always do this resolution on itself or
	 * it may decide to leave it to the user.
	 * <br>
	 * The former provides easier usage, whereas the latter provides
	 * unaltered resource input.
	 * @param r the Red value, will not be <code>null</code>.
	 * @param g the Green value, will be <code>null</code> if unavailable.
	 * @param b the Blue value, will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onSpecularColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException;
	
	/**
	 * Called when a transmission filter color definition (Tf) has been read
	 * and it specifies an RGB model of the color.
	 * <p>
	 * The specification allows for the MTL resource to specify only
	 * Red component in which case the others should be considered the
	 * same. Depending on the implementation of this interface, it is
	 * possible that it will always do this resolution on itself or
	 * it may decide to leave it to the user.
	 * <br>
	 * The former provides easier usage, whereas the latter provides
	 * unaltered resource input.
	 * @param r the Red value, will not be <code>null</code>.
	 * @param g the Green value, will be <code>null</code> if unavailable.
	 * @param b the Blue value, will be <code>null</code> if unavailable.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onTransmissionColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) throws WFException;
	
	/**
	 * Called when a dissolve statement (d) has been read.
	 * <p>
	 * Dissolve can be better considered as opacity, where a value
	 * of <code>0</code> indicates full transparency and a value of
	 * <code>1</code> indicates full opacity.
	 * @param amount the dissolve amount, will not be <code>null</code>.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onDissolve(IFastFloat amount) throws WFException;
	
	/**
	 * Called when a specular exponent statement (Ns) has been read.
	 * <p>
	 * The passed exponent value would generally be a number from 
	 * <code>0</code> to <code>1000</code>.
	 * @param amount the specular exponent amount, will not be <code>null</code>.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onSpecularExponent(IFastFloat amount) throws WFException;
	
	/**
	 * Called when an ambient texture statement (map_Ka) has been read.
	 * @param filename location of the texture file
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onAmbientTexture(String filename) throws WFException;
	
	/**
	 * Called when a diffuse texture statement (map_Kd) has been read.
	 * @param filename location of the texture file
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onDiffuseTexture(String filename) throws WFException;

	/**
	 * Called when a specular texture statement (map_Ks) has been read.
	 * @param filename location of the texture file
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onSpecularTexture(String filename) throws WFException;
	
	/**
	 * Called when a specular exponent texture statement (map_Ns) has been read. 
	 * @param filename location of the texture.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onSpecularExponentTexture(String filename) throws WFException;
	
	/**
	 * Called when a dissolve texture statement (map_d) has been read.
	 * @param filename location of the texture.
	 * @throws WFException can be thrown by users to terminate any
	 * further scanning of the resource.
	 */
	public void onDissolveTexture(String filename) throws WFException;
}
