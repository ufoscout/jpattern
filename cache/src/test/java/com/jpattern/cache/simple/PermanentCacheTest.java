package com.jpattern.cache.simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpattern.cache.BaseTest;
import com.jpattern.cache.ICache;
import com.jpattern.cache.ICacheManager;

/**
 * 
 * @author Francesco Cina
 *
 * 23 Sep 2011
 */
public class PermanentCacheTest extends BaseTest {

	private static String CACHE_NAME = "query.ObjectShortTermCache";
	private ICacheManager cacheService;
	private int cacheSize = 10;
	
	protected void setUp() throws Exception {
		super.setUp();
		ICache cache = new PermanentCache(CACHE_NAME, cacheSize);
		List<ICache> caches = new ArrayList<ICache>();
		caches.add(cache);
		cacheService = new CacheManager(caches);
		assertNotNull(cacheService);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCache1() throws Exception {
		ICache emptyStringCache = cacheService.getCache("");
		assertNotNull( emptyStringCache );
		assertNull( emptyStringCache.get("hello") );
		assertNull( emptyStringCache.get(null) );
		emptyStringCache.put("key", "value");
		emptyStringCache.put("key", null);
		emptyStringCache.put(null, "value");
		emptyStringCache.put(null, null);
	}
	
	public void testCache2() throws Exception {
		ICache nullStringCache = cacheService.getCache("");
		assertNotNull( nullStringCache );
		assertNull( nullStringCache.get("hello") );
		assertNull( nullStringCache.get(null) );
		nullStringCache.put("key", "value");
		nullStringCache.put("key", null);
		nullStringCache.put(null, "value");
		nullStringCache.put(null, null);
	}
	
	public void testCache3() throws Exception {
		ICache cache = cacheService.getCache(CACHE_NAME);
		assertNotNull( cache );
		String key = "test-key-" + new Date().getTime();
		assertNull( cache.get(key) );
		assertNull( cache.get(null) );
		
		cache.put(key, "value");
		assertNotNull(cache.get(key));
		assertEquals( "value" , (String) cache.get(key) );
		
		cache.clear();
		assertNull(cache.get(key));
		
		cache.put("key", null);
		cache.put(null, "value");
		cache.put(null, null);
		
		cache.clear();
	}
	
	public void testCache4() throws Exception {
		ICache cache = cacheService.getCache(CACHE_NAME);
		assertNotNull( cache );
		String key1 = "test-key1-" + new Date().getTime();
		String key2 = "test-key2-" + new Date().getTime();
		String key3 = "test-key3-" + new Date().getTime();
		assertNull( cache.get(key1) );
		assertNull( cache.get(key2) );
		assertNull( cache.get(key3) );
		
		cache.put(key1, "value1");
		assertNotNull(cache.get(key1));
		assertEquals( "value1" , (String) cache.get(key1) );
		
		cache.put(key2, "value2");
		assertNotNull(cache.get(key2));
		assertEquals( "value2" , (String) cache.get(key2) );
		
		cache.put(key3, "value3");
		assertNotNull(cache.get(key3));
		assertEquals( "value3" , (String) cache.get(key3) );
		
		cache.remove(key2);
		assertNotNull(cache.get(key1));
		assertNull(cache.get(key2));
		assertNotNull(cache.get(key3));
		
		cache.clear();
		assertNull(cache.get(key1));
		assertNull(cache.get(key2));
		assertNull(cache.get(key3));
		cache.clear();
	}
	
	
	public void testCacheSize() {
		ICache cache = cacheService.getCache(CACHE_NAME);
		cache.clear();
		
		String first = "first";
		String second = "second";
		
		cache.put(first, "");
		cache.put(second, "");
		assertNotNull(cache.get(first));
		assertNotNull(cache.get(second));
		
		for (int i=0; i<cacheSize-2; i++) {
			cache.put("key" + i, "");
		}
		
		assertNotNull(cache.get(first));
		assertNotNull(cache.get(second));
		
		cache.put("new-key-1", "");
		assertNull(cache.get(first));
		assertNotNull(cache.get(second));
		
		cache.put("new-key-2", "");
		assertNull(cache.get(first));
		assertNull(cache.get(second));
		
	}
}
