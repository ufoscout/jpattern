package com.jpattern.core.textfiles;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.core.textfiles.FileDeleteCommand;
import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IResource;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;


/**
 * 
 * @author Francesco Cina'
 *
 * 11/giu/2010
 */
public class FileDeleteCommandTest extends BaseApplicationTest {

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
	
	public void testFileDelete() throws Exception {
		
		String fileName = "fileTestDelete" + new Date().getTime();
		
		IFile fileCreated = in.create(fileName, "");
		
		assertTrue( fileCreated.exists() );
		
		StringBuffer fileNameSB = new StringBuffer( fileName );
		
		ACommand<IProvider> command = new FileDeleteCommand( in, fileNameSB );
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );

		result = command.exec(getProvider());
		assertFalse( result.isValid() );
		
		assertFalse( in.getFilenames().contains( fileName ) );
	}

}
