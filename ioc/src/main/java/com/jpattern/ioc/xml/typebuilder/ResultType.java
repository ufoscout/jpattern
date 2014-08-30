package com.jpattern.ioc.xml.typebuilder;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ResultType implements Serializable {

	private static final long serialVersionUID = 1L;

	Object instance = new Object();
	Class<?> classOfInstance = Object.class;
	boolean valid = false;
	
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	
	public Class<?> getClassOfInstance() {
		return classOfInstance;
	}
	public void setClassOfInstance(Class<?> classOfInstance) {
		this.classOfInstance = classOfInstance;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
