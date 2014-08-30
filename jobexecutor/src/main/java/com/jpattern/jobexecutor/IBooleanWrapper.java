package com.jpattern.jobexecutor;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public interface IBooleanWrapper extends Serializable {
	
	void setValue(boolean value);
	
	boolean getValue();
	
}
