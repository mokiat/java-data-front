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

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a material library.
 * <p>
 * A material library can be though of as a
 * single MTL resource which can contain information
 * on multiple materials.
 * 
 * @author Momchil Atanasov
 *
 */
public class MTLLibrary {
	
	private final List<MTLMaterial> materials = new ArrayList<MTLMaterial>();
	
	/**
	 * Creates a new {@link MTLLibrary} instance.
	 */
	public MTLLibrary() {
		super();
	}
	
	/**
	 * Returns a list of materials in this library.
	 * @return non-null writable list of {@link MTLMaterial} 
	 * instances.
	 */
	public List<MTLMaterial> getMaterials() {
		return materials;
	}
	
	/**
	 * A helper method that returns the material in this library
	 * with the specified name.
	 * @param name name of the requested material
	 * @return an instance of {@link MTLMaterial}, or <code>null</code>
	 * if the material could not be found.
	 */
	public MTLMaterial getMaterial(String name) {
		for (MTLMaterial material : materials) {
			if (name.equals(material.getName())) {
				return material;
			}
		}
		return null;
	}

}
