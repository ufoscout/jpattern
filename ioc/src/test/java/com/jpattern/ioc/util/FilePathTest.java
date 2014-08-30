package com.jpattern.ioc.util;

import java.io.File;
import java.io.IOException;

import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.util.FilePath;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/feb/2011
 */
public class FilePathTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testFilePath2() throws Exception {
		String userDir = System.getProperty("user.dir");
		assertTrue( userDir.length()>0 );
		
		comparePath( "/" , FilePath.getPath("/ciao.txt"));
		comparePath( userDir + File.separator, FilePath.getPath("ciao.txt"));
		comparePath( userDir + "/subdir/subsubdir/" , FilePath.getPath("subdir/subsubdir/ciao.txt"));
		comparePath( "/subdir/subsubdir/" , FilePath.getPath("/subdir/subsubdir/ciao.txt"));
	}
	
	public void testFilePath1() throws Exception {
		printFileInfo("ciao.txt");
		System.out.println();
		printFileInfo("/ciao.txt");
		System.out.println();
		printFileInfo("/");
		System.out.println();
		printFileInfo("c:\\ciao.txt");
		System.out.println();
		
	}
	
	public void comparePath(String path1, String path2) {
		String path1Clean = path1.replace("\\",File.separator).replace("/",File.separator);
		String path2Clean = path2.replace("\\",File.separator).replace("/",File.separator);
		if (path1Clean.contains(":")) {
			path1Clean = path1Clean.substring(path1Clean.lastIndexOf(":")+1, path1Clean.length());
		}
		if (path2Clean.contains(":")) {
			path2Clean = path2Clean.substring(path2Clean.lastIndexOf(":")+1, path2Clean.length());
		}
		System.out.println("Compare file paths: ");
		System.out.println("path 1: " + path1Clean);
		System.out.println("path 2: " + path2Clean);
		assertEquals( path1Clean , path2Clean);
		
	}

	void printFileInfo(String filename) throws IOException {
		File file1 = new File(filename);
		System.out.println("filename:      " + filename);
		System.out.println("AbsolutePath:  " + file1.getAbsolutePath());
		System.out.println("CanonicalPath: " + file1.getCanonicalPath());
		System.out.println("Parent:        " + file1.getParent());
		System.out.println("ParentFile:    " + file1.getParentFile());
		System.out.println("Path:          " + file1.getPath());
	}
}
