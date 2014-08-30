package com.jpattern.core.chain;

import com.jpattern.core.BaseApplicationTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/gen/2011
 */
public class ChainOfResponsibilityUnblockingTest extends BaseApplicationTest {
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testChainResponsibilityBlocking() throws Exception {
		
		/*
		 * Test with value 0
		 * Only the first, the second and the "always" elements must be activated
		 */
		int value = 0;
		StringBuffer active = new StringBuffer();

		IChainElement chain = new ChainElementUnBlocking0010(value, active);
		chain = new ChainElementUnBlocking0010(value, active, chain);
		chain = new ChainElementUnBlocking1120(value, active, chain);
		chain = new ChainElementUnBlocking3140(value, active, chain);
		chain = new ChainElementUnBlockingAlways(active, chain);
		assertTrue( chain.exec().isExecuted() );
		assertEquals( ChainElementUnBlocking0010.NAME + ChainElementUnBlocking0010.NAME + ChainElementUnBlockingAlways.NAME, active.toString() );

		
		
		/*
		 * Test with value 15
		 * Only the second element must be activated
		 */
		value = 15;
		active = new StringBuffer();

		chain = new ChainElementUnBlocking0010(value, active);
		chain = new ChainElementUnBlocking0010(value, active, chain);
		chain = new ChainElementUnBlocking1120(value, active, chain);
		chain = new ChainElementUnBlocking3140(value, active, chain);
		assertTrue( chain.exec().isExecuted() );
		assertEquals( ChainElementUnBlocking1120.NAME , active.toString() );
		
		
		
		/*
		 * Test with value 31
		 * Only the third element must be activated
		 */
		value = 40;
		active = new StringBuffer();

		chain = new ChainElementUnBlocking0010(value, active);
		chain = new ChainElementUnBlocking0010(value, active, chain);
		chain = new ChainElementUnBlocking1120(value, active, chain);
		chain = new ChainElementUnBlocking3140(value, active, chain);
		assertTrue( chain.exec().isExecuted() );
		assertEquals( ChainElementUnBlocking3140.NAME , active.toString() );
		
		
		
		
		/*
		 * Test with value 100
		 * Only the "always" element must be activated
		 */
		value = 100;
		active = new StringBuffer();

		chain = new ChainElementUnBlocking0010(value, active);
		chain = new ChainElementUnBlocking0010(value, active, chain);
		chain = new ChainElementUnBlocking1120(value, active, chain);
		chain = new ChainElementUnBlocking3140(value, active, chain);
		chain = new ChainElementUnBlockingAlways(active, chain);
		assertTrue( chain.exec().isExecuted() );
		assertEquals( ChainElementUnBlockingAlways.NAME , active.toString() );
		
		
		
		
		
		/*
		 * Test with value 100
		 * No element will be activated
		 */
		value = 100;
		active = new StringBuffer();

		chain = new ChainElementUnBlocking0010(value, active);
		chain = new ChainElementUnBlocking0010(value, active, chain);
		chain = new ChainElementUnBlocking1120(value, active, chain);
		chain = new ChainElementUnBlocking3140(value, active, chain);
		assertFalse( chain.exec().isExecuted() );
		assertEquals( "" , active.toString() );
	}
}
