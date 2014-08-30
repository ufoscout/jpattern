package com.jpattern.cache;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 May 2011
 */
public interface ICacheManager {

	ICache getCache(String cacheName);

	void stopCacheManager();
	
}
