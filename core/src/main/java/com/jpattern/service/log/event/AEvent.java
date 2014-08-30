package com.jpattern.service.log.event;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.service.log.IExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public abstract class AEvent implements IEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ITrigger trigger;
	private String name;
	private List<String> ignoredPathList = new ArrayList<String>();
	
	public AEvent(String name ) {
		this.name = name;
	}

	public abstract void execute(IExecutor executor);
	
	public void fire() {
		getTrigger().fire( this );
	}

	public void acceptITrigger(ITrigger trigger) {
		this.trigger = trigger;
	}

	private ITrigger getTrigger() {
		if ( trigger == null ) {
			trigger = new NullTrigger();
		}
		return trigger;
	}

	public String getName() {
		return name;
	}
	
	public void addIgnoredPath(String classpath) {
		if ( classpath == null) {
			return;
		}
		removeIgnoredPath( classpath );
		ignoredPathList.add( classpath ); 
	}
	
	public void removeIgnoredPath(String classpath) {
		if ( classpath == null) {
			return;
		}
		if (ignoredPathList.contains(classpath) ) {
			ignoredPathList.remove( classpath ); 
		}
	}
	
	public boolean isIgnoredPath( String classpath) {
		boolean result = false;
		for (String classpathIgnored : ignoredPathList) {
			if ( classpath.startsWith( classpathIgnored ) ) {
				result = true;
			}
		}
		return result;
	}
}
