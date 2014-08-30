package com.jpattern.batch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.jpattern.batch.core.ConcurrentBatchTest;
import com.jpattern.batch.core.ThreadJobPoolExecuteJobTest;
import com.jpattern.batch.core.ThreadJobPoolModifyJobExecutionTest;
import com.jpattern.batch.core.ThreadJobPoolRemoveJobTest;
import com.jpattern.batch.core.ThreadJobPoolStartShutdownTest;
import com.jpattern.batch.core.ThreadJobPoolTest;
import com.jpattern.batch.execution.CronJobExecutionStrategyTest;
import com.jpattern.batch.execution.IntervallJobExecutionStrategyTest;
import com.jpattern.batch.execution.RunOnceJobExecutionStrategyTest;
import com.jpattern.batch.execution.TimedJobExecutionStrategyTest;
import com.jpattern.batch.status.JobStatusTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	RunOnceJobExecutionStrategyTest.class,
	CronJobExecutionStrategyTest.class,
	ThreadJobPoolStartShutdownTest.class,
	ConcurrentBatchTest.class,
	JobStatusTest.class,
	ThreadJobPoolTest.class,
	IntervallJobExecutionStrategyTest.class,
	TimedJobExecutionStrategyTest.class,
	ThreadJobPoolRemoveJobTest.class,
	ThreadJobPoolExecuteJobTest.class,
	ThreadJobPoolModifyJobExecutionTest.class
})
public class AllTests
{

}
