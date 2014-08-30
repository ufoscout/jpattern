package com.jpattern.gwt.client.util;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class GenericWrapper <T> {

	private Class<T> objectClass;
	private T value;
	
	public GenericWrapper(Class<T> objectClass) {
		this.objectClass = objectClass;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public Class<T> getWrappedClass() {
		return objectClass;
	}

}
