package com.jpattern.core.textfiles;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2010
 */
public class NullResource implements IResource {

	private static final long serialVersionUID = 1L;

	public boolean delete(String filename) {
		return false;
	}

	public IFile getFile(String filename) {
		return new NullFile();
	}

	public List<String> getFilenames() {
		return new ArrayList<String>();
	}

	public IFile create(String filename, String text) {
		return new NullFile();
	}

	public boolean isValid() {
		return false;
	}

	public IFile create(String filename, String text, CharacterEncoding encodingCharset) throws Exception {
		return new NullFile();
	}

	public IFile getFile(String filename, CharacterEncoding encodingCharset) {
		return new NullFile();
	}

	public String getName() {
		return "";
	}

	public String getPath() {
		return "";
	}

	public boolean rename(String filename, String newFilename) {
		return false;
	}

}
