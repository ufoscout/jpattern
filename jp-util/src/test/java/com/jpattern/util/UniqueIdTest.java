package com.jpattern.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.BaseTest;
import com.jpattern.util.UniqueId;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class UniqueIdTest extends BaseTest {

	@Test
	public void testRandom() throws Exception {
		for ( int i=0; i<10; i++) {
			final String unique = UniqueId.get();
			assertNotNull(unique);
			assertTrue( unique.length()>0);
			System.out.println(unique);
			System.out.println("length: " + unique.length());
		}
	}

}
