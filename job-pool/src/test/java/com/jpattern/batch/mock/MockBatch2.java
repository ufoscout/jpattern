package com.jpattern.batch.mock;

import com.jpattern.batch.AJob;
import com.jpattern.batch.logger.JobPoolLogger;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class MockBatch2 extends AJob {

	public MockBatch2(String name, String group) {
		super(name, group);
	}

	public static int EXECUTIONS;

	@Override
	public void execute() {
		JobPoolLogger.getLogger(getClass()).warn(getClass().getSimpleName() + " has been executed " + ++EXECUTIONS + " times");
	}

}
