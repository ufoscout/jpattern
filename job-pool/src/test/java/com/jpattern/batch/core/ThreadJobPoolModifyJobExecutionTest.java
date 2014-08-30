package com.jpattern.batch.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.execution.IntervalJobExecutionStrategy;
import com.jpattern.batch.execution.NullJobExecutionStrategy;
import com.jpattern.batch.execution.RunOnceJobExecutionStrategy;
import com.jpattern.batch.mock.MockJob;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class ThreadJobPoolModifyJobExecutionTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testModifyJobExecution() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("");

		final String groupName = "testGroup";
		final String jobName = "job1";
		final MockJob mockJob1 = new MockJob(jobName, groupName);

		jobThreadPool.addJob(mockJob1, new IntervalJobExecutionStrategy(10,0));

		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(100);
		assertTrue( mockJob1.isExecuted() );

		jobThreadPool.modifyJobExecution(jobName, groupName, new NullJobExecutionStrategy());
		Thread.sleep(11);

		final int executionMock1 = mockJob1.getCorrectExecution();

		mockJob1.setExecuted(false);

		Thread.sleep(100);
		assertFalse( mockJob1.isExecuted() );

		assertTrue( jobThreadPool.getJobsName(groupName).contains(jobName) );

		assertFalse( jobThreadPool.getJobStatus( jobName , groupName).isRunning() );
		assertFalse( jobThreadPool.getJobStatus( jobName , groupName).isKilled() );
		assertEquals(executionMock1, mockJob1.getCorrectExecution());

		jobThreadPool.modifyJobExecution(jobName, groupName, new RunOnceJobExecutionStrategy());
		Thread.sleep(11);

		assertTrue( mockJob1.isExecuted() );
		assertEquals(executionMock1 + 1, mockJob1.getCorrectExecution());

		Thread.sleep(100);
		jobThreadPool.shutdown(true);
	}

}
