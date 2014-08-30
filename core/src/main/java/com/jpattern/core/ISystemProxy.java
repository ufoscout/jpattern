package com.jpattern.core;

import com.jpattern.core.IProvider;
import com.jpattern.core.ISystem;

/**
 * 
 * @author Francesco Cina'
 *
 * 29 Mar 2011
 */
public interface ISystemProxy<T extends IProvider> {

	T getProvider();

	ISystem getSystem();
	
}
