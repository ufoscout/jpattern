package com.jpattern.jobexecutor.iostream;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public interface IOutputWriter extends Serializable {

	void write(String message);
	
}
