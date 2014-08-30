package com.jpattern.jobexecutor.socket.starter;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public interface IAdminSocketPortReader extends Serializable {

	int getPort() throws Exception;

}
