package com.jpattern.cache.ehcache;

import java.util.Date;

import net.sf.ehcache.CacheManager;

import com.jpattern.cache.BaseTest;
import com.jpattern.cache.ICache;
import com.jpattern.cache.ICacheManager;

/**
 * 
 * @author Francesco Cina'
 *
 * 2 May 2011
 */
public class EhCacheServiceTest extends BaseTest {

	private static String CACHE_NAME = "query.ObjectShortTermCache";
	private ICacheManager cacheService;

	protected void setUp() throws Exception {
		super.setUp();
		CacheManager cacheManager = new CacheManager(getClass().getResource("/test-ehcache.xml"));
		cacheService = new EhCacheManager(cacheManager);
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
		assertFalse( emptyStringCache.contains("key") );
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
		assertFalse( cache.contains(key) );
		
		cache.put(key, "value");
		assertTrue( cache.contains(key) );
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
}
