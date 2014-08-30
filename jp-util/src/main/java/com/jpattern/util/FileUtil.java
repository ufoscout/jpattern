package com.jpattern.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 
 * @author Francesco Cina'
 * 
 *         21/lug/2010
 */
public abstract class FileUtil {

	/**
	 * @param An absolute or relative file path
	 * @return the file name without the full path
	 */
	public static String removePath(String filename) {
		final int pos = filename.lastIndexOf(File.separatorChar);
		if (pos > -1) {
			filename = filename.substring(pos + 1);
		}
		return filename;
	}

	/**
	 * 
	 * @param filename a file name
	 * @return the file name without extension
	 */
	public static String removeExtension(String filename) {
		final int lastindex = filename.lastIndexOf('.');
		if ( lastindex != -1 ) {
			return filename.substring(0, lastindex);
		}
		return filename;
	}

	/**
	 * 
	 * @param filename a file name
	 * @return the extension of the file, an empty String otherwise
	 */
	public static String getExtension(String filename) {
		final int lastindex = filename.lastIndexOf('.');
		if ( (lastindex != -1) && (lastindex<filename.length())) {
			return filename.substring(lastindex+1, filename.length());
		}
		return "";
	}

	/**
	 * Create the tree of Directories is some of them do not exist
	 * @param dirPath
	 * @return
	 */
	public static File createDirectoriesTreeIfNotExist(final String dirPath) {
		final File outputdir = new File(dirPath);
		if (!outputdir.exists()) {
			outputdir.mkdirs();
		}
		return outputdir;
	}

	/**
	 * Read a text file
	 * 
	 * @param in the file to read
	 * @param characterEncoding the character encoding of the file
	 * @return the String representing the content of the readed file
	 * @throws IOException
	 * @throws Exception
	 */
	public static String readFile(final File in, final Charset charset) throws IOException {
		final byte[] inbytes = new byte[(int) in.length()];
		final FileInputStream fin = new FileInputStream(in);
		fin.read(inbytes);
		fin.close();
		return new String(inbytes, charset);
	}

	/**
	 * write a file in a specific path
	 * 
	 * @param outputPath
	 * @param bytes
	 * @return the created file
	 * @throws IOException
	 * @throws Exception
	 */
	public static File writeFile(final String outputPath, final byte[] bytes) throws IOException  {
		final File result = new File(outputPath);
		final FileOutputStream fout = new FileOutputStream(result);
		fout.write(bytes);
		fout.close();
		return result;
	}
}
