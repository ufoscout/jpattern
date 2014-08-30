package com.jpattern.service.log.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public class LogFileWriter implements ILogFileWriter {

	private File file;
	private boolean fileOpened = false;
	private PrintWriter filePrintWriter; 
	
	public LogFileWriter(String filepath) {
		file = new File( filepath );
	    fileOpened = true;
	}

	public void write(String text) {
		try {
			if (fileOpened) {
				initializePrintWriter();
				if ( filePrintWriter != null) {
					filePrintWriter.println( text );
				}
			}
		} catch (Exception e) {
		}
	}

	private void initializePrintWriter() {
		if ( filePrintWriter == null) {
			java.io.FileWriter fw;
			try {
				fw = new java.io.FileWriter ( file , true );
				BufferedWriter bw = new BufferedWriter(fw);
			    filePrintWriter = new PrintWriter(bw, true);
			} catch (IOException e) {
			}
		}
	}

	public long getSize() {
		if (fileOpened) {
			return file.length();
		}
		return 0;
	}
	
	public boolean rename( String filename ) {
		if ( fileOpened ) {
			close();
			File newfile = new File(filename);
			try {
				return file.renameTo( newfile );
			} 
			catch (Exception e) {
			}
		}
		return false;
	}
	
	public boolean delete() {
		if ( fileOpened ) {
			close();
			try {
				return file.delete();
			}
			catch (Exception e) {
			}
		}
		return false;
	}
	
	public void close() {
		try {
			if ( fileOpened && filePrintWriter != null) {
				filePrintWriter.close();
				fileOpened = false;
			}
		} catch (Exception e) {
		}
	}
	
	public boolean exist() {
		return file.exists();
	}
}
