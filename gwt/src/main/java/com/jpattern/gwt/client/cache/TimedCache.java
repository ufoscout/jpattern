package com.jpattern.gwt.client.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * A Cache that holds objects for a fixed maximum time.
 * 
 * @author Francesco Cina
 *
 * 26/lug/2011
 */
public class TimedCache implements ICache {

	private Map<String, Object> map = new HashMap<String, Object>();
	private Map<String, Long> timestampMap = new HashMap<String, Long>();
	private final long timeToLiveMilliSeconds;
	private final int checkAllElementsAfterActions;
	private int performedActions = 0;
	private int miss = 0;
	private int hits = 0;
	
	/**
	 * 
	 * @param timeToLiveSeconds the validity time of an object in the cache
	 * @param checkAllElementsAfterActions indicate to control the validity of all the elements in the cache after n executed actions  
	 */
	public TimedCache(int timeToLiveSeconds, int checkAllElementsAfterActions) {
		this.checkAllElementsAfterActions = checkAllElementsAfterActions;
		this.timeToLiveMilliSeconds = (((long)timeToLiveSeconds) * 1000l);
	}
	
	@Override
	public void put(String key, Object value) {
		checkAllElementsValidity();
		map.put(key, value);
		timestampMap.put(key, new Date().getTime() + timeToLiveMilliSeconds);
	}

	@Override
	public Object get(String key) {
		checkAllElementsValidity();
		checkElementValidity(key);
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
		checkAllElementsValidity();
		timestampMap.remove(key);
		map.remove(key);	
	}
	
	@Override
	public int cacheSize() {
		checkAllElementsValidity();
		return map.size();
	}
	
	private void checkAllElementsValidity() {
		if (performedActions++ > checkAllElementsAfterActions) {
			performedActions = 0;
			long currentTime = new Date().getTime();
			for (Iterator<Entry<String, Long>> i = timestampMap.entrySet().iterator(); i.hasNext();) {
				Entry<String, Long> entry = i.next();
				if (entry.getValue() < currentTime) {
					map.remove(entry.getKey());
					i.remove();
				}
			}

		}
	}
	
	private void checkElementValidity(String key) {
		Long timestamp = timestampMap.get(key);
		if ( (timestamp!=null) && (timestamp < new Date().getTime())) {
			remove(key);
		}
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
		timestampMap = new HashMap<String, Long>();
	}
	
}
