package com.jpattern.batch.execution;


import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/giu/2010
 */
public class TimedJobExecutionStrategyTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testCountedExecutions() throws Exception {
		int howMany = 10;
		final JobExecutionStrategy executionStrategy = new TimedJobExecutionStrategy(howMany);
		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("" + howMany) );

		for (int i=1 ; i<=howMany ; i++) {
			assertTrue( executionStrategy.hasOtherExecution() );
			assertEquals( 0l , executionStrategy.millisecondsBeforeNextExecution() );
			assertTrue( executionStrategy.canExecute() );
			System.out.println( executionStrategy.asString() );
			assertTrue( executionStrategy.asString().contains("" + (howMany-i)) );
		}

		assertFalse( executionStrategy.hasOtherExecution() );
		assertEquals( Long.MAX_VALUE , executionStrategy.millisecondsBeforeNextExecution() );
		assertFalse( executionStrategy.canExecute() );
		assertFalse( executionStrategy.hasOtherExecution() );

		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("0") );
	}

	@Test
	public void testInfiniteExecutions() throws Exception {
		final JobExecutionStrategy executionStrategy = new TimedJobExecutionStrategy(TimedJobExecutionStrategy.INFINITE);
		System.out.println( executionStrategy.asString() );
		assertTrue( executionStrategy.asString().contains("infinite") );

		for (int i=1 ; i<=new Random().nextInt(100) ; i++) {
			assertTrue( executionStrategy.hasOtherExecution() );
			assertEquals( 0l , executionStrategy.millisecondsBeforeNextExecution() );
			assertTrue( executionStrategy.canExecute() );
			System.out.println( executionStrategy.asString() );
			assertTrue( executionStrategy.asString().contains("" + i) );
			assertTrue( executionStrategy.asString().contains("infinite") );
		}

	}

}
