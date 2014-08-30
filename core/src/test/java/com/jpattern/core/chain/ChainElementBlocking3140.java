package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ChainElementBlocking3140 extends AChainElementBlocking {

	private final int value;
	private final StringBuffer active;
	public static int MIN_VALUE = 31;
	public static int MAX_VALUE = 40;
	public static String NAME = "31_40"; 

	public ChainElementBlocking3140(int value, StringBuffer active, IChainElement chain) {
		super(chain);
		this.value = value;
		this.active = active;
	}

	@Override
	public void execute() {
		active.append(NAME);
	}

	@Override
	public boolean verifyCondition() {
		return ( (value>=MIN_VALUE) && (value<=MAX_VALUE));
	}

}
