package com.jpattern.gwt.client.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class SysoutLogger implements ILogger {

	private final String name;

	public SysoutLogger(String name) {
		this.name = name;
	}

	@Override
	public void trace(String methodName, String message) {
		System.out.println(format("TRACE", methodName, message));
	}

	@Override
	public void debug(String methodName, String message) {
		System.out.println(format("DEBUG", methodName, message));
	}

	@Override
	public void info(String methodName, String message) {
		System.out.println(format("INFO ", methodName, message));
	}

	@Override
	public void warn(String methodName, String message) {
		System.out.println(format("WARN ", methodName, message));
	}

	@Override
	public void error(String methodName, String message, Throwable e) {
		System.out.println(format("ERROR", methodName, message));
		e.printStackTrace();
	}

	private String format(String level, String methodName, String message) {
		return "[" + level + "] [" + name + "] [" + methodName + "] [" + message + "]";
	}
}
