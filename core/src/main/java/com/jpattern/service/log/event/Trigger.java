package com.jpattern.service.log.event;

import com.jpattern.service.log.IExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class Trigger implements ITrigger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IExecutor executor;

	public Trigger(IExecutor executor) {
		this.executor = executor;
	}
	
	public void fire(IEvent event) {
		event.execute(executor);
	}

}
