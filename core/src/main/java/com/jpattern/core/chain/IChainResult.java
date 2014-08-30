package com.jpattern.core.chain;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 * 
 * Represent the result of a "Chain of Responsibility" execution
 */
public interface IChainResult extends Serializable {

	/**
	 * Whether an or more elements of the chain are been executed  
	 * @return true if one the element did the job;
	 *         false means that there isn't an element which can be activated at the current condition   
	 */
	boolean isExecuted();
	
}
