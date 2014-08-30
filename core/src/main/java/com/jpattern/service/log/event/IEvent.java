package com.jpattern.service.log.event;

import java.io.Serializable;

import com.jpattern.service.log.IExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface IEvent extends Serializable {
	
	void fire();
	
	void execute( IExecutor executor );
	
	void acceptITrigger( ITrigger trigger );
	
	String getName();
	
	void addIgnoredPath(String classpath);
	
	void removeIgnoredPath(String classpath);
	
	public boolean isIgnoredPath( String classpath);
	
}
