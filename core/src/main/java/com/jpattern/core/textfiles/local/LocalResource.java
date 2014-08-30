package com.jpattern.core.textfiles.local;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileWriter;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.NullFile;
import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2010
 */
public class LocalResource implements IResource {
	
	private static final long serialVersionUID = 1L;
	private String path;
	private CharacterEncoding defaultEncodingCharset;
	
	public LocalResource(String path, CharacterEncoding defaultEncodingCharset) {
		if ( path == null ) {
			path = "";
		}
		if ( defaultEncodingCharset == null ) {
			defaultEncodingCharset = CharacterEncoding.UTF_8;
		}
		this.path = path;
		this.defaultEncodingCharset = defaultEncodingCharset;
	}
	
	public boolean delete(String filename) {
		if (isValid()) {
			File file = new File( path + "/" + filename );
			if ( file.exists() && file.isFile() ) {
				return file.delete();
			}
		}
		return false;
	}

	public IFile getFile(String filename, CharacterEncoding encodingCharset) {
		if (isValid()) {
			File file = new File( path + "/" + filename );
			if ( file.exists() && file.isFile() ) {
				return new LocalFile(file , encodingCharset);
			}
		}
		return new NullFile();
	}

	public IFile getFile(String filename) {
		return getFile( filename, defaultEncodingCharset );
	}
	
	public IFile create(String filename, String text) throws Exception {
		return create( filename, text, defaultEncodingCharset );
	}

	public IFile create(String filename, String text, CharacterEncoding encodingCharset) throws IOException {
		if ( isValid() ) {
			File file = new File( path + "/" + filename );
			if ( !file.exists() && file.createNewFile()) {
				write(file, text );
				IFile resultFile = new LocalFile(file , encodingCharset);
				IFileWriter fileWriter = resultFile.getFileWriter(false);
				fileWriter.println(text);
				fileWriter.close();
				return resultFile;
			}
		}
		return new NullFile();
	}
	
	public List<String> getFilenames() {
		List<String> filesList = new ArrayList<String>();
		if ( isValid() ) {
			File file = new File( path );
			for ( File children : file.listFiles() ) {
				if ( children.isFile() ) {
					filesList.add( children.getName() );
				}
			}
		}
		Collections.sort(filesList);
		return filesList;
	}

	protected void write(File file, String text) throws IOException {
	    FileOutputStream fout = new FileOutputStream( file );
	    PrintStream printStream = new PrintStream(fout);
	    printStream.println( text );
	    printStream.close();
	    fout.close();		
	}

	public boolean isValid() {
		File file = new File(path); 
		boolean valid = file.exists() && file.isDirectory();
		return valid;
	}

	public String getName() {
		
		File file = new File(path);
		return file.getName();
		
	}

	public String getPath() {
		File file = new File(path);
		return file.getPath();
	}

	public boolean rename(String filename, String newFilename) {
		if (isValid()) {
			File file = new File( path + "/" + filename );
			if ( file.exists() && file.isFile() ) {
				return file.renameTo( new File( path + "/" + newFilename ) );
			}
		}
		return false;
	}

	
}
