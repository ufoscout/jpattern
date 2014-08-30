package com.jpattern.gwt.client.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.util.QueryString;

/**
 * 
 * @author Francesco Cina'
 *
 * 13/mag/2011
 */
public class QueryStringTestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}
	
	public void testQueryStringEmpty() throws Exception {
		Map<String, String> keyValuesMap =  new HashMap<String, String>();
		String queryString = QueryString.toQueryString(keyValuesMap );
		System.out.println("queryString: " + queryString);
		assertEquals("" , queryString);
		
		Map<String, String> generatedMap = QueryString.toMap(queryString);
		mapEquals(keyValuesMap, generatedMap);
	}
	
	public void testQueryString() throws Exception {
		
		Map<String, String> keyValuesMap =  new HashMap<String, String>();
		keyValuesMap.put("key1", "value#1");
		keyValuesMap.put("key2", "value2?");
		keyValuesMap.put("key  ? 3", "value+?");
		String queryString = QueryString.toQueryString(keyValuesMap );
		
		System.out.println("queryString: " + queryString);
		
		assertTrue(queryString.startsWith("?"));
		assertTrue(queryString.lastIndexOf(" ")<0);
		assertTrue(queryString.substring(1).lastIndexOf("?")<0);
		
		Map<String, String> generatedMap = QueryString.toMap(queryString);
		mapEquals(keyValuesMap, generatedMap);
		
		Map<String, String> generatedMap2 = QueryString.toMap("http://www.jpattern.com/index.html#historyToken1_historyToken2_" + queryString);
		mapEquals(keyValuesMap, generatedMap2);
		
	}
	
	public void testQueryStringtimestamp() throws Exception {
		String queryString = QueryString.addTimestampQueryToUrl( "hello" );
		System.out.println("queryString: " + queryString);
		assertTrue( queryString.contains( "hello?_ts=" )  );
		
		String queryString2 = QueryString.addTimestampQueryToUrl( "hello?param1=2" );
		System.out.println("queryString: " + queryString2);
		assertTrue( queryString2.contains( "hello?param1=2&_ts=" )  );
		
		String queryString3 = QueryString.addTimestampQueryToUrl( "hello?param1=" );
		System.out.println("queryString: " + queryString3);
		assertTrue( queryString3.contains( "hello?param1=&_ts=" )  );
	}
	
	private void mapEquals(Map<String, String> map1, Map<String, String> map2) {
		
		assertEquals(map1.size(), map2.size());
		
		System.out.println("Map1 entries:");
		for (Entry<String, String> entry : map1.entrySet()) {
			System.out.println("key: [" + entry.getKey() + "] - value: [" + entry.getValue() + "]");
		}
		
		System.out.println("Map2 entries:");
		for (Entry<String, String> entry : map2.entrySet()) {
			System.out.println("key: [" + entry.getKey() + "] - value: [" + entry.getValue() + "]");
		}
		
		for (Entry<String, String> entry1 : map1.entrySet()) {
			System.out.println("checking entry [" + entry1.getKey() + "] - value [" + entry1.getValue() + "]");
			assertTrue( map2.containsKey(entry1.getKey()) );
			assertEquals( entry1.getValue(), map2.get(entry1.getKey()) );
		}
		
	}

}
