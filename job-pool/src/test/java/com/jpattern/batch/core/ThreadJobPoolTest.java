package com.jpattern.batch.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.AJob;
import com.jpattern.batch.JobPool;
import com.jpattern.batch.execution.CronJobExecutionStrategy;
import com.jpattern.batch.execution.IntervalJobExecutionStrategy;
import com.jpattern.batch.mock.LongRunningBatch;
import com.jpattern.batch.mock.MockBatch1;
import com.jpattern.batch.mock.MockBatch2;
import com.jpattern.batch.mock.MockJob;
import com.jpattern.batch.status.StatusType;
import com.jpattern.logger.ILogger;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class ThreadJobPoolTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testBatchEngineStartStop() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final MockBatch1 batchDescription1 = new MockBatch1( "batch1", "batchGroup1");

		MockBatch1.EXECUTIONS = 0;

		batchEngine.start();

		batchEngine.addJob(batchDescription1, new IntervalJobExecutionStrategy(1,0));

		int sleepMaxTime = 5000;
		while (MockBatch1.EXECUTIONS==0) {
			Thread.sleep(1);
			if (--sleepMaxTime==0) {
				throw new RuntimeException("Waited too much... something goes wrong!");
			}
		}


		batchEngine.shutdown(true);

		final int batch1ExecutionsTempCount = MockBatch1.EXECUTIONS;

		Thread.sleep(100);
		assertEquals( batch1ExecutionsTempCount , MockBatch1.EXECUTIONS );
	}

	@Test
	public void testBatchEngineShutdownWait() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");
		final int howMany = 20;

		final List<AJob> batches = new ArrayList<AJob>();

		for (int i=0; i<howMany; i++) {
			final String name = "" + i;
			final LongRunningBatch batch = new LongRunningBatch(name, "");
			batches.add(batch);
			batchEngine.addJob(batch, new IntervalJobExecutionStrategy(1,0));
		}

		MockBatch1.EXECUTIONS = 0;

		batchEngine.start();

		while ( !batchEngine.getJobStatus(batches.get(0).getName(), "").isRunning() ) {
			Thread.sleep(100);
		}

		batchEngine.shutdown(true);

		final ILogger logger = this.getLogger();
		for (final AJob job : batches) {
			final StatusType status = batchEngine.getJobStatus(job.getName(), "").getStatusType();
			logger.info("batch: " + job.getName() + " - status: " + status.getCode());
			assertEquals(StatusType.KILLED, status);
		}

	}

	@Test
	public void testBatchEngineShutdownNoWait() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");
		final int howMany = 20;

		final List<AJob> batches = new ArrayList<AJob>();

		for (int i=0; i<howMany; i++) {
			final String name = "" + i;
			final LongRunningBatch batch = new LongRunningBatch(name, "");
			batches.add(batch);
			batchEngine.addJob(batch, new IntervalJobExecutionStrategy(1,0));
		}

		MockBatch1.EXECUTIONS = 0;

		batchEngine.start();

		while ( !batchEngine.getJobStatus(batches.get(0).getName(), "").isRunning() ) {
			Thread.sleep(100);
		}

		batchEngine.shutdown(false);

		boolean notKilledFound = false;
		final ILogger logger = this.getLogger();
		for (final AJob job : batches) {
			final StatusType status = batchEngine.getJobStatus(job.getName(), "").getStatusType();
			logger.info("batch: " + job.getName() + " - status: " + status.getCode());
			if ( !status.equals(StatusType.KILLED )) {
				notKilledFound = true;
			}
		}
		assertTrue(notKilledFound);

	}

	@Test
	public void testBatchEnginePauseAndResumeBatch() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final MockBatch1 batchDescription1 = new MockBatch1( "batch1", "batchGroup1");
		final MockBatch2 batchDescription2 = new MockBatch2( "batch2", "batchGroup1");
		MockBatch1.EXECUTIONS = 0;
		MockBatch2.EXECUTIONS = 0;

		batchEngine.start();

		batchEngine.addJob(batchDescription1, new IntervalJobExecutionStrategy(1,0));
		batchEngine.addJob(batchDescription2, new IntervalJobExecutionStrategy(1,0));

		int sleepMaxTime = 5000;
		while (MockBatch1.EXECUTIONS==0) {
			Thread.sleep(1);
			if (--sleepMaxTime==0) {
				throw new RuntimeException("Waited too much... something goes wrong!");
			}
		}

		final ILogger logger = this.getLogger();

		logger.info("Pause should work...");
		batchEngine.pauseJob("batch1", "batchGroup1");

		logger.info("Pause should NOT work...");
		batchEngine.pauseJob("batch1", "batchGroup1");

		Thread.sleep(50);
		final int batch1ExecutionsTempCount = MockBatch1.EXECUTIONS;
		int batch2ExecutionsTempCount = MockBatch2.EXECUTIONS;

		Thread.sleep(100);
		assertEquals( batch1ExecutionsTempCount , MockBatch1.EXECUTIONS );
		assertNotSame( batch2ExecutionsTempCount , MockBatch2.EXECUTIONS );

		batch2ExecutionsTempCount = MockBatch2.EXECUTIONS;
		batchEngine.resumeJob("batch1", "batchGroup1");

		Thread.sleep(50);
		assertTrue( MockBatch1.EXECUTIONS > batch1ExecutionsTempCount);
		assertNotSame( batch2ExecutionsTempCount , MockBatch2.EXECUTIONS );

		batchEngine.shutdown(true);
	}

	@Test
	public void testBatchEnginePauseAndResumeGroup() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final MockBatch1 batchDescription1 = new MockBatch1( "batch1", "batchGroup1");
		final MockBatch2 batchDescription2 = new MockBatch2( "batch2", "batchGroup1");
		MockBatch1.EXECUTIONS = 0;
		MockBatch2.EXECUTIONS = 0;

		batchEngine.start();

		batchEngine.addJob(batchDescription1, new IntervalJobExecutionStrategy(10,0));
		batchEngine.addJob(batchDescription2, new IntervalJobExecutionStrategy(10,0));

		int sleepMaxTime = 5000;
		while (MockBatch1.EXECUTIONS==0) {
			Thread.sleep(1);
			if (--sleepMaxTime==0) {
				throw new RuntimeException("Waited too much... something goes wrong!");
			}
		}

		batchEngine.pauseGroup("batchGroup1");

		Thread.sleep(50);
		final int batch1ExecutionsTempCount = MockBatch1.EXECUTIONS;
		final int batch2ExecutionsTempCount = MockBatch2.EXECUTIONS;

		Thread.sleep(100);
		assertEquals( batch1ExecutionsTempCount , MockBatch1.EXECUTIONS );
		assertEquals( batch2ExecutionsTempCount , MockBatch2.EXECUTIONS );

		batchEngine.resumeGroup("batchGroup1");

		Thread.sleep(50);
		assertTrue( MockBatch1.EXECUTIONS > batch1ExecutionsTempCount);
		assertTrue( MockBatch2.EXECUTIONS > batch2ExecutionsTempCount);

		batchEngine.shutdown(true);
	}

	@Test
	public void testBatchEnginePauseAndResumeAll() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final MockBatch1 batchDescription1 = new MockBatch1( "batch1", "batchGroup1");
		final MockBatch2 batchDescription2 = new MockBatch2( "batch2", "batchGroup1");
		MockBatch1.EXECUTIONS = 0;
		MockBatch2.EXECUTIONS = 0;

		batchEngine.start();

		batchEngine.addJob(batchDescription1, new IntervalJobExecutionStrategy(10,0));
		batchEngine.addJob(batchDescription2, new IntervalJobExecutionStrategy(10,0));

		int sleepMaxTime = 5000;
		while (MockBatch1.EXECUTIONS==0) {
			Thread.sleep(1);
			if (--sleepMaxTime==0) {
				throw new RuntimeException("Waited too much... something goes wrong!");
			}
		}

		batchEngine.pauseAllJobs();

		Thread.sleep(50);
		final int batch1ExecutionsTempCount = MockBatch1.EXECUTIONS;
		final int batch2ExecutionsTempCount = MockBatch2.EXECUTIONS;

		Thread.sleep(100);
		assertEquals( batch1ExecutionsTempCount , MockBatch1.EXECUTIONS );
		assertEquals( batch2ExecutionsTempCount , MockBatch2.EXECUTIONS );

		batchEngine.resumeAllJobs();

		Thread.sleep(50);
		assertTrue( MockBatch1.EXECUTIONS > batch1ExecutionsTempCount);
		assertTrue( MockBatch2.EXECUTIONS > batch2ExecutionsTempCount);

		batchEngine.shutdown(true);
	}

	@Test
	public void testBatchEngineCronScheduler() throws Exception {
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final MockBatch1 batchDescription1 = new MockBatch1( "batch1", "batchGroup1");

		final String cronEverySecond = "0/1 * * * * ?";

		MockBatch1.EXECUTIONS = 0;

		batchEngine.addJob(batchDescription1, new CronJobExecutionStrategy(cronEverySecond));

		final long millis = new Date().getTime() % 1000;
		//wait the begin of the next second
		Thread.sleep(1000 - millis);

		batchEngine.start();

		int sleepMaxTime = 5000;
		while (MockBatch1.EXECUTIONS==0) {
			Thread.sleep(1);
			if (--sleepMaxTime==0) {
				throw new RuntimeException("Waited too much... something goes wrong!");
			}
		}

		batchEngine.pauseJob("batch1", "batchGroup1");

		Thread.sleep(50);
		final int batch1ExecutionsTempCount = MockBatch1.EXECUTIONS;

		Thread.sleep(1100);
		assertEquals( batch1ExecutionsTempCount , MockBatch1.EXECUTIONS );

		batchEngine.resumeJob("batch1", "batchGroup1");

		Thread.sleep(1001);
		assertTrue( MockBatch1.EXECUTIONS > batch1ExecutionsTempCount);

		batchEngine.shutdown(true);
	}

	@Test
	public void testJobThreadPool1() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("sonoIlJob");
		assertEquals( "sonoIlJob" , jobThreadPool.getName() );

		final String groupName = "testGroup";
		final MockJob mockJob1 = new MockJob("mockJob1", groupName);
		final MockJob mockJob2 = new MockJob("mockJob2", groupName);

		jobThreadPool.addJob(mockJob1, new IntervalJobExecutionStrategy(250,0));
		jobThreadPool.addJob(mockJob2, new IntervalJobExecutionStrategy(250,0));

		assertFalse( jobThreadPool.getJobStatus( "mockJob1" , groupName).isKilled() );
		assertFalse( jobThreadPool.getJobStatus( "mockJob2" , groupName).isKilled() );

		Thread.sleep(1000);
		assertFalse( mockJob1.isExecuted() );
		assertFalse( mockJob2.isExecuted() );

		assertFalse( jobThreadPool.isStarted() );

		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(1000);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );

		Thread.sleep(1000);
		jobThreadPool.shutdown(true);
		assertFalse( jobThreadPool.isStarted() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob1" , groupName).isKilled() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob2" , groupName).isKilled() );
	}

	@Test
	public void testJobThreadPool2() throws Exception {
		final ThreadJobPool jobThreadPool = new ThreadJobPool("");

		final String groupName = "testGroup";
		final MockJob mockJob1 = new MockJob("mockJob1", groupName);
		final MockJob mockJob2 = new MockJob("mockJob2", groupName);

		jobThreadPool.addJob(mockJob1, new IntervalJobExecutionStrategy(250,0));
		jobThreadPool.addJob(mockJob2, new IntervalJobExecutionStrategy(250,0));

		assertFalse( jobThreadPool.getJobStatus( "mockJob1", groupName ).isKilled() );
		assertFalse( jobThreadPool.getJobStatus( "mockJob2", groupName ).isKilled() );

		jobThreadPool.start();
		assertTrue( jobThreadPool.isStarted() );
		Thread.sleep(1000);
		assertTrue( mockJob1.isExecuted() );
		assertTrue( mockJob2.isExecuted() );


		jobThreadPool.pauseJob("mockJob1", groupName);
		Thread.sleep(100);
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
		jobThreadPool.shutdown(true);
		assertFalse( jobThreadPool.isStarted() );
		assertFalse( jobThreadPool.getJobStatus( "mockJob1" , groupName).isRunning() );
		assertFalse( jobThreadPool.getJobStatus( "mockJob1" , groupName).isRunning() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob1" , groupName).isKilled() );
		assertTrue( jobThreadPool.getJobStatus( "mockJob2" , groupName).isKilled() );
	}

}
