package com.jpattern.shared.util;

import com.jpattern.shared.BaseTest;
import com.jpattern.shared.util.StringUtil;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/lug/2010
 */
public class StringHelperTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCut() throws Exception {
		String stringaProva = "0123456789";

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
	
	public void testTokenize() throws Exception {
		String source = "ciao_mamma_ufo";
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
