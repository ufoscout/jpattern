package com.jpattern.gwt.client.cache;

/**
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public class NullCache implements ICache {

	@Override
	public void put(String key, Object value) {
	}

	@Override
	public void remove(String key) {
	}

	@Override
	public Object get(String key) {
		return null;
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		return null;
	}

	@Override
	public int cacheSize() {
		return 0;
	}

	@Override
	public int getMissing() {
		return 0;
	}

	@Override
	public int getHits() {
		return 0;
	}

	@Override
	public int getTotalcalls() {
		return 0;
	}

	@Override
	public void clear() {
	}

}
