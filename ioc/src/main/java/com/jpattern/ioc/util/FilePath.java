package com.jpattern.ioc.util;

import java.io.File;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/feb/2011
 */
public class FilePath {

	public static String getPath(String path) {
		if (path==null) {
			return "";
		}
		return getPath( new File( path.replace("\\",File.separator).replace("/",File.separator) ));
		
	}
	
	public static String getPath(File file) {
		if (file==null) {
			return "";
		}
		String result = file.getAbsolutePath();
		if (result.contains(File.separator)) {
			result = result.substring(0, max(result.lastIndexOf(File.separator), 1 ));
		}
		if (!result.endsWith(File.separator)){
			result = result + File.separator;
		}
		return result;
		
	}
	
	protected static int max(int a, int b) {
		if (a>b) {
			return a;
		}
		return b;
	}

}
