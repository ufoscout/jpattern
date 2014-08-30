package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 * 
 * This is the NullObject relative to the IChainElement Interface
 */

public class NullChainElement implements IChainElement {

	@Override
	public IChainResult exec() {
		return new ChainResult();
	}

}
