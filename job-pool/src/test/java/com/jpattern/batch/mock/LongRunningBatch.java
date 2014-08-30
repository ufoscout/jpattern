package com.jpattern.batch.mock;

import com.jpattern.batch.AJob;
import com.jpattern.batch.logger.JobPoolLogger;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class LongRunningBatch extends AJob {

	public Integer RUN = 0;
	public boolean STARTED = false;
	public boolean FINISHED = false;
	private final ILogger logger;

	public LongRunningBatch(final String name, final String group) {
		super(name, group);
		this.logger = JobPoolLogger.getLogger(this.getClass());
	}

	@Override
	public void execute() {
		synchronized (this.RUN) {
			++this.RUN;
			this.logger.warn( "Run is : " + this.RUN);
		}
		this.STARTED = true;
		try {
			Thread.sleep(500);
		} catch (final InterruptedException e) {

		} finally {
			this.logger.info("EXECUTION FINISHED");
			this.FINISHED = true;
		}
	}

}
