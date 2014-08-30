package com.jpattern.batch.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.execution.IntervalJobExecutionStrategy;
import com.jpattern.batch.mock.MockJob;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class ThreadJobPoolRemoveJobTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testRemoveJob() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("");

		final String groupName = "testGroup";
		final MockJob mockJob1 = new MockJob("mockJob1", groupName);
		final MockJob mockJob2 = new MockJob("mockJob2", groupName);

		jobThreadPool.addJob(mockJob1, new IntervalJobExecutionStrategy(10,0));
		jobThreadPool.addJob(mockJob2, new IntervalJobExecutionStrategy(10,0));

		assertFalse( jobThreadPool.getJobStatus( "mockJob1", groupName ).isKilled() );
		assertFalse( jobThreadPool.getJobStatus( "mockJob2", groupName ).isKilled() );

		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(100);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );


		jobThreadPool.removeJob("mockJob1", groupName);
		int executionMock1 = mockJob1.getCorrectExecution();
		Thread.sleep(11);
		mockJob1.setExecuted(false);
		mockJob2.setExecuted(false);
		Thread.sleep(100);
		assertFalse( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );

		assertFalse( jobThreadPool.getJobsName(groupName).contains("mockJob1") );
		assertTrue( jobThreadPool.getJobsName(groupName).contains("mockJob2") );
		jobThreadPool.resumeAllJobs();
		mockJob1.setExecuted(false);
		mockJob2.setExecuted(false);
		Thread.sleep(100);
		assertFalse( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );

		assertFalse( jobThreadPool.getJobStatus( "mockJob1" , groupName).isRunning() );
		assertFalse( jobThreadPool.getJobStatus( "mockJob2" , groupName).isKilled() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob1" , groupName).isKilled() );
		assertEquals(executionMock1, mockJob1.getCorrectExecution());

		jobThreadPool.removeAllJobs();
		Thread.sleep(11);
		mockJob1.setExecuted(false);
		mockJob2.setExecuted(false);
		Thread.sleep(50);
		assertFalse( mockJob1.isExecuted() );
		assertFalse( mockJob2.isExecuted() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob2" , groupName).isKilled() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob1" , groupName).isKilled() );

		Thread.sleep(100);
		jobThreadPool.shutdown(true);
	}

}
