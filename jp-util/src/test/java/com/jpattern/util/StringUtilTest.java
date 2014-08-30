package com.jpattern.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.BaseTest;
import com.jpattern.util.StringUtil;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/lug/2010
 */
public class StringUtilTest extends BaseTest {

	@Test
	public void testStringNotNull() throws Exception {
		String value = null;
		final String defaultValue = "default";
		assertEquals( defaultValue, StringUtil.stringNotNullTrimmed(value, defaultValue) );

		value = " ciao ";
		assertEquals( "ciao", StringUtil.stringNotNullTrimmed(value, defaultValue) );

		assertNull( StringUtil.stringNotNullTrimmed(null, null) );
	}
	@Test
	public void testCut() throws Exception {
		final String stringaProva = "0123456789";

		assertEquals("01234", StringUtil.cut(stringaProva, 5) );

		assertEquals( stringaProva , StringUtil.cut(stringaProva, 20) );

		assertEquals( "" , StringUtil.cut(stringaProva, 0) );

		assertEquals( stringaProva , StringUtil.cut(stringaProva, -10) );

		int maxLength = 6;
		String stringaTagliata = StringUtil.cut(stringaProva, maxLength);
		System.out.println(stringaTagliata);
		assertEquals(maxLength, stringaTagliata.length());


		maxLength = 10;
		stringaTagliata = StringUtil.cut(stringaProva, maxLength);
		System.out.println(stringaTagliata);
		assertEquals(maxLength, stringaTagliata.length());

		maxLength = 11;
		stringaTagliata = StringUtil.cut(stringaProva, maxLength);
		System.out.println(stringaTagliata);
		assertEquals(stringaProva.length(), stringaTagliata.length());
	}

	@Test
	public void testTokenize() throws Exception {
		final String source = "ciao_mamma_ufo";
		String pattern = "_";
		String[] result = StringUtil.tokenize(source, pattern);

		assertEquals( 3 , result.length );
		assertEquals( "ciao" , result[0] );
		assertEquals( "mamma" , result[1] );
		assertEquals( "ufo" , result[2] );

		pattern = "$";
		result = StringUtil.tokenize(source, pattern);

		assertEquals( 1 , result.length );
		assertEquals( "ciao_mamma_ufo" , result[0] );
	}
}
