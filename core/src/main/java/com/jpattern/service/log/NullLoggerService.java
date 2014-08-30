package com.jpattern.service.log;

import com.jpattern.logger.ILogger;
import com.jpattern.logger.ILoggerCallback;
import com.jpattern.logger.NullLogger;
import com.jpattern.logger.NullLoggerCallback;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/giu/2010
 */
public class NullLoggerService implements ILoggerService {

	private static final long serialVersionUID = 1L;

	@Override
	public ILogger logger(Class<?> aClass) {
		return logger(aClass, new NullLoggerCallback());
	}

	@Override
	public ILogger logger(Class<?> aClass, ILoggerCallback loggerCallback) {
		return new NullLogger(loggerCallback);
	}
	
	public void setDebugLoggerLevel() {
	}

	public void setDebugLoggerLevel(String classpath) {
	}

	public void setErrorLoggerLevel() {
	}

	public void setErrorLoggerLevel(String classpath) {
	}

	public void setInfoLoggerLevel() {
	}

	public void setInfoLoggerLevel(String classpath) {
	}

	public void setTraceLoggerLevel() {
	}

	public void setTraceLoggerLevel(String classpath) {
	}

	public void setWarnLoggerLevel() {
	}

	public void setWarnLoggerLevel(String classpath) {
	}
	
	public void setOffLoggerLevel() {
	}

	public void setOffLoggerLevel(String classpath) {
	}

	public String getName() {
		return "";
	}

	public void setLoggerLevel(String classpath, String loggerLevel) {
	}

	@Override
	public void stopService() {
	}

}
