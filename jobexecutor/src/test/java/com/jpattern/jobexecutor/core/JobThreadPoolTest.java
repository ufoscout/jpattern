package com.jpattern.jobexecutor.core;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobResult;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.core.JobThreadPool;
import com.jpattern.jobexecutor.core.NullJobResult;
import com.jpattern.jobexecutor.execution.IntervalJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class JobThreadPoolTest extends BaseTest {
	
	IJobThreadPool jobThreadPool;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		jobThreadPool.shutDown();
	}

	public void testJobThreadPool1() throws Exception {
		jobThreadPool = new JobThreadPool("sonoIlJob");
		assertEquals( "sonoIlJob" , jobThreadPool.getName() );
		
		MockJob mockJob1 = new MockJob("mockJob1");
		MockJob mockJob2 = new MockJob("mockJob2");
		
		jobThreadPool.addJob(mockJob1, new IntervalJobExecutionStrategy(250));
		jobThreadPool.addJob(mockJob2, new IntervalJobExecutionStrategy(250));
		
		assertTrue( jobThreadPool.getExecutableJob( "mockJob1" ).isAlive() );
		assertTrue( jobThreadPool.getExecutableJob( "mockJob2" ).isAlive() );
		
		Thread.sleep(1000);
		assertFalse( mockJob1.isExecuted() );
		assertFalse( mockJob2.isExecuted() );
		
		assertTrue( jobThreadPool.isRunning() );
		assertFalse( jobThreadPool.isStarted() );
		
		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(1000);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );
		
		jobThreadPool.stop();
		assertFalse( jobThreadPool.isStarted() );
		Thread.sleep(1000);
		mockJob1.setExecuted(false);
		mockJob2.setExecuted(false);
		assertFalse( mockJob1.isExecuted() );
		assertFalse( mockJob2.isExecuted() );
		
		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(1000);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );
		
		Thread.sleep(1000);
		jobThreadPool.shutDown();
		assertFalse( jobThreadPool.isRunning() );
		assertFalse( jobThreadPool.getExecutableJob( "mockJob1" ).isAlive() );
		assertFalse( jobThreadPool.getExecutableJob( "mockJob2" ).isAlive() );
	}
	
	
	public void testJobThreadPool2() throws Exception {
		jobThreadPool = new JobThreadPool("");
		
		MockJob mockJob1 = new MockJob("mockJob1");
		MockJob mockJob2 = new MockJob("mockJob2");
		
		jobThreadPool.addJob(mockJob1, new IntervalJobExecutionStrategy(250));
		jobThreadPool.addJob(mockJob2, new IntervalJobExecutionStrategy(250));
		
		assertTrue( jobThreadPool.getExecutableJob( "mockJob1" ).isAlive() );
		assertTrue( jobThreadPool.getExecutableJob( "mockJob2" ).isAlive() );
		
		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(1000);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );
		
		
		jobThreadPool.pauseJob("mockJob1");
		mockJob1.setExecuted(false);
		mockJob2.setExecuted(false);
		Thread.sleep(1000);
		assertFalse( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );
		
		jobThreadPool.resumeAllJobs();
		mockJob1.setExecuted(false);
		mockJob2.setExecuted(false);
		Thread.sleep(1000);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );
		
		Thread.sleep(1000);
		jobThreadPool.shutDown();
		assertFalse( jobThreadPool.isRunning() );
		assertFalse( jobThreadPool.getExecutableJob( "mockJob1" ).isAlive() );
		assertFalse( jobThreadPool.getExecutableJob( "mockJob2" ).isAlive() );
	}
	
	
	
	class MockJob implements IJob {

		private static final long serialVersionUID = 1L;
		private String name;
		private boolean mockJobExecuted = false ;
		private int correctExecution = 0;
		
		public MockJob(String name) {
			this.name = name;
		}
		
		public IJobResult execute() throws Exception {
			mockJobExecuted = true;
			System.out.println(name + ": executed " + correctExecution++);
			return new NullJobResult();
		}

		public void setExecuted(boolean executed) {
			mockJobExecuted = executed;
		}
		
		public boolean isExecuted() {
			return mockJobExecuted;
		}
		
		public String getName() {
			return name;
		}
	}
	
}
