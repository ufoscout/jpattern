package com.jpattern.core.textfiles;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.core.textfiles.FileCreateCommand;
import com.jpattern.core.textfiles.FileWrapper;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;


/**
 * 
 * @author Francesco Cina'
 *
 * 10/giu/2010
 */
public class FileCreateCommandTest extends BaseApplicationTest {

	private IResource in;

	protected void setUp() throws Exception {
		super.setUp();
		
		File pathIn = new File(getTestOutputBasePath() + "/in");
		if (!pathIn.exists()) { pathIn.mkdirs(); }
		
		in = new LocalResource( pathIn.getPath() , CharacterEncoding.UTF_8);
		
		assertTrue( in.isValid() );
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreate() throws Exception {
		
		String fileName = "fileTestCreate" + new Date().getTime();
		
		FileWrapper newCreatedFile = new FileWrapper();
		StringBuffer fileNameSB = new StringBuffer( fileName );
		StringBuffer fileContent = new StringBuffer( "contenuto del file" );
		
		ACommand<IProvider> command = new FileCreateCommand( in, fileNameSB, fileContent, newCreatedFile );
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );

		assertTrue( in.getFilenames().contains( fileName ) );
		
		assertTrue( in.delete(fileName) );
		
		
	}
	
	
	public void testCreateTwice() throws Exception {
		
		String fileName = "fileTestCreate" + new Date().getTime();
		
		FileWrapper newCreatedFile = new FileWrapper();
		StringBuffer fileNameSB = new StringBuffer( fileName );
		StringBuffer fileContent = new StringBuffer( "contenuto del file" );
		
		ACommand<IProvider> command = new FileCreateCommand( in, fileNameSB, fileContent, newCreatedFile );
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );
		assertTrue( in.getFilenames().contains( fileName ) );

		result = command.exec(getProvider());
		assertFalse( result.isValid() );
		
		assertTrue( in.delete(fileName) );
		
	}
	
}
