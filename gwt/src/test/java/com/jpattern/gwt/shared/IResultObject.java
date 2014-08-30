package com.jpattern.gwt.shared;

import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public interface IResultObject {

	boolean isValid();
	
	String getName();
	
	List<String> getMessages();
	
}
