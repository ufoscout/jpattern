package com.jpattern.jobexecutor.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobResult;
import com.jpattern.jobexecutor.core.BooleanWrapper;
import com.jpattern.jobexecutor.core.ExecutableJob;
import com.jpattern.jobexecutor.core.NullJobResult;
import com.jpattern.jobexecutor.execution.IntervalJobExecutionStrategy;
import com.jpattern.jobexecutor.execution.TimedJobExecutionStrategy;
import com.jpattern.jobexecutor.mock.ExecutableJobResultMock;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class ExecutableJobTest extends BaseTest {
	
	boolean mockJobExecuted = false;
	int exception = 0;
	int correctExecution = 0;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecutableJob() throws Exception {
		IJob mockJob1 = new MockJob("mockJob1");
		ExecutableJobResultMock executableJobResult = new ExecutableJobResultMock();
		IExecutableJob executableJob = new ExecutableJob( mockJob1, new IntervalJobExecutionStrategy(1), executableJobResult , new BooleanWrapper(true));
		
		assertEquals( mockJob1.getName() , executableJob.getJobName() );
		assertTrue( executableJob.isAlive() );
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit( executableJob );
		
		Thread.sleep(2000);
		
		executableJob.kill();
		assertFalse( executableJob.isAlive() );
		assertTrue( mockJobExecuted );
		
		assertEquals( exception , executableJobResult.getException() );
		assertEquals( correctExecution , executableJobResult.getCorrectExecution() );
		
	}
	
	
	public void testExecutableJobDaemon() throws Exception {
		
		int quanteEsecuzioni = 50;
		
		IJob mockJob1 = new MockJob("mockJob2");
		ExecutableJobResultMock executableJobResult = new ExecutableJobResultMock();
		IExecutableJob executableJob = new ExecutableJob( mockJob1, new TimedJobExecutionStrategy( quanteEsecuzioni ), executableJobResult , new BooleanWrapper(true));
		
		assertEquals( mockJob1.getName() , executableJob.getJobName() );
		assertTrue( executableJob.isAlive() );
		
		ExecutorService executor = Executors.newSingleThreadExecutor( Executors.defaultThreadFactory() );
		executor.submit( executableJob );
		executor.awaitTermination(10, TimeUnit.SECONDS);
		
		assertEquals( quanteEsecuzioni , executableJobResult.getCorrectExecution() + executableJobResult.getException() );
		executableJob.kill();
	}
	
	public void testIsExecuting() throws Exception {
		
		IJob job1 = new IJob() {
			private static final long serialVersionUID = 1L;
			public String getName() {
				return "";
			}
			public IJobResult execute() throws Exception {
				System.out.println("esecuzione in corso");
				Thread.sleep(1000);
				System.out.println("fine esecuzione");
				return null;
			}
			
		};
		ExecutableJobResultMock executableJobResult = new ExecutableJobResultMock();
		IExecutableJob executableJob = new ExecutableJob( job1, new TimedJobExecutionStrategy( 1 ), executableJobResult , new BooleanWrapper(true));
		
		assertFalse(  executableJob.isWorking() );
		
		ExecutorService executor = Executors.newSingleThreadExecutor( Executors.defaultThreadFactory() );
		executor.submit( executableJob );
		
		Thread.sleep(300);
		
		assertTrue(  executableJob.isWorking() );
		
		executor.awaitTermination(2, TimeUnit.SECONDS);
		
		System.out.println("fine waiting");
		
		assertFalse(  executableJob.isWorking() );
		
	}

	
	class MockJob implements IJob {

		private static final long serialVersionUID = 1L;
		private String name;
		
		public MockJob(String name) {
			this.name = name;
		}
		
		public IJobResult execute() throws Exception {
			mockJobExecuted = true;
			if(Math.random() > 0.7) {
				System.out.println("job: random Exception throwed " + exception++);
				throw new Exception();
			}
			System.out.println("job: executed " + correctExecution++);
			return new NullJobResult();
		}

		public String getName() {
			return name;
		}
	}
}
