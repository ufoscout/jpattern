package com.jpattern.core.textfiles.local;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.IFileWriter;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;


/**
 * 
 * @author Francesco Cina'
 *
 * 11/giu/2010
 */
public class LocalFileWriterTest extends BaseApplicationTest {

	private static int COUNT = 0;
	private String filename;
	private IResource resource;
	
	protected void setUp() throws Exception {
		super.setUp();
		File pathIn = new File("target/resource/in");
		if (!pathIn.exists()) { pathIn.mkdirs(); }
		
		resource = new LocalResource( pathIn.getPath() , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		filename = "LocalFileWriterTest" + new Date().getTime() + COUNT++;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		assertTrue( resource.delete(filename) );
	}
	
	public void testWriter() throws Exception {
		
		
		IFile file = resource.create(filename, "");
		assertTrue( file.exists() );
		
		// SCRIVO FILE
		IFileWriter fileWriter = file.getFileWriter( false );
		assertTrue( fileWriter.println("riga1") );
		assertTrue( fileWriter.println("riga 2") );
		assertTrue( fileWriter.println("") );
		assertTrue( fileWriter.println("riga   3") );
		assertTrue( fileWriter.println("") );
		assertTrue( fileWriter.close() );
		
		IFileReader fileReader = file.getFileReader();
		assertEquals( "riga1\nriga 2\n\nriga   3\n" , fileReader.getFullText() );
		fileReader.close();
		
		//SOVRASCRIVO FILE
		fileWriter = file.getFileWriter( false );
		assertTrue( fileWriter.println("riga1") );
		assertTrue( fileWriter.close() );
		
		fileReader = file.getFileReader();
		assertEquals( "riga1" , fileReader.getFullText() );
		fileReader.close();
		
		
		//APPENDO IN CODA AL FILE
		fileWriter = file.getFileWriter( true );
		assertTrue( fileWriter.println("append") );
		assertTrue( fileWriter.close() );
		
		fileReader = file.getFileReader();
		assertEquals( "riga1\nappend" , fileReader.getFullText() );
		fileReader.close();
	}

}
