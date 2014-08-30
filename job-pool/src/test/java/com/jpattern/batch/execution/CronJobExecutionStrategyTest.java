package com.jpattern.batch.execution;


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class CronJobExecutionStrategyTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testCron() throws Exception {
		final String cronEverySecond = "0/1 * * * * ?";

		final long millis = new Date().getTime() % 1000;
		//wait the begin of the next second
		Thread.sleep(1000 - millis);

		final JobExecutionStrategy executionStrategy = new CronJobExecutionStrategy(cronEverySecond);

		assertTrue( executionStrategy.asString().contains(cronEverySecond) );
		assertTrue( executionStrategy.canExecute() );

		// should be false because it has just been executed in this second
		assertFalse( executionStrategy.canExecute() );

		Thread.sleep( 200 );
		assertFalse( executionStrategy.canExecute() );

		long millisToWait = executionStrategy.millisecondsBeforeNextExecution();
		System.out.println("millisToWait = " + millisToWait);
		Thread.sleep( millisToWait );
		assertTrue( executionStrategy.canExecute() );
		assertFalse( executionStrategy.canExecute() );
	}

}
