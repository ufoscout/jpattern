package com.jpattern.service.log.file;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public interface ILogFileWriter {
	
	int BYTE = 1;
	int KILOBYTE = 1024;
	int MEGABYTE = 1024*1024;

	void write(String text);

	public long getSize();
	
	boolean rename( String filename );
	
	boolean delete();
	
	void close();
	
	boolean exist();
}
