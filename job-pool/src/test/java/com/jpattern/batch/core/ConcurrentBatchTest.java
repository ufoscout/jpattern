package com.jpattern.batch.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobPool;
import com.jpattern.batch.execution.IntervalJobExecutionStrategy;
import com.jpattern.batch.mock.LongRunningBatch;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class ConcurrentBatchTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testConcurrentBatches() throws Exception {
		final ILogger logger = this.getLogger();
		final JobPool batchEngine = new ThreadJobPool("engine1");

		final LongRunningBatch batchDescription1 = new LongRunningBatch("batch1", "batchGroup1");

		batchEngine.start();

		batchEngine.addJob(batchDescription1, new IntervalJobExecutionStrategy(10, 0));

		while ( !batchDescription1.STARTED ) {
			logger.info("Waiting to start...");
		}
		while ( !batchDescription1.FINISHED ) {
			synchronized ( batchDescription1.RUN ) {
				logger.info("Run is: " + batchDescription1.RUN );
				assertTrue( batchDescription1.RUN == 1 );
				Thread.sleep(25);
			}

		}

		Thread.sleep(50);
		assertTrue( batchDescription1.RUN > 1 );

		batchEngine.shutdown(true);

	}

}
