package com.jpattern.core.util;


/**
 * 
 * @author Francesco Cina' 09/nov/2010 - 08.51.03
 *
 */
public abstract class ValueUtil extends com.jpattern.shared.util.ValueUtil {

	@SuppressWarnings("unchecked")
	public static <E> E castObjectNotNull(Class<E> aClass, Object objectToCast, E defaultValue) {
		if (objectToCast == null || !aClass.isInstance(objectToCast)) {
			return defaultValue;
		}
		return (E) objectToCast;
	}
	
}
