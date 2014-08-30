package com.jpattern.jobexecutor.core;

import com.jpattern.jobexecutor.IBooleanWrapper;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class BooleanWrapper implements IBooleanWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean value = false;
	
	public BooleanWrapper(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
