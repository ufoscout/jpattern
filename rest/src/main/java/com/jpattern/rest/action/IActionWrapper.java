package com.jpattern.rest.action;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public interface IActionWrapper<T extends IAction> {

	T action();
	
	String relativePath();
	
}
