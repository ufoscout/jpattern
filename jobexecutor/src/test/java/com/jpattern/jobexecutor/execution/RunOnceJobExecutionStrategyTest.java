package com.jpattern.jobexecutor.execution;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.execution.RunOnceJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/giu/2010
 */
public class RunOnceJobExecutionStrategyTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCount() throws Exception {
		IJobExecutionStrategy executionStrategy = new RunOnceJobExecutionStrategy();
		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("1") );
		assertTrue( executionStrategy.canExecute() );
		assertFalse( executionStrategy.canExecute() );
		assertFalse( executionStrategy.canExecute() );
		assertFalse( executionStrategy.canExecute() );
		
		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("0") );
	}

}
