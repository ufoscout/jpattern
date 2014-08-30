package com.jpattern.service.log.slf4j;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 5 Apr 2011
 */
public interface ILoggerBackendConfigurator extends Serializable {

	void configure();
}
