package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/gen/2011
 * 
 * This is an element of a Chain of responsibility.
 * The method execute() is called only if the verifyCondition() returns true.
 * This element is not activated if a previous element of the same chain has been executed.
 */
public abstract class AChainElementBlocking extends AChainElement {
	
	protected AChainElementBlocking(IChainElement previousChainElement) {
		super(previousChainElement);
	}
	
	public final boolean tryExecution(IChainResult previousElementResult) {
		return !previousElementResult.isExecuted();
	}
}
