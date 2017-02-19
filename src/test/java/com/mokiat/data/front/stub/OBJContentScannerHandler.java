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
import com.mokiat.data.front.common.IFastInt;
import com.mokiat.data.front.scanner.IOBJScannerHandler;

public class OBJContentScannerHandler implements IOBJScannerHandler {
	
	private static final float FLOAT_MARGIN = 0.000001f;
	
	private final List<String> comments = new ArrayList<String>();
	
	private final List<Vertex> vertices = new ArrayList<Vertex>();
	private final List<TexCoord> texCoords = new ArrayList<TexCoord>();
	private final List<Normal> normals = new ArrayList<Normal>();
	
	private final List<String> objects = new ArrayList<String>();
	private final List<DataReference> dataReferences = new ArrayList<DataReference>();
	
	private final List<String> materialLibraries = new ArrayList<String>();
	private final List<String> materialReferences = new ArrayList<String>();
	
	private int faceBeginCount = 0;
	private int faceEndCount = 0;
	
	
	public OBJContentScannerHandler() {
		super();
	}
	
	public void assertCommentCount(int count) {
		assertEquals("Wrong comment count.", count, comments.size());
	}
	
	public void assertComment(int index, String value) {
		assertEquals(value, comments.get(index));
	}
	
	public void assertVertexCount(int count) {
		assertEquals("Wrong vertex count.", count, vertices.size());
	}
	
	public void assertVertex(int index, Float x, Float y, Float z, Float w) {
		final Vertex vertex = vertices.get(index);
		assertFloatEquals(x, vertex.x);
		assertFloatEquals(y, vertex.y);
		assertFloatEquals(z, vertex.z);
		assertFloatEquals(w, vertex.w);
	}
	
	public void assertTexCoordCount(int count) {
		assertEquals("Wrong texture coordiante count.", count, texCoords.size());
	}
	
	public void assertTexCoord(int index, Float u, Float v, Float w) {
		final TexCoord texCoord = texCoords.get(index);
		assertFloatEquals(u, texCoord.u);
		assertFloatEquals(v, texCoord.v);
		assertFloatEquals(w, texCoord.w);
	}
	
	public void assertNormalCount(int count) {
		assertEquals("Wrong normal count.", count, normals.size());
	}
	
	public void assertNormal(int index, Float x, Float y, Float z) {
		final Normal normal = normals.get(index);
		assertFloatEquals(x, normal.x);
		assertFloatEquals(y, normal.y);
		assertFloatEquals(z, normal.z);
	}
	
	public void assertObjectCount(int count) {
		assertEquals("Wrong object count.", count, objects.size());
	}
	
	public void assertObject(int index, String name) {
		final String objectName = objects.get(index);
		assertEquals("Wrong object name.", name, objectName);
	}
	
	public void assertFaceBeginCount(int count) {
		assertEquals("Wrong face begin count.", count, faceBeginCount);
	}
	
	public void assertFaceEndCount(int count) {
		assertEquals("Wrong face end count.", count, faceEndCount);
	}
	
	public void assertDataReferencesCount(int count) {
		assertEquals("Wrong data references count.", count, dataReferences.size());
	}
	
	public void assertDataReference(int index, Integer vertexIndex, Integer texCoordIndex, Integer normalIndex) {
		final DataReference reference = dataReferences.get(index);
		assertEquals(vertexIndex, reference.vertex);
		assertEquals(texCoordIndex, reference.texCoord);
		assertEquals(normalIndex, reference.normal);
	}
	
	public void assertMaterialLibraryCount(int count) {
		assertEquals("Wrong material library count.", count, materialLibraries.size());
	}
	
	public void assertMaterialLibrary(int index, String name) {
		final String materialLibrary = materialLibraries.get(index); 
		assertEquals("Wrong material library name.", name, materialLibrary);
	}
	
	public void assertMaterialReferenceCount(int count) {
		assertEquals("Wrong material reference count.", count, materialReferences.size());
	}
	
	public void assertMaterialReference(int index, String name) {
		final String materialReference = materialReferences.get(index);
		assertEquals("Wrong material reference.", name, materialReference);
	}
	
	
	@Override
	public void onComment(String comment) {
		comments.add(comment);
	}

	@Override
	public void onVertex(IFastFloat x, IFastFloat y, IFastFloat z, IFastFloat w) {
		final Vertex vertex = new Vertex();
		vertex.x = (x != null) ? x.get() : null;
		vertex.y = (y != null) ? y.get() : null;
		vertex.z = (z != null) ? z.get() : null;
		vertex.w = (w != null) ? w.get() : null;
		vertices.add(vertex);
	}

	@Override
	public void onNormal(IFastFloat x, IFastFloat y, IFastFloat z) {
		final Normal normal = new Normal();
		normal.x = (x != null) ? x.get() : null;
		normal.y = (y != null) ? y.get() : null;
		normal.z = (z != null) ? z.get() : null;
		normals.add(normal);
	}

	@Override
	public void onTextureCoordinate(IFastFloat u, IFastFloat v, IFastFloat w) {
		final TexCoord texCoord = new TexCoord();
		texCoord.u = (u != null) ? u.get() : null;
		texCoord.v = (v != null) ? v.get() : null;
		texCoord.w = (w != null) ? w.get() : null;
		texCoords.add(texCoord);
	}

	@Override
	public void onObject(String objectName) {
		objects.add(objectName);
	}

	@Override
	public void onFaceBegin() {
		faceBeginCount++;
	}

	@Override
	public void onFaceEnd() {
		faceEndCount++;
	}

	@Override
	public void onDataReference(IFastInt vertexIndex, IFastInt texCoordIndex, IFastInt normalIndex) {
		final DataReference reference = new DataReference();
		reference.vertex = (vertexIndex != null) ? vertexIndex.get() : null;
		reference.texCoord = (texCoordIndex != null) ? texCoordIndex.get() : null;
		reference.normal = (normalIndex != null) ? normalIndex.get() : null;
		dataReferences.add(reference);
	}

	@Override
	public void onMaterialReference(String materialName) {
		materialReferences.add(materialName);
	}

	@Override
	public void onMaterialLibrary(String libraryFilename) {
		materialLibraries.add(libraryFilename);
	}
	
	private static class DataReference {
		public Integer vertex;
		public Integer texCoord;
		public Integer normal;
	}
	
	private static class Vertex {
		public Float x;
		public Float y;
		public Float z;
		public Float w;
	}

	private static class TexCoord {
		public Float u;
		public Float v;
		public Float w;
	}
	
	private static class Normal {
		public Float x;
		public Float y;
		public Float z;
	}


	private void assertFloatEquals(Float expected, Float actual) {
		if (expected == null) {
			assertNull(actual);
		} else {
			assertNotNull(actual);
			assertEquals((float)expected,(float)actual, FLOAT_MARGIN); 
		}
	}
	

}
