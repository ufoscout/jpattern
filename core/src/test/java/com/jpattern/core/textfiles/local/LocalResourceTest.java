package com.jpattern.core.textfiles.local;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseTest;
import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;


/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2010
 */
public class LocalResourceTest extends BaseTest {

	private static int COUNT = 0;
	private String path;

	protected void setUp() throws Exception {
		super.setUp();
		
		path = getTestOutputBasePath() + "/resource" + new Date().getTime() + COUNT++;
		File pathOut = new File(path);
		if (!pathOut.exists()) { pathOut.mkdirs(); }
		
		System.out.println("test path out: " + pathOut.getPath());
		IResource out = new LocalResource( pathOut.getPath() , CharacterEncoding.UTF_8 );
		assertTrue( out.isValid() );

		String fileContent = "riga 1\nriga 2\nriga 3\n"; 
		
		IFile fileIn1 = out.create("file1.txt", fileContent);
		assertTrue( fileIn1.exists() );
		
		IFile fileIn2 = out.create("file2.xml", fileContent);
		assertTrue( fileIn2.exists() );
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLocalPath1() throws Exception {
		LocalResource resource = new LocalResource( "" + Math.random() , CharacterEncoding.UTF_8 );
		
		assertFalse( resource.isValid() );
		assertEquals( 0 , resource.getFilenames().size() );
		
		String path = System.getProperty( "user.dir" );
		System.out.println("test path: " + path);
		resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
	}
	
	public void testLocalPath2() throws Exception {
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8);
		assertTrue( resource.isValid() );
		
		for (String filename : resource.getFilenames() ) {
			System.out.println("Trovato file: " + filename);
		}
		
		assertEquals( 2 , resource.getFilenames().size() );
		assertEquals( "file1.txt" , resource.getFilenames().get(0) );
		assertEquals( "file2.xml" , resource.getFilenames().get(1) );
		
		
		// BEGIN - Create new file
		IFile newFile = resource.create( "newFile.nuovo" , "");
		System.out.println("path nuovo file: " + newFile.getPath() );
		assertEquals( 3 , resource.getFilenames().size() );
		assertEquals( "file1.txt" , resource.getFilenames().get(0) );
		assertEquals( "file2.xml" , resource.getFilenames().get(1) );
		assertEquals( "newFile.nuovo" , resource.getFilenames().get(2) );
		assertEquals( "newFile.nuovo" , newFile.getName() );
		System.out.println("newFile.getPath() = " + newFile.getPath());
		assertTrue( newFile.getPath().replace("\\", "/").contains(path) );
		
		IFile newFile2 = resource.create( "newFile.nuovo" , "");
		System.out.println("path nuovo file: " + newFile2.getPath() );
		assertEquals( 3 , resource.getFilenames().size() );
		assertEquals( "" , newFile2.getName() );
		assertFalse( newFile2.getPath().replace("\\", "/").contains(path) );
		// END - Create new file		
		
		
		// BEGIN - delete file	
		assertFalse( resource.delete("NON_ESISTE.CIAO") );
		assertEquals( 3 , resource.getFilenames().size() );
		assertTrue( resource.delete("newFile.nuovo") );
		assertEquals( 2 , resource.getFilenames().size() );
		assertEquals( "file1.txt" , resource.getFilenames().get(0) );
		assertEquals( "file2.xml" , resource.getFilenames().get(1) );
		// END - delete file
		
	}
	
	
	public void testLocalPath3() throws Exception {
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		for (String filename : resource.getFilenames() ) {
			System.out.println("Trovato file: " + filename);
		}
		
		IFile file1 = resource.getFile( "file1.txt" );
		System.out.println(file1.getName());
		assertTrue( file1.exists() );
		assertEquals( "file1.txt" , file1.getName() );
		
		IFile file2 = resource.getFile( "NON_ESISTO.CIAO" );
		assertFalse( file2.exists() );
		assertEquals( "" , file2.getName() );
		
	}
	
	public void testReadWrite() throws Exception {
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		// BEGIN - Create new file
		IFile newFile = resource.create( "newFile.nuovo" , "prima riga\nseconda riga");
		assertTrue( newFile.exists() );
		// END - Create new file		
		
		
		// BEGIN READ FILE
		IFileReader fileReader = newFile.getFileReader();
		assertNotNull( fileReader );
		
		assertTrue( fileReader.hasNextLine() );
		assertTrue( fileReader.hasNextLine() );
		assertTrue( fileReader.hasNextLine() );
		assertTrue( fileReader.hasNextLine() );
		assertEquals( "prima riga" , fileReader.readLine() );
		
		assertTrue( fileReader.hasNextLine() );
		assertTrue( fileReader.hasNextLine() );
		assertEquals( "seconda riga" , fileReader.readLine() );
		
		assertFalse( fileReader.hasNextLine() );
		assertFalse( fileReader.hasNextLine() );
		assertEquals( "" , fileReader.readLine() );
		
		fileReader.close();
		// END READ FILE
		
		// BEGIN - delete file	
		assertTrue( resource.delete("newFile.nuovo") );
		// END - delete file
	}
	
	public void testName() throws Exception {
		
		String path = "/usr/local";		
		IResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertEquals( path , resource.getPath().replace("\\", "/") );
		assertEquals( "local" , resource.getName() );
		
		
		path = "/usr/local";		
		resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertEquals( path , resource.getPath().replace("\\", "/") );
		assertEquals( "local" , resource.getName() );
		
		path = "/local";		
		resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertEquals( path , resource.getPath().replace("\\", "/") );
		assertEquals( "local" , resource.getName() );
		
		path = "/usr/local/";		
		resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertEquals( "/usr/local" , resource.getPath().replace("\\", "/") );
		assertEquals( "local" , resource.getName() );
		
	}
	
	public void testRename1() throws Exception {
		
		long now = new Date().getTime();
		String oldName = "oldFileName" + now;
		String newName = "newFileName" + now;
		
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		IFile oldfile = resource.create(oldName, "ciao" + now);
		assertTrue( oldfile.exists() );
		
		assertTrue( resource.rename(oldName, newName) );
		
		assertFalse( oldfile.exists() );
		
		IFile newfile = resource.getFile( newName );
		assertTrue( newfile.exists() );
		
		IFileReader filereader = newfile.getFileReader();
		assertEquals( "ciao" + now , filereader.getFullText() );
		filereader.close();
		
		assertTrue( resource.delete(newName) );
	}
	
}
