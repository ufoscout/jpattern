package com.jpattern.batch.execution;


import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobExecutionStrategy;
import com.jpattern.batch.execution.RunOnceJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/giu/2010
 */
public class RunOnceJobExecutionStrategyTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testCount() throws Exception {
		final JobExecutionStrategy executionStrategy = new RunOnceJobExecutionStrategy();
		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("1") );
		assertTrue( executionStrategy.hasOtherExecution() );
		assertEquals( 0l , executionStrategy.millisecondsBeforeNextExecution() );
		assertTrue( executionStrategy.canExecute() );
		assertFalse( executionStrategy.hasOtherExecution() );
		assertTrue( executionStrategy.millisecondsBeforeNextExecution() > 0l );
		assertFalse( executionStrategy.canExecute() );
		assertFalse( executionStrategy.canExecute() );
		assertFalse( executionStrategy.canExecute() );

		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("0") );
	}

}
