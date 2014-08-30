package com.jpattern.rest.action;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class ActionWrapper<T extends IAction> implements IActionWrapper<T> {

	private final String relativePath;
	private final T action;

	public ActionWrapper (T action , String relativePath) {
		this.action = action;
		this.relativePath = relativePath;
	}
	
	@Override
	public T action() {
		return action;
	}

	@Override
	public String relativePath() {
		return relativePath;
	}

}
