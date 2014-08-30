package com.jpattern.core.chain;

import com.jpattern.core.BaseTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class NullChainElementTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testNullDefaultValue() throws Exception {
		IChainElement chainElement = new NullChainElement();
		assertFalse( chainElement.exec().isExecuted() );
	}

}
