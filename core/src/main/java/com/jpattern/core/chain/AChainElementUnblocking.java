package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/gen/2011
 * 
 * This is an element of a Chain of responsibility.
 * The method execute() is called only if the verifyCondition() returns true.
 * This element is always activated, even if a previous element of the same chain has been executed. 
 */
public abstract class AChainElementUnblocking extends AChainElement {
	
	protected AChainElementUnblocking(IChainElement previousChainElement) {
		super(previousChainElement);
	}
	
	public final boolean tryExecution(IChainResult previousElementResult) {
		return true;
	}

}