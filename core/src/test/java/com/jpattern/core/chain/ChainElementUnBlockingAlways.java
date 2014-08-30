package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ChainElementUnBlockingAlways extends AChainElementUnblocking {

	private final StringBuffer active;
	public static String NAME = "ALWAYS"; 

	public ChainElementUnBlockingAlways(StringBuffer active, IChainElement chain) {
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
