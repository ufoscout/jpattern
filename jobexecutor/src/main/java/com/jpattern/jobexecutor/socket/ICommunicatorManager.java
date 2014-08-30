package com.jpattern.jobexecutor.socket;

import java.io.Serializable;

import com.jpattern.jobexecutor.iostream.ICommunicationChannel;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/mar/2010
 */
public interface ICommunicatorManager extends Serializable {
	
	String getName();
	
	void execute( ICommunicationChannel communicatioChannel );
	

	
}
