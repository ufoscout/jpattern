package com.jpattern.core.chain;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/gen/2011
 * 
 * Interface for the "Chain Of Responsibility" pattern
 * 
 */
public interface IChainElement {

	/**
	 * Execute the chain
	 * @return the result of the execution  
	 */
	IChainResult exec() throws Exception;

}
