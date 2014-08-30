package com.jpattern.jobexecutor.iostream;

import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public interface IInputReader extends Serializable {

	String read() throws IOException;
	
}
