package com.jpattern.core.textfiles;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public interface IFile extends Serializable {
	
	String getPath();
	
	String getName();
	
	IFileReader getFileReader();
	
	IFileWriter getFileWriter(boolean append);
	
	boolean exists();
	
}
