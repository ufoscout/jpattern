package com.jpattern.cache.simple;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jpattern.cache.ACache;

/**
 * A simple fixed size cache.
 * @author Francesco Cina
 *
 * 23 Sep 2011
 */
public class PermanentCache extends ACache {

	private Map<Object, Object> map;
	public final int maxSize; 
	
	public PermanentCache(String name, int maxSize) {
		super(name);
		this.maxSize = maxSize;
		
		map = new LinkedHashMap<Object, Object>() {
			private static final long serialVersionUID = 1L;
			
            @Override
			protected boolean removeEldestEntry(Map.Entry<Object, Object> oldest) {
                return size() > PermanentCache.this.maxSize;
            }
			
		};
	}
	
	@Override
	public synchronized Object getValue(Object key) {
		return map.get(key);
	}

	@Override
	public synchronized void put(Object key, Object value) {
		map.put(key, value);
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
