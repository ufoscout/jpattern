package com.jpattern.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author Francesco Cina' 09/nov/2010 - 08.53.03
 *
 */
public class GenericUtilTest extends BaseTest {

	@Test
	public void testObjectNotNull() throws Exception {
		String value = null;
		final String defaultValue = "default";
		assertEquals( defaultValue, GenericUtil.objectNotNull(String.class, value, defaultValue) );

		value = "ciao";
		assertEquals( value, GenericUtil.objectNotNull(String.class, value, defaultValue) );
	}

	@Test
	public void testObjectNotNull2() throws Exception {
		String value = null;
		final String defaultValue = "default";
		assertEquals( defaultValue, GenericUtil.objectNotNull(value, defaultValue) );

		value = "ciao";
		assertEquals( value, GenericUtil.objectNotNull(value, defaultValue) );

		final List<String> listString1 = new ArrayList<String>();
		final List<String> listString2 = new ArrayList<String>();
		listString2.add("");
		assertEquals( 1 , GenericUtil.objectNotNull(null, listString2).size() );
		assertEquals( 0 , GenericUtil.objectNotNull(listString1, listString2).size() );
	}

	@Test
	public void testCastObjectNotNull1() throws Exception {
		Object value = null;
		final Integer defaultValue = Integer.valueOf(11);
		assertEquals( defaultValue, GenericUtil.castObjectNotNull(Integer.class, value, defaultValue) );

		value = "ciao";
		assertEquals( defaultValue, GenericUtil.castObjectNotNull(Integer.class, value, defaultValue) );

		value = Long.valueOf(11);
		assertEquals( defaultValue, GenericUtil.castObjectNotNull(Integer.class, value, defaultValue) );

		value = Integer.valueOf(22);
		assertEquals( value, GenericUtil.castObjectNotNull(Integer.class, value, defaultValue) );
	}

	@Test
	public void testCastObjectNotNull2() throws Exception {
		Object value = null;
		final Integer defaultValue = Integer.valueOf(11);
		assertEquals( defaultValue, GenericUtil.castObjectNotNull(Number.class, value, defaultValue) );

		value = "ciao";
		assertEquals( defaultValue, GenericUtil.castObjectNotNull(Number.class, value, defaultValue) );

		value = Long.valueOf(11);
		assertEquals( value, GenericUtil.castObjectNotNull(Number.class, value, defaultValue) );

		value = Integer.valueOf(22);
		assertEquals( value, GenericUtil.castObjectNotNull(Number.class, value, defaultValue) );
	}

}
