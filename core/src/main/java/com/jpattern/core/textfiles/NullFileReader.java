package com.jpattern.core.textfiles;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public class NullFileReader implements IFileReader, Serializable {

	private static final long serialVersionUID = 1L;

	public boolean hasNextLine() {
		return false;
	}

	public String readLine() {
		return "";
	}

	public void close() {
	}

	public String getFullText() {
		return "";
	}

}
