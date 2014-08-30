package com.jpattern.shared.util;


/**
 * 
 * @author Francesco Cina' 09/nov/2010 - 08.51.03
 *
 */
public class ValueUtil {

	public static String stringNotNull(final String value, final String defaultValue) {
		return objectNotNull(String.class, value, defaultValue);
	}
	
	public static String stringNotNullTrimmed(final String value, final String defaultValue) {
		String result = stringNotNull(value, defaultValue);
		if ( result != null) {
			result = result.trim();
		}
		return result;
	}

	public static <E> E objectNotNull( final E value, final E defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}
	
	public static <E> E objectNotNull(final Class<E> aClass, final E value, final E defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

}
