package com.jpattern.core.util;

import java.io.File;

/**
 * 
 * @author Francesco Cina'
 * 
 *         21/lug/2010
 */
public abstract class FileUtil {

	/**
	 * Restituisce il nome del file privo del path
	 * @param fName il nome completo del file (comprendente il path)
	 * @return il nome del file privo del path
	 */
	public static String removePath(String filename) {
		int pos = filename.lastIndexOf(File.separatorChar);
		if (pos > -1) {
			filename = filename.substring(pos + 1);
		}
		return filename;
	}

	public static String removeExtension(String filename) {
		int lastindex = filename.lastIndexOf('.');
		if ( lastindex != -1 ) {
			return filename.substring(0, lastindex);
		}
		return filename;
	}
	
	public static String getExtension(String filename) {
		int lastindex = filename.lastIndexOf('.');
		if ( lastindex != -1 && lastindex<filename.length()) {
			return filename.substring(lastindex+1, filename.length());
		}
		return "";
	}

}
