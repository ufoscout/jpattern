package com.jpattern.core.textfiles;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/giu/2010
 */
public class FileWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private IFile file;

	public FileWrapper() {
	}
	
	public FileWrapper(IFile file) {
		this.file = file;
	}

	public IFile getFile() {
		if (file == null) {
			file = new NullFile();
		}
		return file;
	}

	public void setFile(IFile file) {
		this.file = file;
	}
	
}
