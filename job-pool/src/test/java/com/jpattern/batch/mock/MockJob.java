package com.jpattern.batch.mock;

import com.jpattern.batch.AJob;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class MockJob extends AJob {

	private boolean mockJobExecuted = false ;
	private int correctExecution = 0;

	public MockJob(final String name, final String groupName) {
		super(name, groupName);
	}

	@Override
	public void execute() {
		this.mockJobExecuted = true;
		System.out.println(this.getName() + ": executed " + this.correctExecution++);
	}

	public void setExecuted(final boolean executed) {
		this.mockJobExecuted = executed;
	}

	public boolean isExecuted() {
		return this.mockJobExecuted;
	}

	public int getCorrectExecution() {
		return this.correctExecution;
	}

}
