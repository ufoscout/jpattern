package com.jpattern.gwt.client.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public class CacheService extends ACacheService {

	private Map<String, ICache> cacheMap = new HashMap<String, ICache>();
	
	@Override
	public ICache getCache(String cacheName) {
		if (cacheMap.containsKey(cacheName)) {
			return cacheMap.get(cacheName);
		}
		return new NullCache();
	}

	@Override
	public void registerCache(String cacheName, ICache cache) {
		cacheMap.put(cacheName, cache);
	}

	@Override
	public List<String> getCacheNames() {
		List<String> cacheNames = new ArrayList<String>();
		for (Entry<String, ICache> entry : cacheMap.entrySet()) {
			cacheNames.add(entry.getKey());
		}
		return cacheNames;
	}

}
