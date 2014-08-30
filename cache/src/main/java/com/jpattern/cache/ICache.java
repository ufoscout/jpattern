package com.jpattern.cache;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 May 2011
 */
public interface ICache {
	
	<T> T get(Object key, Class<T> clazz);
	
	Object get(Object key);
	
	void put(Object key, Object value);
	
	void remove(Object key);
	
	void clear();

	boolean contains(Object key);
	
	String getName();
	
	int getSize();
	
}
