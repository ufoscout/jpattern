package com.jpattern.batch.core;

import com.jpattern.batch.AJob;

/**
 * 
 * @author cinafr
 *
 */
public class NullJob extends AJob {

	public NullJob() {
		super("", "");
	}

	@Override
	public void execute() {
	}

}
