package com.jpattern.core.textfiles;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.core.textfiles.FileCopyCommand;
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
public class FileCopyCommandTest extends BaseApplicationTest {

	private IResource in;
	private IResource out;

	protected void setUp() throws Exception {
		super.setUp();
		
		File pathIn = new File( getTestOutputBasePath() + "/in");
		if (!pathIn.exists()) { pathIn.mkdirs(); }
		
		File pathOut = new File(getTestOutputBasePath() + "/out");
		if (!pathOut.exists()) { pathOut.mkdirs(); }
		
		System.out.println("test path in : " + pathIn.getPath());
		System.out.println("test path out: " + pathOut.getPath());
		in = new LocalResource( pathIn.getPath() , CharacterEncoding.UTF_8 );
		out = new LocalResource( pathOut.getPath() , CharacterEncoding.UTF_8 );
		
		assertTrue( in.isValid() );
		assertTrue( out.isValid() );
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testCopy1() throws Exception {
		
		String fileName = "testFileToCopy" + new Date().getTime();
		String fileContent = "riga 1\nriga 2\nriga 3\n"; 
		
		IFile fileIn = in.create(fileName, fileContent);
		assertTrue( fileIn.exists() );
		
		ACommand<IProvider> command = new FileCopyCommand(new StringBuffer(fileName) , in , new StringBuffer(fileName), out );
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );
		
		
		command = new FileCopyCommand(new StringBuffer(fileName) , in , new StringBuffer(fileName), out );
		result = command.exec(getProvider());
		assertFalse( result.isValid() );
		
		assertTrue( out.getFilenames().contains(fileName) );
		
		IFileReader destinationFileReader = out.getFile(fileName).getFileReader();
		
		assertEquals( fileContent , destinationFileReader.getFullText() );
		destinationFileReader.close();
		
		assertTrue( in.delete(fileName) );
		assertTrue( out.delete(fileName) );
		
	}

	
	public void testCopy2() throws Exception {
		
		String fileNameInput = "testFileToCopy" + new Date().getTime();
		String fileContent = "riga 1\nriga 2\nriga 3\n";
		String fileNameOutput = "testFileCopied" + new Date().getTime();
		
		IFile fileIn = in.create(fileNameInput, fileContent);
		assertTrue( fileIn.exists() );
		
		ACommand<IProvider> command = new FileCopyCommand(new StringBuffer(fileNameInput) , in , new StringBuffer(fileNameOutput), out );
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );
		
		
		command = new FileCopyCommand(new StringBuffer(fileNameInput) , in , new StringBuffer(fileNameOutput), out );
		result = command.exec(getProvider());
		assertFalse( result.isValid() );
		
		assertTrue( out.getFilenames().contains(fileNameOutput) );
		
		IFileReader destinationFileReader = out.getFile(fileNameOutput).getFileReader();
		
		assertEquals( fileContent , destinationFileReader.getFullText() );
		destinationFileReader.close();
		
		assertTrue( in.delete(fileNameInput) );
		assertTrue( out.delete(fileNameOutput) );
		
	}
}
