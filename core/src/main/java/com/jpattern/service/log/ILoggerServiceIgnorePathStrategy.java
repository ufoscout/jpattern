package com.jpattern.service.log;

import java.io.Serializable;


/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public interface ILoggerServiceIgnorePathStrategy extends Serializable {

	void ignorePath( ILoggerService loggerService, String path );
	
}
