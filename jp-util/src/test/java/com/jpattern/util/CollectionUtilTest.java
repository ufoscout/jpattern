package com.jpattern.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author Francesco Cina' 14/dic/2010 - 15.06.22
 * @version $Id$
 *
 */
public class CollectionUtilTest extends BaseTest {

	@Test
	public void testArrayToList() throws Exception {
		final String[] stringArray1 = new String[]{"primo","secondo"};
		final List<String> result = CollectionUtil.toList(stringArray1);

		assertEquals(2 , result.size());
		assertEquals("primo" , result.get(0));
		assertEquals("secondo" , result.get(1));

	}

	@Test
	public void testArrayToList2() throws Exception {
		final Integer[] stringArray1 = new Integer[]{1,2};
		final List<Integer> result = CollectionUtil.toList(stringArray1);

		assertEquals(2 , result.size());
		assertEquals( Integer.valueOf(1) , result.get(0));
		assertEquals( Integer.valueOf(2) , result.get(1));

	}

	@Test
	public void testCollectionToArray() throws Exception {
		final List<String> integerList = new ArrayList<String>();
		integerList.add( "1" );
		integerList.add( "2" );

		final String[] result = CollectionUtil.toArray(String.class, integerList);

		assertEquals(2 , result.length);
		assertEquals( "1" , result[0]);
		assertEquals( "2" , result[1]);
	}

	@Test
	public void testCollectionToArray2() throws Exception {
		final List<Integer> integerList = new ArrayList<Integer>();
		integerList.add( Integer.valueOf(1) );
		integerList.add( Integer.valueOf(2) );

		final Number[] result = CollectionUtil.toArray(Number.class, integerList);

		assertEquals(2 , result.length);
		assertEquals( Integer.valueOf(1) , result[0]);
		assertEquals( Integer.valueOf(2) , result[1]);
	}
}
