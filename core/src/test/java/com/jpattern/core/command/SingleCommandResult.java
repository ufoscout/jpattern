package com.jpattern.core.command;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class SingleCommandResult {

	private boolean executed = false;
	private boolean rollbackExecuted = false;
	private final String name;
	
	SingleCommandResult(String name) {
		this.name = name;
	}
	
	public boolean isExecuted() {
		return executed;
	}
	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
	
	public boolean isRollbackExecuted() {
		return rollbackExecuted;
	}
	public void setRollbackExecuted(boolean rollbackExecuted) {
		this.rollbackExecuted = rollbackExecuted;
	}

	public String getName() {
		return name;
	}
	
}
