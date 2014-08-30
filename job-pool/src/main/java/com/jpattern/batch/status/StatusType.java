package com.jpattern.batch.status;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public enum StatusType {

	KILLED("Killed"),
	KILLED_AFTER_EXECUTION("KilledAfterExecution"),
	PAUSED("Paused"),
	PAUSED_AFTER_EXECUTION("PausedAfterExecution"),
	RUNNING("Running"),
	WAITING("Waiting");

	private String code;

	StatusType(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
