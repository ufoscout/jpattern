package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public abstract class AChainElement implements IChainElement {

	private IChainElement previousChainElement;

	public AChainElement(IChainElement previousChainElement) {
		this.previousChainElement = previousChainElement;
	}

	protected IChainElement getPreviousChainElement() {
		if (previousChainElement == null) {
			previousChainElement = new NullChainElement();
		}
		return previousChainElement;
	}

	public final IChainResult exec() throws Exception {
		IChainResult previousResult = getPreviousChainElement().exec();
		if (verifyCondition() && tryExecution(previousResult)) {
			execute();
			return new ChainResult(true);
		}
		return previousResult;
	}
	
	public abstract boolean verifyCondition();

	public abstract void execute();
	
	public abstract boolean tryExecution(IChainResult previousElementResult);

}