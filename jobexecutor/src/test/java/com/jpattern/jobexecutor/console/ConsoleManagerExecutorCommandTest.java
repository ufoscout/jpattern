package com.jpattern.jobexecutor.console;

import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobResult;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.console.ConsoleManagerExecutorCommand;
import com.jpattern.jobexecutor.core.BooleanWrapper;
import com.jpattern.jobexecutor.core.JobThreadPool;
import com.jpattern.jobexecutor.core.NullJobExecutionStrategy;
import com.jpattern.jobexecutor.core.WrappedResult;
import com.jpattern.jobexecutor.execution.IntervalJobExecutionStrategy;
import com.jpattern.jobexecutor.execution.SleepingIntervalJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class ConsoleManagerExecutorCommandTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testManager() throws Exception {
		JobThreadPool jobThreadPool = new JobThreadPool("Job Test");
		jobThreadPool.start();
		jobThreadPool.addJob(new MockJob("JOB1"), new SleepingIntervalJobExecutionStrategy(50));
		jobThreadPool.addJob(new MockJob("JOB2"), new IntervalJobExecutionStrategy(50));
		
		String job1Status =  jobThreadPool.getExecutableJob("JOB1").executionAsString();
		String job2Status =  jobThreadPool.getExecutableJob("JOB2").executionAsString();
		System.out.println( "esecuzione Job1: " + job1Status );
		System.out.println( "esecuzione Job2: " + job2Status );
		
		
		ICommandExecutorHandler command = new ConsoleManagerExecutorCommand(jobThreadPool);
		
		IWrappedResult aWrappedResult = new WrappedResult();
		IBooleanWrapper stopCommand = new BooleanWrapper(false);
		command.exec( ICommandExecutorHandler.COMMAND_CONSOLE_MANAGER, aWrappedResult, stopCommand );
		
		System.out.println("risultato:");
		System.out.println(aWrappedResult.result());		
	
		assertTrue( aWrappedResult.result().contains(job1Status) );
		assertTrue( aWrappedResult.result().contains(job2Status) );
		assertFalse( stopCommand.getValue() ); 
		
		
		
		//FERMO IL JOB1
		aWrappedResult = new WrappedResult();
		stopCommand = new BooleanWrapper(false);
		command.exec( ConsoleManagerExecutorCommand.STOP + " JOB1", aWrappedResult, stopCommand );
		
		System.out.println("risultato:");
		System.out.println(aWrappedResult.result());		
	
		assertTrue( aWrappedResult.result().contains(new NullJobExecutionStrategy().asString()) );
		assertTrue( aWrappedResult.result().contains(job2Status) );
		assertFalse( aWrappedResult.result().contains(job1Status) );
		assertFalse( stopCommand.getValue() ); 

		
		
		//FERMO IL JOB2
		aWrappedResult = new WrappedResult();
		stopCommand = new BooleanWrapper(false);
		command.exec( ConsoleManagerExecutorCommand.STOP + " JOB2", aWrappedResult, stopCommand );
		
		System.out.println("risultato:");
		System.out.println(aWrappedResult.result());		
	
		assertTrue( aWrappedResult.result().contains(new NullJobExecutionStrategy().asString()) );
		assertFalse( aWrappedResult.result().contains(job2Status) );
		assertFalse( aWrappedResult.result().contains(job1Status) );
		assertFalse( stopCommand.getValue() ); 
		
		
		//RESTARTO IL JOB2
		aWrappedResult = new WrappedResult();
		stopCommand = new BooleanWrapper(false);
		command.exec( ConsoleManagerExecutorCommand.START + " JOB2", aWrappedResult, stopCommand );
		
		System.out.println("risultato:");
		System.out.println(aWrappedResult.result());		
	
		assertTrue( aWrappedResult.result().contains(new NullJobExecutionStrategy().asString()) );
		assertTrue( aWrappedResult.result().contains(job2Status) );
		assertFalse( aWrappedResult.result().contains(job1Status) );
		assertFalse( stopCommand.getValue() ); 
		
		
		//RESTARTO IL JOB1
		aWrappedResult = new WrappedResult();
		stopCommand = new BooleanWrapper(false);
		command.exec( ConsoleManagerExecutorCommand.START + " JOB1", aWrappedResult, stopCommand );
		
		System.out.println("risultato:");
		System.out.println(aWrappedResult.result());		
	
		assertFalse( aWrappedResult.result().contains(new NullJobExecutionStrategy().asString()) );
		assertTrue( aWrappedResult.result().contains(job2Status) );
		assertTrue( aWrappedResult.result().contains(job1Status) );
		assertFalse( stopCommand.getValue() ); 
		
		
		//Esco dal manager
		aWrappedResult = new WrappedResult();
		stopCommand = new BooleanWrapper(false);
		command.exec( ConsoleManagerExecutorCommand.QUIT, aWrappedResult, stopCommand );
		assertTrue( stopCommand.getValue() ); 
	}
	
	class MockJob implements IJob {
		private static final long serialVersionUID = 1L;
		private String name;
		
		public MockJob(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}

		public IJobResult execute() throws Exception {
			return null;
		}
		
	}
}
