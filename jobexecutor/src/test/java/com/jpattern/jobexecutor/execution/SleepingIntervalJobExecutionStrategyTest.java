package com.jpattern.jobexecutor.execution;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.execution.SleepingIntervalJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public class SleepingIntervalJobExecutionStrategyTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSleeping1() throws Exception {
		
		long sleepTime = 2000;
		
		IJobExecutionStrategy jobExecutionStrategy = new SleepingIntervalJobExecutionStrategy( sleepTime );
		assertTrue( jobExecutionStrategy.canExecute() );
		
		// la prima volta risponde true, la seconda volta risponde false ed inizia a calcola il 
		// tempo di sleep, passato il tempo di sleep risponde nuovamente true 
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 500 );
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 500 );
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 500 );
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 1000 );
		//sono passati piu' di due secondi, risponde true
		assertTrue( jobExecutionStrategy.canExecute() );
		
		// ripeto il giro 
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 500 );
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 500 );
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 500 );
		assertFalse( jobExecutionStrategy.canExecute() );
		
		Thread.sleep( 1000 );
		//sono passati piu' di due secondi, risponde true
		assertTrue( jobExecutionStrategy.canExecute() );
		
	}
	
}
