package com.jpattern.core.textfiles;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public class NullFile implements IFile {

	private static final long serialVersionUID = 1L;

	public String getPath() {
		return "";
	}

	public String getName() {
		return "";
	}

	public boolean exists() {
		return false;
	}

	public IFileReader getFileReader() {
		return new NullFileReader();
	}
	
	public IFileWriter getFileWriter(boolean append) {
		return new NullFileWriter();
	}
}
