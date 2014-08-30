package com.jpattern.batch.execution;

import java.text.ParseException;
import java.util.Date;

import com.jpattern.batch.JobExecutionStrategy;
import com.jpattern.batch.util.CronExpression;

/**
 * 
 * Execute a job based on a cron expression
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class CronJobExecutionStrategy implements JobExecutionStrategy {

	private static final long serialVersionUID = 1L;
	private final String cronExpression;
	private final CronExpression cron;
	private Date nextExecutionDate;

	public CronJobExecutionStrategy(final String cronExpression) {
		this.cronExpression = cronExpression;
		this.nextExecutionDate = new Date(0);
		try {
			this.cron = new CronExpression(cronExpression);
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean canExecute() {
		final Date now = new Date();
		if ( (now.compareTo(this.nextExecutionDate)>=0) && (this.cron.isSatisfiedBy(now)) ) {
			this.nextExecutionDate = this.cron.getNextValidTimeAfter(now);
			return true;
		}
		return false;
	}

	@Override
	public long millisecondsBeforeNextExecution() {
		final Date now = new Date();
		return (this.cron.getNextValidTimeAfter(now).getTime() - now.getTime()) + 1;
	}

	@Override
	public String asString() {
		return this.getClass().getSimpleName() + ". Cron expression: [" + this.cronExpression + "]";
	}

	@Override
	public boolean hasOtherExecution() {
		return true;
	}

}
