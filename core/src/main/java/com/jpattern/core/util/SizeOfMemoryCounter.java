package com.jpattern.core.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Stack;

public class SizeOfMemoryCounter {

	private static final MemorySizes sizes = new MemorySizes();
	private Map<Object, Object> visited;
	private Stack<Object> stack;

	SizeOfMemoryCounter() {}
	
	public synchronized long estimate(Object obj) {
		visited = new IdentityHashMap<Object, Object>();
		stack = new Stack<Object>();

		long result = _estimate(obj);
		while (!stack.isEmpty()) {
			result += _estimate(stack.pop());
		}
		visited.clear();
		return result;
	}

	private boolean skipObject(Object obj) {
		if (obj instanceof String) {
			// this will not cause a memory leak since
			// unused interned Strings will be thrown away
			if (obj == ((String) obj).intern()) {
				return true;
			}
		}
		return (obj == null) || visited.containsKey(obj);
	}

	private long _estimate(Object obj) {
		if (skipObject(obj))
			return 0;
		visited.put(obj, null);
		long result = 0;
		Class<?> clazz = obj.getClass();
		if (clazz.isArray()) {
			return _estimateArray(obj);
		}
		while (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (!Modifier.isStatic(fields[i].getModifiers())) {
					if (fields[i].getType().isPrimitive()) {
						result += sizes.getPrimitiveFieldSize(fields[i]
								.getType());
					} else {
						result += sizes.getPointerSize();
						fields[i].setAccessible(true);
						try {
							Object toBeDone = fields[i].get(obj);
							if (toBeDone != null) {
								stack.add(toBeDone);
							}
						} catch (IllegalAccessException ex) {
							assert false;
						}
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		result += sizes.getClassSize();
		return roundUpToNearestEightBytes(result);
	}

	private long roundUpToNearestEightBytes(long result) {
		if ((result % 8) != 0) {
			result += 8 - (result % 8);
		}
		return result;
	}

	protected long _estimateArray(Object obj) {
		long result = 16;
		int length = Array.getLength(obj);
		if (length != 0) {
			Class<?> arrayElementClazz = obj.getClass().getComponentType();
			if (arrayElementClazz.isPrimitive()) {
				result += length
						* sizes.getPrimitiveArrayElementSize(arrayElementClazz);
			} else {
				for (int i = 0; i < length; i++) {
					result += sizes.getPointerSize()
							+ _estimate(Array.get(obj, i));
				}
			}
		}
		return result;
	}

	private static class MemorySizes {

		private static int POINTER_SIZE = 0;

		private final Map<Object, Object> primitiveSizes = new IdentityHashMap<Object, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put(boolean.class, new Integer(1));
				put(byte.class, new Integer(1));
				put(char.class, new Integer(2));
				put(short.class, new Integer(2));
				put(int.class, new Integer(4));
				put(float.class, new Integer(4));
				put(double.class, new Integer(8));
				put(long.class, new Integer(8));
			}
		};

		public int getPrimitiveFieldSize(Class<?> clazz) {
			return ((Integer) primitiveSizes.get(clazz)).intValue();
		}

		public int getPrimitiveArrayElementSize(Class<?> clazz) {
			return getPrimitiveFieldSize(clazz);
		}

		public int getPointerSize() {
			if (POINTER_SIZE == 0) {
				String arch = System.getProperty("os.arch");
				if (arch != null && arch.contains("64")) {
					POINTER_SIZE = 8;
				} else {
					POINTER_SIZE = 4;
				}
			}
			return POINTER_SIZE;
		}

		public int getClassSize() {
			return 8;
		}

	}
}
