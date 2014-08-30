package com.jpattern.core.textfiles.local;


import java.io.File;

import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.IFileWriter;
import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public class LocalFile implements IFile {

	private static final long serialVersionUID = 1L;
	private File file;
	private CharacterEncoding encodingCharset;

	public LocalFile(File file, CharacterEncoding encodingCharset) {
		this.file = file;
		if (this.file == null) {
			this.file = new File("");
		}
		this.encodingCharset = encodingCharset;
	}

	public String getPath() {
		return file.getAbsolutePath();
	}
	
	public String getName() {
		return file.getName();
	}

	public boolean exists() {
		return file.exists();
	}

	public IFileReader getFileReader() {
		return new LocalFileReader(file, encodingCharset);
	}

	public IFileWriter getFileWriter(boolean append) {
		return new LocalFileWriter(file, encodingCharset, append);
	}
	
}
