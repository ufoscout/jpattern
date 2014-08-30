package com.jpattern.jobexecutor.socket;

import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/mar/2010
 */
public interface ICommunicationClientStrategy extends Serializable {

	String read() throws IOException;
	
	void write(String message);
	
}
