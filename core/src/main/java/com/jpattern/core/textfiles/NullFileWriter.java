package com.jpattern.core.textfiles;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/giu/2010
 */
public class NullFileWriter implements IFileWriter {

	public boolean close() {
		return false;
	}

	public boolean println(String text) {
		return false;
	}

}
