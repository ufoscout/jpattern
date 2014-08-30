package com.jpattern.shared.util;

import java.io.Serializable;


/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class GenericWrapperSerializable <E extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private E value;
	
	public GenericWrapperSerializable(E value) {
		this.value = value;
	}
	
	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

}
