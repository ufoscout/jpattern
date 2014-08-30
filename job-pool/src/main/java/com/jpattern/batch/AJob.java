package com.jpattern.batch;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public abstract class AJob implements Job {

	private final String name;
	private final String group;

	public AJob(final String name, final String group) {
		this.name = name;
		this.group = group;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final String getGroup() {
		return this.group;
	}

}
