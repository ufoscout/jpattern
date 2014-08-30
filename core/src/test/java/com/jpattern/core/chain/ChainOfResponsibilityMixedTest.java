package com.jpattern.core.chain;

import com.jpattern.core.BaseApplicationTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/gen/2011
 */
public class ChainOfResponsibilityMixedTest extends BaseApplicationTest {
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testChainResponsibilityBlocking() throws Exception {
		
		/*
		 * Test with value 0
		 * Only the first element must be activated
		 */
		int value = 0;
		StringBuffer active = new StringBuffer();

		IChainElement chain = new ChainElementUnBlocking0010(value, active);
		chain = new ChainElementBlocking0010(value, active, chain);
		chain = new ChainElementBlocking1120(value, active, chain);
		chain = new ChainElementBlocking3140(value, active, chain);
		chain = new ChainElementBlockingAlways(active, chain);
		assertTrue( chain.exec().isExecuted() );
		assertEquals( ChainElementBlocking0010.NAME , active.toString() );

		
		
		/*
		 * Test with value 0
		 * Only the first and the "always" elements must be activated
		 */
		value = 0;
		active = new StringBuffer();

		chain = new ChainElementBlocking0010(value, active);
		chain = new ChainElementBlocking0010(value, active, chain);
		chain = new ChainElementBlocking1120(value, active, chain);
		chain = new ChainElementBlocking3140(value, active, chain);
		chain = new ChainElementUnBlockingAlways(active, chain);
		assertTrue( chain.exec().isExecuted() );
		assertEquals( ChainElementBlocking0010.NAME + ChainElementBlockingAlways.NAME , active.toString() );
		
	}
}
