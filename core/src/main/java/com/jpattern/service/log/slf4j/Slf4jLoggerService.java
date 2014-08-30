package com.jpattern.service.log.slf4j;

import com.jpattern.logger.ILogger;
import com.jpattern.logger.ILoggerCallback;
import com.jpattern.logger.Slf4JLogger;
import com.jpattern.logger.WrapperLoggerCallback;
import com.jpattern.logger.WrappersLoggerCallback;
import com.jpattern.service.log.ILoggerService;

/**
 * 
 * @author Francesco Cina'
 *
 * 4 Mar 2011
 */
public class Slf4jLoggerService implements ILoggerService {

	private static final long serialVersionUID = 1L;
	private final ILoggerService loggerService;

	public Slf4jLoggerService(ILoggerService loggerService) {
		this.loggerService = loggerService;
	}
	
	@Override
	public ILogger logger(Class<?> aClass) {
		return new Slf4JLogger(aClass, new WrapperLoggerCallback( loggerService.logger(aClass)) );
	}
	
	@Override
	public ILogger logger(Class<?> aClass, ILoggerCallback loggerCallback) {
		return new Slf4JLogger(aClass, new WrappersLoggerCallback( new ILogger[]{loggerService.logger(aClass), loggerCallback}) );
	}
	
	@Override
	public String getName() {
		return loggerService.getName();
	}

	@Override
	public void setTraceLoggerLevel() {
		loggerService.setTraceLoggerLevel();
	}

	@Override
	public void setDebugLoggerLevel() {
		loggerService.setDebugLoggerLevel();
	}

	@Override
	public void setInfoLoggerLevel() {
		loggerService.setInfoLoggerLevel();
	}

	@Override
	public void setWarnLoggerLevel() {
		loggerService.setWarnLoggerLevel();
	}

	@Override
	public void setErrorLoggerLevel() {
		loggerService.setErrorLoggerLevel();
	}

	@Override
	public void setOffLoggerLevel() {
		loggerService.setOffLoggerLevel();
	}

	@Override
	public void setTraceLoggerLevel(String classpath) {
		loggerService.setTraceLoggerLevel(classpath);
	}

	@Override
	public void setDebugLoggerLevel(String classpath) {
		loggerService.setDebugLoggerLevel(classpath);
	}

	@Override
	public void setInfoLoggerLevel(String classpath) {
		loggerService.setInfoLoggerLevel(classpath);
	}

	@Override
	public void setWarnLoggerLevel(String classpath) {
		loggerService.setWarnLoggerLevel(classpath);
	}

	@Override
	public void setErrorLoggerLevel(String classpath) {
		loggerService.setErrorLoggerLevel(classpath);
	}

	@Override
	public void setOffLoggerLevel(String classpath) {
		loggerService.setOffLoggerLevel(classpath);
	}

	@Override
	public void setLoggerLevel(String classpath, String loggerLevel) {
		loggerService.setLoggerLevel(classpath, loggerLevel);
	}

	@Override
	public void stopService() {
	}

}
