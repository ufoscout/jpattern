package com.jpattern.batch;

/**
 * 
 * @author cinafr
 *
 */
public interface Job {

	String getName();

	String getGroup();

	void execute();

}
