package com.jpattern.core.util;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.core.util.MapUtil;
import com.jpattern.core.BaseTest;

/**
 * 
 * @author Francesco Cina' 17/dic/2010 - 17.43.51
 * @version $Id$ 
 *
 */
public class MapUtilTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMapConversion() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("1", "ciao");
		map.put("2", "?");
		map.put("3", "");
		map.put("4", null);
		map.put("5", "&");
		map.put("6", "=");
		map.put("7", "ciao=mio.ciao");
		
		String mapToString = MapUtil.mapToUTF8String(map);
		
		System.out.println("mapToString = " + mapToString);
		
		Map<String, String> stringToMap = MapUtil.stringUTF8ToMap(mapToString);
		
		System.out.println("stringToMap.size() = " + stringToMap.size());
		assertEquals( map.size() , stringToMap.size() );
		
		assertEquals( "ciao" , stringToMap.get("1") );
		assertEquals( "?" , stringToMap.get("2") );
		assertEquals( "" , stringToMap.get("3") );
		assertEquals( "" , stringToMap.get("4") );
		assertEquals( "&" , stringToMap.get("5") );
		assertEquals( "=" , stringToMap.get("6") );
		assertEquals( "ciao=mio.ciao" , stringToMap.get("7") );
		
	}
	
}
