package com.jpattern.core.textfiles;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public interface IFileReader {

	String getFullText();	
	
	String readLine();
	
	boolean hasNextLine();
	
	void close();
}
