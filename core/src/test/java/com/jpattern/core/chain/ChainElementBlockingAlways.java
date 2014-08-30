package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ChainElementBlockingAlways extends AChainElementBlocking {

	private final StringBuffer active;
	public static String NAME = "ALWAYS"; 

	public ChainElementBlockingAlways(StringBuffer active, IChainElement chain) {
		super(chain);
		this.active = active;
	}

	@Override
	public void execute() {
		active.append(NAME);
	}

	@Override
	public boolean verifyCondition() {
		return true;
	}

}
