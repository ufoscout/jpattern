package com.jpattern.cache.ehcache;

import com.jpattern.cache.ACache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 May 2011
 */
public class EhCache extends ACache {

	private Ehcache ehcache;

	public EhCache(String name, CacheManager cacheManager) {
		super(name);
		ehcache = cacheManager.getEhcache(name);
	}

	@Override
	public synchronized Object getValue(Object key) {
		if (ehcache!=null) {
			Element element = ehcache.get(key);
	        if (element != null) {
	            return element.getValue();
	        }
		}
        return null;
	}

	@Override
	public synchronized void put(Object key, Object value) {
		if (ehcache!=null) {
			ehcache.put(new Element(key, value));
		}
	}

	@Override
	public synchronized void clear() {
		if (ehcache!=null) {
			ehcache.removeAll();
		}
	}
	
	@Override
	public synchronized void remove(Object key) {
		if (ehcache!=null) {
			ehcache.remove(key);
		}
	}

	@Override
	public boolean contains(Object key) {
		return ( (ehcache!=null) && (ehcache.get(key)!=null) );
	}

	@Override
	public int getSize() {
		return ehcache.getSize();
	}

}
