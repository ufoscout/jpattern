package com.jpattern.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author Francesco Cina' 17/dic/2010 - 17.43.51
 * @version $Id$
 *
 */
public class QueryStringUtilTest extends BaseTest {

	@Test
	public void testMapConversion() throws Exception {

		final Map<String, String> map = new HashMap<String, String>();

		map.put("1", "ciao");
		map.put("2", "?");
		map.put("3", "");
		map.put("4", null);
		map.put("5", "&");
		map.put("6", "=");
		map.put("7", "ciao=mio.ciao");

		final String mapToString = QueryStringUtil.mapToUTF8String(map);

		System.out.println("mapToString = " + mapToString);

		final Map<String, String> stringToMap = QueryStringUtil.stringUTF8ToMap(mapToString);

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
