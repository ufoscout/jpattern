package com.jpattern.gwt.client.cache;

import java.util.List;

import com.jpattern.gwt.client.AService;
import com.jpattern.gwt.client.session.ISessionObserver;

/**
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public abstract class ACacheService extends AService implements ISessionObserver {

	public abstract List<String> getCacheNames();
	
	public abstract ICache getCache(String cacheName);
	
	public abstract void registerCache(String cacheName, ICache cache);
	
	@Override
	public void onSessionClean() {
		for (String cacheName : getCacheNames()) {
			getCache(cacheName).clear();
		}		
	}

	@Override
	public void onAttributeAdded(String key) {
	}

	@Override
	public void onAttributeRemoved(String key) {
	}
}
