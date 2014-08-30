package com.jpattern.shared.util;

import com.jpattern.shared.BaseTest;
import com.jpattern.shared.util.UniqueId;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class UniqueIdTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testRandom() throws Exception {
		for ( int i=0; i<10; i++) {
			String unique = UniqueId.get();
			assertNotNull(unique);
			assertTrue( unique.length()>0);
			System.out.println(unique);
			System.out.println("length: " + unique.length());
		}
	}

}
