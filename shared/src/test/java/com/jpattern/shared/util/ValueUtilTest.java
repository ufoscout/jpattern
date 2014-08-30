package com.jpattern.shared.util;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.shared.BaseTest;

/**
 * 
 * @author Francesco Cina' 09/nov/2010 - 08.53.03
 *
 */
public class ValueUtilTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testStringNotNull() throws Exception {
		String value = null;
		String defaultValue = "default";
		assertEquals( defaultValue, ValueUtil.stringNotNullTrimmed(value, defaultValue) );
		
		value = " ciao ";
		assertEquals( "ciao", ValueUtil.stringNotNullTrimmed(value, defaultValue) );
		
		assertNull( ValueUtil.stringNotNullTrimmed(null, null) );
	}
	
	public void testObjectNotNull() throws Exception {
		String value = null;
		String defaultValue = "default";
		assertEquals( defaultValue, ValueUtil.objectNotNull(String.class, value, defaultValue) );
		
		value = "ciao";
		assertEquals( value, ValueUtil.objectNotNull(String.class, value, defaultValue) );
	}
	
	public void testObjectNotNull2() throws Exception {
		String value = null;
		String defaultValue = "default";
		assertEquals( defaultValue, ValueUtil.objectNotNull(value, defaultValue) );
		
		value = "ciao";
		assertEquals( value, ValueUtil.objectNotNull(value, defaultValue) );
		
		List<String> listString1 = new ArrayList<String>();
		List<String> listString2 = new ArrayList<String>();
		listString2.add("");
		assertEquals( 1 , ValueUtil.objectNotNull(null, listString2).size() );
		assertEquals( 0 , ValueUtil.objectNotNull(listString1, listString2).size() );
	}
	
}
