package com.jpattern.service.log.reader;

import java.util.Iterator;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/ago/2009
 */
public class NullIterator implements Iterator<String> {

	public boolean hasNext() {
		return false;
	}

	public String next() {
		return "";
	}

	public void remove() {
	}

}
