package com.jpattern.batch.execution;


import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/giu/2010
 */
public class IntervallJobExecutionStrategyTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testCount() throws Exception {
		long interval = 100l;
		long delay = 20l;
		final JobExecutionStrategy executionStrategy = new IntervalJobExecutionStrategy(interval, delay);

		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("" + interval) );

		assertFalse( executionStrategy.canExecute() );

		Thread.sleep( delay );
		assertTrue( executionStrategy.hasOtherExecution() );
		assertTrue( interval >= executionStrategy.millisecondsBeforeNextExecution() );
		assertTrue( executionStrategy.canExecute() );
		assertTrue( executionStrategy.hasOtherExecution() );

		long remainingToSleep = executionStrategy.millisecondsBeforeNextExecution();
		Thread.sleep( (remainingToSleep/2) );
		assertFalse( executionStrategy.canExecute() );
		assertTrue( (remainingToSleep/2) >= executionStrategy.millisecondsBeforeNextExecution() );

		Thread.sleep( executionStrategy.millisecondsBeforeNextExecution() );
		assertTrue( executionStrategy.hasOtherExecution() );
		assertTrue( interval >= executionStrategy.millisecondsBeforeNextExecution() );
		assertTrue( executionStrategy.canExecute() );
		assertTrue( executionStrategy.hasOtherExecution() );

		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("" + interval) );
	}

}
