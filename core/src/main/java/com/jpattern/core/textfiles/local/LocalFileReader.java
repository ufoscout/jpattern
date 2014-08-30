package com.jpattern.core.textfiles.local;


import java.io.File;
import java.util.Scanner;

import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public class LocalFileReader implements IFileReader {

	private Scanner scanner;

	public LocalFileReader(File file, CharacterEncoding encodingCharset) {
		try {
			scanner = new Scanner(file , encodingCharset.getCharset() );
		} catch (Exception e) {
			e.printStackTrace();
//			( (ILoggerService) SystemProxy.proxy.getProvider().getService(IMailerServicesName.LOGGERSERVICE) ).logger(getClass()).error("LocalFileReader", "filenotFound", e);
		}
	}
	
	public boolean hasNextLine() {
		if ( scanner == null) {
			return false;
		}
		return (scanner.hasNextLine());
	}

	public String readLine() {
		String result = "";
		if ( hasNextLine() ) {
			result = scanner.nextLine();
		}
		return result;
	}

	public void close() {
		if ( scanner != null) {
			scanner.close();
		}
	}

	public String getFullText() {
		StringBuffer result = new StringBuffer();
		if ( hasNextLine() ) {
			result.append( readLine() );
			while (hasNextLine()) {
				result.append("\n");
				result.append( readLine() );
			}
		}
		return result.toString();
	}

}
