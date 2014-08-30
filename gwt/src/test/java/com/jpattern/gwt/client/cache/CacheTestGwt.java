package com.jpattern.gwt.client.cache;

import java.util.Random;

import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.cache.ICache;
import com.jpattern.gwt.client.cache.PermanentCache;
import com.jpattern.gwt.client.cache.TimedCache;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public class CacheTestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	@Override
	public String getModuleName() {
		return "com.jpattern.gwt.jpattern_tests";
	}
	

	public void testPermanentCache() throws Exception {
		ICache cache = new PermanentCache();
		assertEquals( 0 , cache.cacheSize());
		assertNull( cache.get("hello", String.class) );
		
		cache.put("hello", "firstValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello", "helloValue");
		assertEquals( 1 , cache.cacheSize());
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( 1 , cache.cacheSize());
	}
	
	public void testTimedCache1() throws Exception {
		int timeToLiveSeconds = 10;
		int checkAllElementsAfterActions = 2;
		ICache cache = new TimedCache(timeToLiveSeconds, checkAllElementsAfterActions);
		
		assertEquals( 0 , cache.cacheSize());
		assertNull( cache.get("hello", String.class) );
		
		cache.put("hello", "firstValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello", "helloValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello2", "helloValue2");
		assertEquals( 2 , cache.cacheSize());
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue", cache.get("hello", String.class));
		
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals( 2 , cache.cacheSize());
	}
	
	public void testTimedCache2() throws Exception {
		int timeToLiveSeconds = 1;
		int checkAllElementsAfterActions = 1;
		ICache cache = new TimedCache(timeToLiveSeconds, checkAllElementsAfterActions);
		
		assertEquals( 0 , cache.cacheSize());
		assertNull( cache.get("hello", String.class) );
		
		cache.put("hello", "firstValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello", "helloValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello2", "helloValue2");
		assertEquals( 2 , cache.cacheSize());
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals("helloValue2", cache.get("hello2", String.class));

		assertEquals( 2 , cache.cacheSize());
		
		waitFor((timeToLiveSeconds * 1000l) + 10l);
		
		assertEquals( 0 , cache.cacheSize());
	}
	
	public void testCacheCleanSession() throws Exception {
		String cacheName = "" + new Random().nextLong();
		getProvider().getCacheService().registerCache(cacheName, new PermanentCache());
		
		assertTrue( getProvider().getCacheService().getCacheNames().contains(cacheName) );
		
		ICache cache = getProvider().getCacheService().getCache(cacheName);
		
		cache.put("key", "value");
		assertNotNull( cache.get("key") );
		
		getProvider().getSession().cleanSession();
		
		assertNull( cache.get("key") );
	}

}
