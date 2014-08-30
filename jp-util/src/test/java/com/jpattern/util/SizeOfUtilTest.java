package com.jpattern.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author cinafr
 * 
 */
public class SizeOfUtilTest extends BaseTest {

	private static final SizeOfMemoryCounter mc = new SizeOfMemoryCounter();

	@Test
	public void testString() {
		assertTrue( mc.estimate(new String("Hello World!")) >= 64 );
	}

	@Test
	public void testIntegerToString() {
		for (int i = 0; i < 1; i++) {
			assertEquals(42, mc.estimate("" + i));
		}
	}

	@Test
	public void testHashMap() {
		assertTrue( mc.estimate(new HashMap<Object, Object>()) >= 120);

		final Byte[] all = new Byte[256];
		for (int i = -128; i < 128; i++) {
			all[i + 128] = new Byte((byte) i);
		}
		assertTrue(mc.estimate(all) >= 5136);

		final HashMap<Object, Object> hm = new HashMap<Object, Object>();
		for (int i = -128; i < 128; i++) {
			hm.put("" + i, new Byte((byte) i));
		}
		assertEquals( 23940, mc.estimate(hm) );
	}

	@Test
	public void testVector() {
		assertEquals(80, mc.estimate(new Vector<Object>(10)));
	}

	@Test
	public void testObject() {
		assertEquals(8, mc.estimate(new Object()));
	}

	@Test
	public void testInteger() {
		assertEquals(16, mc.estimate(new Integer(1)));
	}

	@Test
	public void testCharArray() {
		assertEquals(40, mc.estimate("Hello World!".toCharArray()));
	}

	@Test
	public void testByte() {
		assertEquals(16, mc.estimate(new Byte((byte) 10)));
	}

	@Test
	public void testThreeBytes() {
		assertEquals(16, mc.estimate(new ThreeBytes()));
	}

	@Test
	public void testSixtyFourBooleans() {
		assertEquals(72, mc.estimate(new SixtyFourBooleans()));
	}

	@Test
	public void testThousandBooleansObjects() {
		final Boolean[] booleans = new Boolean[1000];

		for (int i = 0; i < booleans.length; i++) {
			booleans[i] = new Boolean(true);
		}

		assertEquals(20016, mc.estimate(booleans));
	}

	@Test
	public void testThousandBytes() {
		assertEquals(1016, mc.estimate(new byte[1000]));
	}

	@Test
	public void testEmptyArrayList() {
		assertEquals(80, mc.estimate(new ArrayList<Object>()));
	}

	@Test
	public void testFullArrayList() {
		final ArrayList<Object> arrayList = new ArrayList<Object>(10000);

		for (int i = 0; i < 10000; i++) {
			arrayList.add(new Object());
		}

		assertEquals(120040, mc.estimate(arrayList));
	}

	@Test
	public void testFullLinkedList() {
		final LinkedList<Object> linkedList = new LinkedList<Object>();

		for (int i = 0; i < 10000; i++) {
			linkedList.add(new Object());
		}

		assertEquals(320048, mc.estimate(linkedList));
	}

	@Test
	public void testComplexClass() {
		assertEquals(48, mc.estimate(new ComplexClass()));
	}

	@Test
	public void testBooleanArray() {
		assertEquals(27, mc.estimate(new boolean[11]));
	}

	@Test
	public void testShortArray() {
		assertEquals(38, mc.estimate(new short[11]));
	}

	@Test
	public void testIntArray() {
		assertEquals(60, mc.estimate(new int[11]));
	}

	@Test
	public void testFloatArray() {
		assertEquals(60, mc.estimate(new float[11]));
	}

	@Test
	public void testLongArray() {
		assertEquals(104, mc.estimate(new long[11]));
	}

	@Test
	public void testDoubleArray() {
		assertEquals(104, mc.estimate(new double[11]));
	}

	static class ThreeBytes {
		byte b0;
		byte b1;
		byte b2;
	}

	@SuppressWarnings("unused")
	private static class ComplexClass {
		ComplexClass cc = this;
		boolean z;
		byte b;
		char c;
		double d;
		float f;
		int i;
		long l;
		short s;
	}

	@SuppressWarnings("unused")
	private static class SixtyFourBooleans {
		boolean a0;
		boolean a1;
		boolean a2;
		boolean a3;
		boolean a4;
		boolean a5;
		boolean a6;
		boolean a7;
		boolean b0;
		boolean b1;
		boolean b2;
		boolean b3;
		boolean b4;
		boolean b5;
		boolean b6;
		boolean b7;
		boolean c0;
		boolean c1;
		boolean c2;
		boolean c3;
		boolean c4;
		boolean c5;
		boolean c6;
		boolean c7;
		boolean d0;
		boolean d1;
		boolean d2;
		boolean d3;
		boolean d4;
		boolean d5;
		boolean d6;
		boolean d7;
		boolean e0;
		boolean e1;
		boolean e2;
		boolean e3;
		boolean e4;
		boolean e5;
		boolean e6;
		boolean e7;
		boolean f0;
		boolean f1;
		boolean f2;
		boolean f3;
		boolean f4;
		boolean f5;
		boolean f6;
		boolean f7;
		boolean g0;
		boolean g1;
		boolean g2;
		boolean g3;
		boolean g4;
		boolean g5;
		boolean g6;
		boolean g7;
		boolean h0;
		boolean h1;
		boolean h2;
		boolean h3;
		boolean h4;
		boolean h5;
		boolean h6;
		boolean h7;
	}

	//	static class Entry implements Map.Entry<Object, Object> {
	//		final Object key;
	//		Object value;
	//		final int hash;
	//		Entry next;
	//
	//		Entry(int h, Object k, Object v, Entry n) {
	//			value = v;
	//			next = n;
	//			key = k;
	//			hash = h;
	//		}
	//
	//		@Override
	//		public Object getKey() {
	//			return key;
	//		}
	//
	//		@Override
	//		public Object getValue() {
	//			return value;
	//		}
	//
	//		@Override
	//		public Object setValue(Object value) {
	//			return value;
	//		}
	//	}
}
