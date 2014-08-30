package com.jpattern.cache;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina
 *
 * 23 Sep 2011
 */
public class NullCacheManager implements ICacheManager, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ICache getCache(String cacheName) {
		return new NullCache();
	}

	@Override
	public void stopCacheManager() {
	}

}
