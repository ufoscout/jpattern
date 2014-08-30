package com.jpattern.core.textfiles.local;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.jpattern.core.textfiles.IFileWriter;
import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/giu/2010
 */
public class LocalFileWriter implements IFileWriter {

	private PrintWriter output;

	public LocalFileWriter(File file, CharacterEncoding encodingCharset, boolean append) {
		try {
			output = new PrintWriter( (new OutputStreamWriter(new FileOutputStream(file, append), encodingCharset.getCharset() )) , true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean close() {
		if (output != null) {
			try {
				output.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean println(String text) {
		
		if (output != null) {
			try {
				output.println(text);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
