package com.jpattern.core.util;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.core.util.ValueUtilExt;
import com.jpattern.core.BaseTest;

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
		assertEquals( defaultValue, ValueUtilExt.stringNotNullTrimmed(value, defaultValue) );
		
		value = " ciao ";
		assertEquals( "ciao", ValueUtilExt.stringNotNullTrimmed(value, defaultValue) );
		
		assertNull( ValueUtilExt.stringNotNullTrimmed(null, null) );
	}
	
	public void testObjectNotNull() throws Exception {
		String value = null;
		String defaultValue = "default";
		assertEquals( defaultValue, ValueUtilExt.objectNotNull(String.class, value, defaultValue) );
		
		value = "ciao";
		assertEquals( value, ValueUtilExt.objectNotNull(String.class, value, defaultValue) );
	}
	
	public void testObjectNotNull2() throws Exception {
		String value = null;
		String defaultValue = "default";
		assertEquals( defaultValue, ValueUtilExt.objectNotNull(value, defaultValue) );
		
		value = "ciao";
		assertEquals( value, ValueUtilExt.objectNotNull(value, defaultValue) );
		
		List<String> listString1 = new ArrayList<String>();
		List<String> listString2 = new ArrayList<String>();
		listString2.add("");
		assertEquals( 1 , ValueUtilExt.objectNotNull(null, listString2).size() );
		assertEquals( 0 , ValueUtilExt.objectNotNull(listString1, listString2).size() );
	}
	
	public void testCastObjectNotNull1() throws Exception {
		Object value = null;
		Integer defaultValue = Integer.valueOf(11);
		assertEquals( defaultValue, ValueUtilExt.castObjectNotNull(Integer.class, value, defaultValue) );
		
		value = "ciao";
		assertEquals( defaultValue, ValueUtilExt.castObjectNotNull(Integer.class, value, defaultValue) );
		
		value = Long.valueOf(11);
		assertEquals( defaultValue, ValueUtilExt.castObjectNotNull(Integer.class, value, defaultValue) );
		
		value = Integer.valueOf(22);
		assertEquals( value, ValueUtilExt.castObjectNotNull(Integer.class, value, defaultValue) );
	}
	
	public void testCastObjectNotNull2() throws Exception {
		Object value = null;
		Integer defaultValue = Integer.valueOf(11);
		assertEquals( defaultValue, ValueUtilExt.castObjectNotNull(Number.class, value, defaultValue) );
		
		value = "ciao";
		assertEquals( defaultValue, ValueUtilExt.castObjectNotNull(Number.class, value, defaultValue) );
		
		value = Long.valueOf(11);
		assertEquals( value, ValueUtilExt.castObjectNotNull(Number.class, value, defaultValue) );
		
		value = Integer.valueOf(22);
		assertEquals( value, ValueUtilExt.castObjectNotNull(Number.class, value, defaultValue) );
	}
}
