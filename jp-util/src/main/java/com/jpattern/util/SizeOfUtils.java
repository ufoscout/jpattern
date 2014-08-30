package com.jpattern.util;

/**
 * 
 * @author Francesco Cina'
 *
 * Feb 13, 2012
 */
public abstract class SizeOfUtils {

	/**
	 * Return the estimated size of an object. The object and all is children are analised recursively.
	 * The result is a rough estimation. It is considered that the pointer size in a 32 bit architecture is 4 byte and 
	 * in a 64 bit architecture is 8 byte.
	 * @param obj
	 * @return the estimated byte size of the object
	 */
	public static long sizeOf(Object obj) {
		return new SizeOfMemoryCounter().estimate(obj);
	}
    
}
