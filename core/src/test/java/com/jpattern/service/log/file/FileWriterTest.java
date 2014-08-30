package com.jpattern.service.log.file;

import java.io.File;


import com.jpattern.core.BaseTest;
import com.jpattern.service.log.file.LogFileWriter;
import com.jpattern.shared.util.UniqueId;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public class FileWriterTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
		
		File path = new File("target/temp");
		if (!path.exists()) {
			path.mkdirs();
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFileWriter1() throws Exception {
		
		String uniqueId = UniqueId.get();
		String filepath = "target/temp/testFileWriter.log" + uniqueId;
		
		System.out.println("Creo file: " + filepath);
		
		LogFileWriter fileWriter = new LogFileWriter( filepath );
		
		assertFalse( new File(filepath).exists() );
		
		assertEquals( 0l , fileWriter.getSize());
		
		fileWriter.write("ciao");
		fileWriter.write("ciao");
		
		assertTrue( fileWriter.getSize() > 0 );
		
		assertTrue( new File(filepath).exists() );
		
		fileWriter.close();
		
	}

	public void testFileWriter2() throws Exception {
		
		String uniqueId = UniqueId.get();
		String filepath = "target/temp/nonesiste/testFileWriter.log" + uniqueId;
		
		System.out.println("Creo file: " + filepath);
		
		LogFileWriter fileWriter = new LogFileWriter( filepath );
		
		assertEquals( 0l , fileWriter.getSize());
		
		fileWriter.write("ciao");
		fileWriter.write("ciao");
		
		assertEquals( 0l , fileWriter.getSize());
		
		assertFalse( new File(filepath).exists() );
		
		fileWriter.close();
		
	}
	
	
	public void testFileWriterAppend() throws Exception {
		
		String uniqueId = UniqueId.get();
		String filepath = "target/temp/testFileWriterAppend.log" + uniqueId;
		
		System.out.println("Creo file: " + filepath);
		
		LogFileWriter fileWriter1 = new LogFileWriter( filepath );
		assertEquals( 0l , fileWriter1.getSize());
		fileWriter1.write("ciao1ciao1");
		fileWriter1.write("ciao1ciao1");
		long filesize = fileWriter1.getSize();

		
		LogFileWriter fileWriter2 = new LogFileWriter( filepath );
		assertEquals( filesize , fileWriter2.getSize());
		fileWriter2.write("ciao2");
		assertTrue( fileWriter2.getSize() > filesize );
		
		assertTrue( new File(filepath).exists() );
		
		fileWriter1.close();
		fileWriter2.close();
	}
	
	
	public void testFileRename() throws Exception {
		
		String uniqueId = UniqueId.get();
		String filepath = "target/temp/testFileWriter.log" + uniqueId;
		
		System.out.println("Creo file: " + filepath);
		
		LogFileWriter fileWriter = new LogFileWriter( filepath );
		assertEquals( 0l , fileWriter.getSize());
		fileWriter.write("ciao file col vecchio nome!");
		
		assertTrue(fileWriter.rename( filepath + ".1" ));
		fileWriter.write("ciao file col nuovo nome!");
		
		fileWriter.close();
		
		assertTrue( new File( filepath + ".1" ).exists() );
	}
	
	public void testFileDelete() throws Exception {
		
		String uniqueId = UniqueId.get();
		String filepath = "target/temp/testDeleteFile.log" + uniqueId;
		
		System.out.println("Creo file: " + filepath);
		
		LogFileWriter fileWriter = new LogFileWriter( filepath );
		assertEquals( 0l , fileWriter.getSize());
		fileWriter.write("ciao file col vecchio nome!");
		
		
		assertTrue( new File(filepath).exists() );
		assertTrue( fileWriter.delete() );
		assertFalse( new File(filepath).exists() );
		
		fileWriter.close();
		
		LogFileWriter fileWriter2 = new LogFileWriter(filepath);
		fileWriter2.delete();
		
		fileWriter2.close();
		
	}
	
}
