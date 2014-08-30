package com.jpattern.core.exception;

/**
 * This exception is thrown when a command is executed before the injection of a valid provider;
 * The provider must be injected using the visit(IProvider provider) method
 * 
 * @author Francesco Cina'
 *
 * 16/set/2011
 */
public class NullProviderException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullProviderException() {
		super("The service provider is null. Before the execution of the command, you must inject a valid Service Provider using the visit() method.");
	}
}
