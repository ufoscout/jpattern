package com.jpattern.service.log.file;

import java.io.File;
import java.util.Date;

import com.jpattern.service.log.file.ILogFileExecutorStrategy;
import com.jpattern.service.log.file.RollingLogFileExecutorStrategy;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public class RollingFileExecutorTest extends TestCase {

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
	
	public void testRollingFile1() throws Exception {
		
		long uniqueid = new Date().getTime();
		String filepath = "target/temp/rollingfile" + uniqueid + ".log";
		int maxFileSize = 50;
		int unitOfMisure = 1;
		int maxFileNumber = 3;
		
		ILogFileExecutorStrategy rollingFile = new RollingLogFileExecutorStrategy( filepath, maxFileSize, unitOfMisure, maxFileNumber );
		
		assertFalse( new File( filepath ).exists() );
		rollingFile.getFileWriter().write("1234567890");
		rollingFile.getFileWriter().write("1234567890");
		
		assertTrue( new File( filepath ).exists() );
		assertFalse( new File( filepath + ".1" ).exists() );
		assertFalse( new File( filepath + ".2" ).exists() );
		
		rollingFile.getFileWriter().write("123456789012345678901234567890");
		rollingFile.getFileWriter().write("123456789012345678901234567890");
		
		assertTrue( new File( filepath ).exists() );
		assertTrue( new File( filepath + ".1" ).exists() );
		assertFalse( new File( filepath + ".2" ).exists() );
		
		rollingFile.getFileWriter().write("123456789012345678901234567890");
		rollingFile.getFileWriter().write("123456789012345678901234567890");
		
		assertTrue( new File( filepath ).exists() );
		assertTrue( new File( filepath + ".1" ).exists() );
		assertTrue( new File( filepath + ".2" ).exists() );
		assertTrue( new File( filepath + ".1" ).length() > 0 );
		assertTrue( new File( filepath + ".2" ).length() > 0 );
	}

	
	public void testRollingFile2() throws Exception {
		
		long uniqueid = new Date().getTime();
		String filepath = "target/temp/rollingfile" + uniqueid + ".log";
		int maxFileSize = 5;
		int unitOfMisure = 32;
		int maxFileNumber = 10;
		
		ILogFileExecutorStrategy rollingFile = new RollingLogFileExecutorStrategy( filepath, maxFileSize, unitOfMisure, maxFileNumber );
		
		System.out.println("Scrivo: " + maxFileSize * unitOfMisure * maxFileNumber + " volte");
		
		for (int i=0; i< (maxFileSize * unitOfMisure * maxFileNumber ) ; i++) {
			rollingFile.getFileWriter().write( "--------------------------" );
			rollingFile.getFileWriter().write( new Date().toString());
		}
		
		assertTrue( new File( filepath ).exists() );
		assertTrue( new File( filepath ).length() > 0 );
		for (int i=1; i<=maxFileNumber; i++) {
			assertTrue( new File( filepath + "." + i ).exists() );
			assertTrue( new File( filepath + "." + i ).length() > 0 );
		}
		assertFalse( new File( filepath + "." + (maxFileNumber+1) ).exists() );
		
	}
	
}
