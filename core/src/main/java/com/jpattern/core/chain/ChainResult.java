package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ChainResult implements IChainResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean executed = false;
	
	public ChainResult() {
		this(false);
	}
	
	public ChainResult(boolean executed) {
		this.executed = executed;
	}

	@Override
	public boolean isExecuted() {
		return executed;
	}
	
	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

}
