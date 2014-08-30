package com.jpattern.cache;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 May 2011
 */
public class NullCache implements ICache {

	@Override
	public Serializable get(Object key) {
		return null;
	}

	@Override
	public void put(Object key, Object value) {
	}

	@Override
	public void clear() {
	}

	@Override
	public void remove(Object key) {
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public <T> T get(Object key, Class<T> clazz) {
		return null;
	}

	@Override
	public boolean contains(Object key) {
		return false;
	}

	@Override
	public int getSize() {
		return 0;
	}

}
