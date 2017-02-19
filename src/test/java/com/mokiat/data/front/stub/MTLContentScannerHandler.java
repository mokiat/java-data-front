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

package com.mokiat.data.front.stub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.mokiat.data.front.common.IFastFloat;
import com.mokiat.data.front.error.WFException;
import com.mokiat.data.front.scanner.IMTLScannerHandler;

public class MTLContentScannerHandler implements IMTLScannerHandler {
	
	private static final float FLOAT_MARGIN = 0.000001f;
	
	private final List<String> comments = new ArrayList<String>();
	private final List<String> materials = new ArrayList<String>();
	private final List<ColorRGB> ambientColorsRGB = new ArrayList<ColorRGB>();
	private final List<ColorRGB> diffuseColorsRGB = new ArrayList<ColorRGB>();
	private final List<ColorRGB> specularColorsRGB = new ArrayList<ColorRGB>();
	private final List<ColorRGB> transmissionColorsRGB = new ArrayList<ColorRGB>();
	private final List<Float> dissolves = new ArrayList<Float>();
	private final List<Float> specularExponents = new ArrayList<Float>();
	private final List<String> ambientTextures = new ArrayList<String>();
	private final List<String> diffuseTextures = new ArrayList<String>();
	private final List<String> specularTextures = new ArrayList<String>();
	private final List<String> specularExponentTextures = new ArrayList<String>();
	private final List<String> dissolveTextures = new ArrayList<String>();

	
	public void assertCommentCount(int count) {
		assertEquals("Wrong comment count.", count, comments.size());
	}

	public void assertComment(int index, String comment) {
		assertEquals("Wrong comment.", comment, comments.get(index));
	}
	
	public void assertMaterialCount(int count) {
		assertEquals("Wrong material count.", count, materials.size());
	}

	public void assertMaterial(int index, String name) {
		assertEquals("Wrong material.", name, materials.get(index));
	}
	
	public void assertAmbientColorRGBCount(int count) {
		assertEquals("Wrong ambient RGB color count.", count, ambientColorsRGB.size());
	}

	public void assertAmbientColorRGB(int index, float r, float g, float b) {
		final ColorRGB color = ambientColorsRGB.get(index);
		assertFloatEquals(r, color.r);
		assertFloatEquals(g, color.g);
		assertFloatEquals(b, color.b);
	}
	
	public void assertDiffuseColorRGBCount(int count) {
		assertEquals("Wrong diffuse RGB color count.", count, diffuseColorsRGB.size());
	}

	public void assertDiffuseColorRGB(int index, float r, float g, float b) {
		final ColorRGB color = diffuseColorsRGB.get(index);
		assertFloatEquals(r, color.r);
		assertFloatEquals(g, color.g);
		assertFloatEquals(b, color.b);
	}
	
	public void assertSpecularColorRGBCount(int count) {
		assertEquals("Wrong specular RGB color count.", count, specularColorsRGB.size());
	}

	public void assertSpecularColorRGB(int index, float r, float g, float b) {
		final ColorRGB color = specularColorsRGB.get(index);
		assertFloatEquals(r, color.r);
		assertFloatEquals(g, color.g);
		assertFloatEquals(b, color.b);
	}
	
	public void assertTransmissionColorRGBCount(int count) {
		assertEquals("Wrong transmission RGB color count.", count, transmissionColorsRGB.size());
	}

	public void assertTransmissionColorRGB(int index, float r, float g, float b) {
		final ColorRGB color = transmissionColorsRGB.get(index);
		assertFloatEquals(r, color.r);
		assertFloatEquals(g, color.g);
		assertFloatEquals(b, color.b);
	}
	
	public void assertDissolveCount(int count) {
		assertEquals("Wrong dissolve count.", count, dissolves.size());
	}
	
	public void assertDissolve(int index, float amount) {
		assertFloatEquals(amount, dissolves.get(index));
	}
	
	public void assertSpecularExponentCount(int count) {
		assertEquals("Wrong specular exponent count.", count, specularExponents.size());
	}
	
