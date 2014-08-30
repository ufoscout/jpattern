package com.jpattern.logger;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface ILogger extends Serializable {

	void trace(String method, String message);

	void debug(String method, String message);

	void info(String method, String message);

	void warn(String method, String message);

	void error(String method, String message);

	void error(String method, String message, Throwable e);

	void trace(String message);

	void debug(String message);

	void info(String message);

	void warn(String message);

	void error(String message);

	void error(String message, Throwable e);
}
