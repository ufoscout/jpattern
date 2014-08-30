package com.jpattern.cache.simple;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/set/2011
 */
public class CacheElement implements ICacheElement {

	private static final long serialVersionUID = 1L;
	private final Object value;
	private final long timestamp;
	 
	public CacheElement (Object value, long timestamp) {
		this.value = value;
		this.timestamp = timestamp;
	}
	
	@Override
	public Object getValue() {
		return value;
	}
	@Override
	public long getTimestamp() {
		return timestamp;
	}
	
}
