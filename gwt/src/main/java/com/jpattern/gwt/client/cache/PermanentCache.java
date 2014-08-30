package com.jpattern.gwt.client.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * A Cache that holds forever the objects put in it
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public class PermanentCache implements ICache {

	private Map<String, Object> map = new HashMap<String, Object>();
	private int miss = 0;
	private int hits = 0;
	
	@Override
	public void put(String key, Object value) {
		map.put(key, value);
	}

	@Override
	public Object get(String key) {
		Object result = map.get(key);
		if (result != null) {
			hits++;
		} else {
			miss++;
		}
		return map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, Class<T> clazz) {
		return (T) get(key);
	}

	@Override
	public void remove(String key) {
		map.remove(key);		
	}

	@Override
	public int cacheSize() {
		return map.size();
	}

	@Override
	public int getMissing() {
		return miss;
	}

	@Override
	public int getHits() {
		return hits;
	}

	@Override
	public int getTotalcalls() {
		return miss + hits;
	}

	@Override
	public void clear() {
		map = new HashMap<String, Object>();		
	}

}
