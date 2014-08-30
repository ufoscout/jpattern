package com.jpattern.core.textfiles;

import java.io.Serializable;
import java.util.List;

import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2010
 */
public interface IResource extends Serializable {
	
	List<String> getFilenames();
	
	IFile getFile(String filename);
	
	IFile getFile(String filename, CharacterEncoding encodingCharset);
	
	IFile create(String filename, String text) throws Exception;
	
	IFile create(String filename, String text, CharacterEncoding encodingCharset) throws Exception;
	
	String getPath();
	
	String getName();
	
	boolean delete(String filename);
	
	boolean rename(String filename, String newFilename);
	
	boolean isValid();

}
