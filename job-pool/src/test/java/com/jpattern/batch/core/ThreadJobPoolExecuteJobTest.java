package com.jpattern.batch.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.batch.AJob;
import com.jpattern.batch.BaseTest;
import com.jpattern.batch.Job;
import com.jpattern.batch.execution.NullJobExecutionStrategy;
import com.jpattern.batch.execution.TimedJobExecutionStrategy;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class ThreadJobPoolExecuteJobTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testExecuteRunningJob() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("");

		final String groupName = "testGroup";
		final String jobName = "sleepingJob1";
		final long sleepFor = 250l;
		final int executeTimes = 10;
		final Job mockJob1 = new SleepingJob(jobName, groupName, sleepFor);
		jobThreadPool.start();

		jobThreadPool.addJob(mockJob1, new TimedJobExecutionStrategy(executeTimes));

		Thread.sleep(sleepFor/4);
		//Those call to execute should not have effect because the batch is running
		jobThreadPool.executeJob( jobName , groupName);
		jobThreadPool.executeGroup(groupName);
		jobThreadPool.executeAllJobs();

		while( jobThreadPool.getJobTrigger( jobName , groupName).isAlive() ) {
			Thread.sleep(1);
		}

		assertEquals(executeTimes , jobThreadPool.getJobStatus( jobName , groupName).getExecutionTimes() );

		jobThreadPool.shutdown(true);
	}

	@Test
	public void testExecutePausedJob() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("");

		final String groupName = "testGroup";
		final String jobName = "sleepingJob1";
		final long sleepFor = 50l;
		final Job mockJob1 = new SleepingJob(jobName, groupName, sleepFor);
		jobThreadPool.start();

		jobThreadPool.addJob(mockJob1, new NullJobExecutionStrategy());
		jobThreadPool.pauseAllJobs();

		assertEquals(0 , jobThreadPool.getJobStatus( jobName , groupName).getExecutionTimes() );

		jobThreadPool.executeJob( jobName , groupName);
		Thread.sleep(sleepFor/2);
		assertTrue( jobThreadPool.getJobStatus( jobName , groupName).isRunning() );
		Thread.sleep(sleepFor);
		assertEquals(1 , jobThreadPool.getJobStatus( jobName , groupName).getExecutionTimes() );

		Thread.sleep(2*sleepFor );
		assertEquals(1 , jobThreadPool.getJobStatus( jobName , groupName).getExecutionTimes() );

		jobThreadPool.shutdown(true);
	}

	@Test
	public void testExecuteJob() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("");

		final String groupName = "testGroup";
		final String jobName = "sleepingJob1";
		final long sleepFor = 50l;
		final Job mockJob1 = new SleepingJob(jobName, groupName, sleepFor);
		jobThreadPool.start();

		jobThreadPool.addJob(mockJob1, new NullJobExecutionStrategy());

		assertFalse( jobThreadPool.getJobStatus( jobName , groupName).isRunning() );
		jobThreadPool.executeJob( jobName , groupName);
		Thread.sleep(sleepFor/2);
		assertTrue( jobThreadPool.getJobStatus( jobName , groupName).isRunning() );
		Thread.sleep(sleepFor);
		assertEquals(1 , jobThreadPool.getJobStatus( jobName , groupName).getExecutionTimes() );

		//Only one of this three executions should be performed
		jobThreadPool.executeJob( jobName , groupName);
		jobThreadPool.executeJob( jobName , groupName);
		jobThreadPool.executeJob( jobName , groupName);

		Thread.sleep(sleepFor + 10);
		assertEquals(2 , jobThreadPool.getJobStatus( jobName , groupName).getExecutionTimes() );

		Thread.sleep(100);
		jobThreadPool.shutdown(true);
	}

	class SleepingJob extends AJob {

		private final long sleepFor;

		public SleepingJob(final String name, final String group, final long sleepFor) {
			super(name, group);
			this.sleepFor = sleepFor;
		}

		@Override
		public void execute() {
			try {
				Thread.sleep(this.sleepFor);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
