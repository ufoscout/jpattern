package com.jpattern.service.log;


import com.jpattern.core.IService;
import com.jpattern.logger.ILoggerFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface ILoggerService extends IService, ILoggerFactory {

	String TRACE = "trace";
	String DEBUG = "debug";
	String INFO = "info";
	String WARNING = "warning";
	String ERROR = "error";
	String OFF = "off";
	
	void setTraceLoggerLevel();
	void setDebugLoggerLevel();
	void setInfoLoggerLevel();
	void setWarnLoggerLevel();
	void setErrorLoggerLevel();
	void setOffLoggerLevel();
	
	void setTraceLoggerLevel( String classpath );
	void setDebugLoggerLevel( String classpath );
	void setInfoLoggerLevel( String classpath );
	void setWarnLoggerLevel( String classpath );
	void setErrorLoggerLevel( String classpath );
	void setOffLoggerLevel( String classpath );
	
	void setLoggerLevel( String classpath, String loggerLevel );
	
}
