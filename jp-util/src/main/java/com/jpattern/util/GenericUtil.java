package com.jpattern.util;


/**
 * 
 * @author Francesco Cina' 09/nov/2010 - 08.51.03
 *
 */
public abstract class GenericUtil {

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

	@SuppressWarnings("unchecked")
	public static <E> E castObjectNotNull(Class<E> aClass, Object objectToCast, E defaultValue) {
		if ((objectToCast == null) || !aClass.isInstance(objectToCast)) {
			return defaultValue;
		}
		return (E) objectToCast;
	}

}
