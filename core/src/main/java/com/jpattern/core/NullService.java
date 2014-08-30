package com.jpattern.core;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/apr/2010
 */
public class NullService implements IService, Serializable {

	private static final long serialVersionUID = 1L;

	public String getName() {
		return "";
	}

	@Override
	public void stopService() {
	}

}
