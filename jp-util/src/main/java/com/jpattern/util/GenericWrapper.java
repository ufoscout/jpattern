package com.jpattern.util;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class GenericWrapper <E> {

	private E value;
	
	public GenericWrapper(E value) {
		this.value = value;
	}
	
	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

}
