package com.jpattern.gwt.util;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.util.ClassName;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public class ClassNameTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testClassName() throws Exception {
		assertEquals( getClass().getSimpleName(), ClassName.getSimpleClassName(getClass()) );
	}
	
}
