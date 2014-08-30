package com.jpattern.gwt.cache;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.cache.ICache;
import com.jpattern.gwt.client.cache.PermanentCache;
import com.jpattern.gwt.client.cache.TimedCache;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public class CacheTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPermanentCache() throws Exception {
		ICache cache = new PermanentCache();
		int miss = 0;
		int hits = 0;
		assertEquals( 0 , cache.cacheSize());
		
		assertEquals( hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertNull( cache.get("hello", String.class) );
		assertEquals( hits, cache.getHits() );
		assertEquals( ++miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		cache.put("hello", "firstValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello", "helloValue");
		assertEquals( 1 , cache.cacheSize());
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals( 1 , cache.cacheSize());
	}
	
	public void testTimedCache1() throws Exception {
		int timeToLiveSeconds = 10;
		int checkAllElementsAfterActions = 2;
		ICache cache = new TimedCache(timeToLiveSeconds, checkAllElementsAfterActions);
		
		int miss = 0;
		int hits = 0;
		assertEquals( 0 , cache.cacheSize());
		
		assertEquals( hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertNull( cache.get("hello", String.class) );
		assertEquals( hits, cache.getHits() );
		assertEquals( ++miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		cache.put("hello", "firstValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello", "helloValue");
		assertEquals( 1 , cache.cacheSize());
		
		cache.put("hello2", "helloValue2");
		assertEquals( 2 , cache.cacheSize());
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue", cache.get("hello", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
		assertEquals("helloValue2", cache.get("hello2", String.class));
		assertEquals( ++hits, cache.getHits() );
		assertEquals( miss, cache.getMissing() );
		assertEquals( miss + hits, cache.getTotalcalls() );
		
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
		
		Thread.sleep((timeToLiveSeconds * 1000l) + 10l);
		assertEquals( 0 , cache.cacheSize());
	}

}
