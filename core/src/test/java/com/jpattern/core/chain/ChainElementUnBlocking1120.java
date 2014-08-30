package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ChainElementUnBlocking1120 extends AChainElementUnblocking {

	private final int value;
	private final StringBuffer active;
	public static int MIN_VALUE = 11;
	public static int MAX_VALUE = 20;
	public static String NAME = "11_20"; 

	public ChainElementUnBlocking1120(int value, StringBuffer active, IChainElement chain) {
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
