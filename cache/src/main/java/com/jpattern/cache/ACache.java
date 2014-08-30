package com.jpattern.cache;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/set/2011
 */
public abstract class ACache implements ICache {

	private final String name;

	public ACache(String name) {
		this.name = name;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final synchronized <T> T get(Object key, Class<T> clazz) {
		return (T) get(key);
	}
	
	@Override
	public final synchronized Object get(Object key) {
		return getValue(key);
	}
	
	protected abstract Object getValue(Object key);

	@Override
	public String getName() {
		return name;
	}

}
