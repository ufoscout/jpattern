package com.jpattern.jobexecutor.socket.starter;

/**
 * 
 * @author Francesco Cina'
 * 
 *         10/ago/2010
 */
public class SimpleAdminSocketPortReader implements IAdminSocketPortReader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int port;

	public SimpleAdminSocketPortReader(int port) {
		this.port = port;
	}
	
	public int getPort() throws Exception {
		return port;
	}

}
