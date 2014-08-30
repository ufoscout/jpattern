package com.jpattern.logger;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina
 *
 * 15/giu/2011
 */
public interface ILoggerFactory extends Serializable {

	ILogger logger( Class<?> aClass );
	
	ILogger logger( Class<?> aClass, ILoggerCallback loggerCallback );
	
}
