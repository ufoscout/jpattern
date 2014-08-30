package com.jpattern.core.textfiles.local;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;


/**
 * 
 * @author Francesco Cina'
 *
 * 11/giu/2010
 */
public class LocalFileReaderTest extends BaseApplicationTest {

	private static int COUNT = 0;
	private IResource resource;
	private String filename1;
	private String text;

	protected void setUp() throws Exception {
		super.setUp();
		
		File pathIn = new File("target/resource/in");
		if (!pathIn.exists()) { pathIn.mkdirs(); }
		
		resource = new LocalResource( pathIn.getPath() , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		filename1 = "fileNameTestRead" + new Date().getTime() + COUNT++;
		text = "riga1\nriga 2\nriga   3\n";
		resource.create(filename1, text);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		assertTrue( resource.delete(filename1) );
	}

	public void testReadFullFile() throws Exception {

		IFile file = resource.getFile(filename1);
		
		IFileReader fileReader = file.getFileReader();
		String fullContent = fileReader.getFullText();
		fileReader.close();
		
		System.out.println("readed content:");
		System.out.println(fullContent);
		assertTrue( text.equals(fullContent) );

	}
}
