package com.jpattern.util;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Francesco Cina' 14/dic/2010 - 15.13.02
 * @version $Id$ 
 *
 */
public abstract class CollectionUtil {

	public static <T> List<T> toList(T[] aArray) {
		return Arrays.asList(aArray);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Class<T> aClass, Collection<? extends T> aList) {
		return aList.toArray( (T[]) Array.newInstance(aClass, aList.size()) );
	}
	
}
