package com.jpattern.gwt.client.util;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public abstract class ClassName {

	/**
	 * Return the SimpleName of a Class as the getClass().getSimpleName() is not provided by GWT
	 * @param clazz
	 * @return
	 */
	public static String getSimpleClassName(Class<?> clazz) {
		String name = clazz.getName();
		return name.substring( name.lastIndexOf(".")+1 );
	}
}
