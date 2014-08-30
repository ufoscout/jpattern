package com.jpattern.cache.simple;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.cache.ICache;
import com.jpattern.cache.ICacheManager;
import com.jpattern.cache.NullCache;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/set/2011
 */
public class CacheManager implements ICacheManager, Serializable {

	private static final long serialVersionUID = 1L;
	private final Map<String, ICache> cachesMap = new HashMap<String, ICache>();

	public CacheManager(List<ICache> caches) {
		for (ICache cache : caches) {
			cachesMap.put(cache.getName(), cache);
		}
	}

	@Override
	public ICache getCache(String cacheName) {
		if (cachesMap.containsKey(cacheName)) {
			return cachesMap.get(cacheName);
		}
		return new NullCache();
	}

	@Override
	public void stopCacheManager() {
		cachesMap.clear();		
	}

}
