package com.jpattern.cache.simple;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jpattern.cache.ACache;

/**
 * 
 * A Cache that holds objects for a fixed maximum time
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public class Cache extends ACache {

	private final Map<Object, ICacheElement> map;
	private final long timeToLiveMilliSeconds;
	public final int maxSize; 
	
	public Cache(String name, int maxSize, long timeToLiveSeconds) {
		super(name);
		this.maxSize = maxSize;
		this.timeToLiveMilliSeconds = timeToLiveSeconds*1000;
		
		map = new LinkedHashMap<Object, ICacheElement>() {
			private static final long serialVersionUID = 1L;
			
            @Override
			protected boolean removeEldestEntry(Map.Entry<Object, ICacheElement> oldest) {
                return size() > Cache.this.maxSize;
            }
			
		};
	}
	
	@Override
	public synchronized Object getValue(Object key) {
		ICacheElement cacheElement = map.get(key);
		if ( cacheElement!=null ) {
			if ( cacheElement.getTimestamp() < new Date().getTime()) {
				remove(key);
				return null;
			}
			return cacheElement.getValue();
		}
		return null;
	}

	@Override
	public synchronized void put(Object key, Object value) {
		map.put(key, new CacheElement(value, new Date().getTime() + timeToLiveMilliSeconds));
	}

	@Override
	public synchronized void remove(Object key) {
		map.remove(key);
	}

	@Override
	public synchronized void clear() {
		map.clear();
	}

	@Override
	public boolean contains(Object key) {
		return map.containsKey(key);
	}
	
	@Override
	public int getSize() {
		return map.size();
	}
	
}
