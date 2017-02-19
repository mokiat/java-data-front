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

package com.mokiat.data.front.parser;

/**
 * Represents a single material.
 * 
 * @author Momchil Atanasov
 *
 */
public class MTLMaterial {
	
	private String name;
	private final MTLColor ambientColor = new MTLColor(1.0f, 1.0f, 1.0f);
	private final MTLColor diffuseColor = new MTLColor(1.0f, 1.0f, 1.0f);
	private final MTLColor specularColor = new MTLColor(0.0f, 0.0f, 0.0f);
	private final MTLColor transmissionColor = new MTLColor(0.0f, 0.0f, 0.0f);
	private float specularExponent = 0.0f;
	private float dissolve = 1.0f;
	private String ambientTexture;
	private String diffuseTexture;
	private String specularTexture;
	private String specularExponentTexture;
	private String dissolveTexture;
	
	
	/**
	 * Creates a new default {@link MTLMaterial}.
	 * <p>
	 * Note: The name of this material will be
	 * <code>null</code> which is considered invalid
	 * according to the MTL specification.
	 * <br>
	 * One needs to set a name before this material can be
	 * serialized or properly used.
	 */
	public MTLMaterial() {
		super();
	}
	
	/**
	 * Creates a new {@link MTLMaterial} with the
	 * specified name
	 * @param name name of the material
	 */
	public MTLMaterial(String name) {
		this.name = name;
	}
	
	/**
	 * A copy-constructor that creates a new {@link MTLMaterial}
	 * based on an existing {@link MTLMaterial}.
	 * @param other material to copy
	 */
	public MTLMaterial(MTLMaterial other) {
		this.ambientColor.setTo(other.ambientColor);
		this.diffuseColor.setTo(other.diffuseColor);
		this.specularColor.setTo(other.specularColor);
		this.transmissionColor.setTo(other.transmissionColor);
		this.specularExponent = other.specularExponent;
		this.dissolve = other.dissolve;
		this.ambientTexture = other.ambientTexture;
		this.diffuseTexture = other.dissolveTexture;
		this.specularTexture = other.specularTexture;
		this.specularExponentTexture = other.specularExponentTexture;
		this.dissolveTexture = other.dissolveTexture;
	}
	
	/**
	 * Sets a new name for this material.
	 * <p>
	 * The new name should not be <code>null</code> though
	 * no restriction or validation is placed.
	 * @param name name of the material
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of this material.
	 * <p>
	 * Generally, this value should not be <code>null</code>,
	 * though this could happen if the user decided to leave
	 * it in such state.
	 * @return name of the material
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns this material's ambient color.
	 * <p>
	 * By default this color will be equal to <code>White</code>.
	 * <p>
	 * You can directly use the returned instance to
	 * change the color's values.
	 * @return an instance of {@link MTLColor}.
	 */
	public MTLColor getAmbientColor() {
		return ambientColor;
	}
	
	/**
	 * Returns this material's diffuse color.
	 * <p>
	 * By default this color will be equal to <code>White</code>.
	 * <p>
	 * You can directly use the returned instance to change
	 * the color's values.
	 * @return an instance of {@link MTLColor}.
	 */
	public MTLColor getDiffuseColor() {
		return diffuseColor;
	}
	
	/**
	 * Returns this material's specular color.
	 * <p>
	 * By default this color will be equal to <code>Black</code>.
	 * <p>
	 * You can directly use the returned instance to change
	 * the color's values.
	 * @return an instance of {@link MTLColor}.
	 */
	public MTLColor getSpecularColor() {
		return specularColor;
	}
	
	/**
	 * Returns this material's transmission filter color.
	 * <p>
	 * By default this color will be equal to <code>Black</code>.
	 * <p>
	 * You can directly use the returned instance to change
	 * the color's values.
	 * @return an instance of {@link MTLColor}.
	 */
	public MTLColor getTransmissionColor() {
		return transmissionColor;
	}
	
