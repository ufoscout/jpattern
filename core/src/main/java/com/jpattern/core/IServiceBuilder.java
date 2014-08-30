package com.jpattern.core;

/**
 * 
 * @author cinafr
 *
 */
public interface IServiceBuilder<T extends IService> {

	T buildService();
	
}