	public void assertSpecularExponent(int index, float amount) {
		assertFloatEquals(amount, specularExponents.get(index));
	}
	
	public void assertAmbientTextureCount(int count) {
		assertEquals("Wrong ambient texture count.", count, ambientTextures.size());
	}
	
	public void assertAmbientTexture(int index, String filename) {
		assertEquals("Wrong ambient texture filename.", filename, ambientTextures.get(index));
	}
	
	public void assertDiffuseTextureCount(int count) {
		assertEquals("Wrong diffuse texture count.", count, diffuseTextures.size());
	}
	
	public void assertDiffuseTexture(int index, String filename) {
		assertEquals("Wrong diffuse texture filename.", filename, diffuseTextures.get(index));
	}

	public void assertSpecularTextureCount(int count) {
		assertEquals("Wrong specular texture count.", count, specularTextures.size());
	}
	
	public void assertSpecularTexture(int index, String filename) {
		assertEquals("Wrong specular texture filename.", filename, specularTextures.get(index));
	}
	
	public void assertSpecularExponentTextureCount(int count) {
		assertEquals("Wrong specular exponent texture count.", count, specularExponentTextures.size());
	}
	
	public void assertSpecularExponentTexture(int index, String filename) {
		assertEquals("Wrong specular exponent texture filename.", filename, specularExponentTextures.get(index));
	}
	
	public void assertDissolveTextureCount(int count) {
		assertEquals("Wrong dissolve texture count.", count, dissolveTextures.size());
	}
	
	public void assertDissolveTexture(int index, String filename) {
		assertEquals("Wrong dissolve texture filename.", filename, dissolveTextures.get(index));
	}
	
	
	@Override
	public void onComment(String comment) throws WFException {
		comments.add(comment);
	}
	
	@Override
	public void onMaterial(String name) throws WFException {
		materials.add(name);
	}
	
	@Override
	public void onAmbientColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) {
		ambientColorsRGB.add(createColorRGB(r, g, b));
	}
	
	@Override
	public void onDiffuseColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) {
		diffuseColorsRGB.add(createColorRGB(r, g, b));
	}

	@Override
	public void onSpecularColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) {
		specularColorsRGB.add(createColorRGB(r, g, b));
	}
	
	@Override
	public void onTransmissionColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) {
		transmissionColorsRGB.add(createColorRGB(r, g, b));
	}

	@Override
	public void onDissolve(IFastFloat amount) {
		dissolves.add((amount != null) ? amount.get() : null);
	}
	
	@Override
	public void onSpecularExponent(IFastFloat amount) {
		specularExponents.add((amount != null) ? amount.get() : null);
	}
	
	@Override
	public void onAmbientTexture(String filename) throws WFException {
		ambientTextures.add(filename);
	}
	
	@Override
	public void onDiffuseTexture(String filename) throws WFException {
		diffuseTextures.add(filename);
	}
	
	@Override
	public void onSpecularTexture(String filename) throws WFException {
		specularTextures.add(filename);
	}
	
	@Override
	public void onSpecularExponentTexture(String filename) throws WFException {
		specularExponentTextures.add(filename);
	}
	
	@Override
	public void onDissolveTexture(String filename) throws WFException {
		dissolveTextures.add(filename);
	}
	
	
	private ColorRGB createColorRGB(IFastFloat r, IFastFloat g, IFastFloat b) {
		final ColorRGB color = new ColorRGB();
		color.r = (r != null) ? r.get() : null;
		color.g = (g != null) ? g.get() : null;
		color.b = (b != null) ? b.get() : null;
		return color;
	}
	
	private void assertFloatEquals(Float expected, Float actual) {
		if (expected == null) {
			assertNull(actual);
		} else {
			assertNotNull(actual);
			assertEquals((float)expected,(float)actual, FLOAT_MARGIN); 
		}
	}
	
	
	private static class ColorRGB {
		public Float r;
		public Float g;
		public Float b;
	}



}
