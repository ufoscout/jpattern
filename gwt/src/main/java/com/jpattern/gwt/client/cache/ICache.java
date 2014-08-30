package com.jpattern.gwt.client.cache;

/**
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public interface ICache {

	/**
	 * empty the cache
	 */
	void clear();
	
	/**
	 * Put a new object in the cache
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);
	
	/**
	 * Remove an object from the cache
	 * @param key
	 */
	void remove(String key);
	
	/**
	 * Return the object associated to the key if exists, null otherwise 
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * Return the object associated to the key if exists, null otherwise 
	 * @param key
	 * @return
	 */
	<T> T get(String key, Class<T> clazz);
	
	/**
	 * return the total number of objects hold by the cache
	 * @return
	 */
	int cacheSize();
	
	/**
	 * return the total number of times an object has not be found in the cache
	 * @return
	 */
	int getMissing();
	
	/**
	 * return the total number of times an object has not be found in the cache
	 * @return
	 */
	int getHits();
	
	/**
	 * return the total number of request to get an object from the cache
	 * @return
	 */
	int getTotalcalls();
}
