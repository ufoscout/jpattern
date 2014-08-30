package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ChainElementBlocking0010 extends AChainElementBlocking {

	private final int value;
	private final StringBuffer active;
	public static int MIN_VALUE = 0;
	public static int MAX_VALUE = 10;
	public static String NAME = "00_10"; 

	public ChainElementBlocking0010(int value, StringBuffer active) {
		this( value, active, new NullChainElement() );
	}
	
	public ChainElementBlocking0010(int value, StringBuffer active, IChainElement chain) {
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
