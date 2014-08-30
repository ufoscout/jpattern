package com.jpattern.batch.execution;

import java.util.Date;

import com.jpattern.batch.JobExecutionStrategy;
/**
 * 
 * Execute a job every prefixed interval of time
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class IntervalJobExecutionStrategy implements JobExecutionStrategy {

	private static final long serialVersionUID = 1L;
	private final long interval;
	private long lastTimeStamp;
	private final long delayMilliseconds;
	private boolean firstExecution = true;

	public IntervalJobExecutionStrategy( final long repeatEveryMilliseconds, final long delayMilliseconds) {

		this.interval = repeatEveryMilliseconds;
		this.delayMilliseconds = delayMilliseconds;
		this.lastTimeStamp = new Date().getTime();

	}

	@Override
	public boolean canExecute() {
		final long now = new Date().getTime();

		if (this.firstExecution) {

			if ( now >= (this.lastTimeStamp + this.delayMilliseconds)) {
				this.lastTimeStamp = now;
				this.firstExecution = false;
				return true;
			}
		} else {
			if ( now >= (this.lastTimeStamp + this.interval)) {
				this.lastTimeStamp = now;
				return true;
			}
		}
		return false;
	}

	@Override
	public long millisecondsBeforeNextExecution() {
		final long now = new Date().getTime();
		final long timeSinceLastExecution = now - this.lastTimeStamp;
		if (timeSinceLastExecution >= this.interval) {
			return 0l;
		}
		return this.interval - timeSinceLastExecution;
	}

	@Override
	public String asString() {
		return  this.getClass().getSimpleName() + " repeat every  " + this.interval + " milliseconds";
	}

	@Override
	public boolean hasOtherExecution() {
		return true;
	}
}
