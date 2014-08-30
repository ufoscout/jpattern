package com.jpattern.service.log.file;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public interface ILogFileExecutorStrategy extends Serializable {

	ILogFileWriter getFileWriter();
	
}
