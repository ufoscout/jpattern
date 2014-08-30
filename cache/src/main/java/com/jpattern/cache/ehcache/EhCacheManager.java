package com.jpattern.cache.ehcache;

import com.jpattern.cache.ICache;
import com.jpattern.cache.ICacheManager;

import net.sf.ehcache.CacheManager;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 May 2011
 */
public class EhCacheManager implements ICacheManager {

	private final CacheManager cacheManager;

	public EhCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	@Override
	public ICache getCache(String cacheName) {
		return new EhCache(cacheName, cacheManager);
	}

	@Override
	public void stopCacheManager() {
		cacheManager.shutdown();		
	}

}
