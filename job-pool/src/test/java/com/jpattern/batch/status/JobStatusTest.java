package com.jpattern.batch.status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobPool;
import com.jpattern.batch.core.ThreadJobPool;
import com.jpattern.batch.execution.IntervalJobExecutionStrategy;
import com.jpattern.batch.mock.LongRunningBatch;
import com.jpattern.batch.status.JobStatus;
import com.jpattern.batch.status.StatusType;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class JobStatusTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testBatchStatus() throws Exception {
		final ILogger logger = getLogger();

		final Date startTestDate = new Date();
		final String group = "batchGroup1";
		final String name = "batch1";
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final LongRunningBatch batchDescription1 = new LongRunningBatch("batch1", "batchGroup1");
		batchDescription1.STARTED = false;
		batchDescription1.FINISHED = false;
		batchDescription1.RUN = 0;

		batchEngine.addJob(batchDescription1, new IntervalJobExecutionStrategy(1000,0));
		assertEquals( StatusType.WAITING , batchEngine.getJobStatus(name, group).getStatusType() );

		batchEngine.start();

		while ( !batchDescription1.STARTED ) {

			logger.info("Waiting to start...");
		}
		while ( !batchDescription1.FINISHED ) {
			synchronized ( batchDescription1.RUN ) {
				assertEquals( StatusType.RUNNING , batchEngine.getJobStatus(name, group).getStatusType() );
				logger.info("Run is: " + batchDescription1.RUN );
				assertTrue( batchDescription1.RUN == 1 );
				Thread.sleep(25);
			}
		}

		assertEquals( StatusType.WAITING , batchEngine.getJobStatus(name, group).getStatusType() );

		batchEngine.pauseJob(name, group);
		assertEquals( StatusType.PAUSED , batchEngine.getJobStatus(name, group).getStatusType() );

		batchEngine.resumeJob(name, group);
		assertEquals( StatusType.WAITING , batchEngine.getJobStatus(name, group).getStatusType() );

		final JobStatus status = batchEngine.getJobStatus(name, group);
		assertTrue( status.getExecutionTimes() > 0 );
		assertTrue( status.getExecutionInError() == 0 );
		assertTrue( status.getMeanExecutionTimeMillisec() > 0  );
		assertTrue( status.getLastExecutionStartDate().compareTo(startTestDate) > 0 );
		assertTrue( status.getLastExecutionEndDate().compareTo(startTestDate) > 0 );
		assertTrue( status.getLastExecutionEndDate().compareTo(status.getLastExecutionStartDate()) > 0 );

		batchEngine.shutdown(true);
	}


	@Test
	public void testStatus() {

		final JobStatusImpl status = new JobStatusImpl("", "");

		assertEquals(StatusType.WAITING , status.getStatusType());

		status.setRunning(false);
		assertEquals(StatusType.WAITING , status.getStatusType());

		status.setRunning(true);
		assertEquals(StatusType.RUNNING , status.getStatusType());

		status.setRunning(true);
		status.pause();
		assertEquals(StatusType.PAUSED_AFTER_EXECUTION , status.getStatusType());

		status.setRunning(false);
		assertEquals(StatusType.PAUSED , status.getStatusType());

		status.resume();
		assertEquals(StatusType.WAITING , status.getStatusType());

		status.setRunning(true);
		assertEquals(StatusType.RUNNING , status.getStatusType());

		status.setRunning(false);
		assertEquals(StatusType.WAITING , status.getStatusType());

		status.pause();
		assertEquals(StatusType.PAUSED , status.getStatusType());

		status.resume();
		status.setRunning(true);
		assertEquals(StatusType.RUNNING , status.getStatusType());

		status.kill();
		assertEquals(StatusType.KILLED_AFTER_EXECUTION , status.getStatusType());

		status.setRunning(false);
		assertEquals(StatusType.KILLED , status.getStatusType());

	}

	@Test
	public void testStatusExecutionCount() {
		final JobStatusImpl status = new JobStatusImpl("", "");

		assertEquals( 0  , status.getExecutionTimes() );
		assertEquals( 0  , status.getExecutionInError() );
		assertEquals( 0  , status.getMeanExecutionTimeMillisec() );

		final Date endDate = new Date();
		Date startDate = new Date( endDate.getTime() - 1000 );

		status.executionStarted(startDate);
		status.executionEnded(endDate);

		assertEquals( 1  , status.getExecutionTimes() );
		assertEquals( 0  , status.getExecutionInError() );
		assertEquals( 1000  , status.getMeanExecutionTimeMillisec() );
		assertEquals( startDate  , status.getLastExecutionStartDate() );
		assertEquals( endDate  , status.getLastExecutionEndDate() );

		status.executionStarted(startDate);
		status.executionEnded(endDate);

		assertEquals( 2  , status.getExecutionTimes() );
		assertEquals( 0  , status.getExecutionInError() );
		assertEquals( 1000  , status.getMeanExecutionTimeMillisec() );
		assertEquals( startDate  , status.getLastExecutionStartDate() );
		assertEquals( endDate  , status.getLastExecutionEndDate() );

		status.executionStarted(startDate);
		status.executionError(endDate);

		assertEquals( 3  , status.getExecutionTimes() );
		assertEquals( 1  , status.getExecutionInError() );
		assertEquals( 1000  , status.getMeanExecutionTimeMillisec() );
		assertEquals( startDate  , status.getLastExecutionStartDate() );
		assertEquals( endDate  , status.getLastExecutionEndDate() );

		startDate = new Date( endDate.getTime() - 7000 );

		status.executionStarted(startDate);
		status.executionEnded(endDate);

		assertEquals( 4  , status.getExecutionTimes() );
		assertEquals( 1  , status.getExecutionInError() );
		assertEquals( 2500  , status.getMeanExecutionTimeMillisec() );
		assertEquals( startDate  , status.getLastExecutionStartDate() );
		assertEquals( endDate  , status.getLastExecutionEndDate() );
	}


}