	/**
	 * Sets this material's specular exponent.
	 * <p>
	 * This value should generally be a number from
	 * <code>0.0f</code> to <code>1000.0f</code>.
	 * @param specularExponent the specular exponent value
	 */
	public void setSpecularExponent(float specularExponent) {
		this.specularExponent = specularExponent;
	}
	
	/**
	 * Returns this material's specular exponent.
	 * <p>
	 * This value will generally be a number from
	 * <code>0.0f</code> to <code>1000.0f</code>.
	 * @return the specular exponent value
	 */
	public float getSpecularExponent() {
		return specularExponent;
	}
	
	/**
	 * Sets this material's dissolve.
	 * <p>
	 * This can better be considered as this material's
	 * opacity, where a value of <code>0.0f</code> indicates
	 * a full transparency and a value of <code>1.0f</code>
	 * indicates a full opacity.
	 * @param dissolve the dissolve value
	 */
	public void setDissolve(float dissolve) {
		this.dissolve = dissolve;
	}
	
	/**
	 * Returns this material's dissolve.
	 * <p>
	 * This can better be considered as this material's
	 * opacity, where a value of <code>0.0f</code> indicates
	 * a full transparency and a value of <code>1.0f</code>
	 * indicates a full opacity.
	 * @return the dissolve value
	 */
	public float getDissolve() {
		return dissolve;
	}
	
	/**
	 * Sets the ambient texture filename.
	 * <p>
	 * If <code>null</code> is specified, then this
	 * material will not have an ambient texture.
	 * @param filename filename of the ambient texture
	 */
	public void setAmbientTexture(String filename) {
		this.ambientTexture = filename;
	}
	
	/**
	 * Returns this material's ambient texture filename.
	 * <p>
	 * If <code>null</code> is returned, then this material
	 * has no ambient texture.
	 * @return ambient texture filename
	 */
	public String getAmbientTexture() {
		return ambientTexture;
	}
	
	/**
	 * Sets the diffuse texture filename.
	 * <p>
	 * If <code>null</code> is specified, then this
	 * material will not have a diffuse texture.
	 * @param filename filename of the diffuse texture
	 */
	public void setDiffuseTexture(String filename) {
		this.diffuseTexture = filename;
	}
	
	/**
	 * Returns the material's diffuse texture filename.
	 * <p>
	 * If <code>null</code> is returned, then this material
	 * has no diffuse texture.
	 * @return diffuse texture filename
	 */
	public String getDiffuseTexture() {
		return diffuseTexture;
	}
	
	/**
	 * Sets the specular texture filename.
	 * <p>
	 * If <code>null</code> is specified, then this
	 * material will not have a specular texture.
	 * @param filename filename of the specular texture
	 */
	public void setSpecularTexture(String filename) {
		this.specularTexture = filename;
	}
	
	/**
	 * Returns the material's specular texture filename.
	 * <p>
	 * If <code>null</code> is returned, then this material
	 * has no specular texture.
	 * @return specular texture filename
	 */
	public String getSpecularTexture() {
		return specularTexture;
	}
	
	/**
	 * Sets the specular exponent texture filename.
	 * <p>
	 * If <code>null</code> is specified, then this
	 * material will not have a specular exponent texture.
	 * @param filename filename of the specular exponent texture
	 */
	public void setSpecularExponentTexture(String filename) {
		this.specularExponentTexture = filename;
	}
	
	/**
	 * Returns the specular exponent texture filename
	 * <p>
	 * If <code>null</code> is returned, then this material
	 * has no specular exponent texture.
	 * @return specular exponent texture filename
	 */
	public String getSpecularExponentTexture() {
		return specularExponentTexture;
	}
	
	/**
	 * Sets the dissolve texture filename.
	 * <p>
	 * If <code>null</code> is specified, then this
	 * material will not have a dissolve texture.
	 * @param filename filename of the dissolve texture
	 */
	public void setDissolveTexture(String filename) {
		this.dissolveTexture = filename;
	}
	
	/**
	 * Returns the dissolve texture filename.
	 * <p>
	 * If <code>null</code> is returned, then this
	 * material will not have a dissolve texture.
	 * @return dissolve texture filename
	 */
	public String getDissolveTexture() {
		return dissolveTexture;
	}

}
