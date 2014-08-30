package com.jpattern.core.textfiles;

import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.textfiles.FileRenameCommand;
import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 18/giu/2010
 */
public class FileRenameCommandTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRename1() throws Exception {
		
		long now = new Date().getTime();
		String oldName = "oldFileName" + now;
		String newName = "newFileName" + now;
		
		String path = getTestOutputBasePath();
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		IFile oldfile = resource.create(oldName, "ciao" + now);
		assertTrue( oldfile.exists() );
		
		ACommand<IProvider> command = new FileRenameCommand(new StringBuffer(oldName), new StringBuffer(newName), resource);
		assertTrue( command.exec(getProvider()).isValid() );
		
		assertFalse( oldfile.exists() );
		
		IFile newfile = resource.getFile( newName );
		assertTrue( newfile.exists() );
		
		IFileReader fileReader = newfile.getFileReader();
		assertEquals( "ciao" + now , fileReader.getFullText() );
		fileReader.close();
		
		assertTrue( resource.delete(newName) );
	}
	
	
	public void testRename2() throws Exception {
		
		long now = new Date().getTime();
		String oldName = "inesistentFileName" + now;
		String newName = "newFileName" + now;
		
		String path = getTestOutputBasePath();
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		IFile oldfile = resource.getFile(oldName);
		assertFalse( oldfile.exists() );
		
		ACommand<IProvider> command = new FileRenameCommand(new StringBuffer(oldName), new StringBuffer(newName), resource);
		assertFalse( command.exec(getProvider()).isValid() );

	}
	
	public void testRename3() throws Exception {
		
		long now = new Date().getTime();
		String oldName = "oldFileName" + now;
		String newName = "newFileName" + now;
		
		String path = getTestOutputBasePath();
		System.out.println("test path: " + path);
		LocalResource resource = new LocalResource( path , CharacterEncoding.UTF_8 );
		assertTrue( resource.isValid() );
		
		IFile oldfile = resource.create(oldName, "ciao" + now);
		assertTrue( oldfile.exists() );
		
		IFile newfile = resource.create(newName, "ciao" + now);
		assertTrue( newfile.exists() );
		
		ACommand<IProvider> command = new FileRenameCommand(new StringBuffer(oldName), new StringBuffer(newName), resource);
		assertFalse( command.exec(getProvider()).isValid() );
		
		assertTrue( oldfile.exists() );
		assertTrue( newfile.exists() );
		
		assertTrue( resource.delete(oldName) );
		assertTrue( resource.delete(newName) );
	}
	
}
