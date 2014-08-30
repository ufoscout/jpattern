package com.jpattern.gwt.client.property;

import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public interface IProperty {

	List<String> getKeys();
	
	
	String getProperty(String key);


	boolean containsKey(String key);
}
