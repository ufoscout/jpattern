package com.jpattern.core.util;

import java.io.File;
import java.util.Date;

import com.jpattern.core.BaseApplicationTest;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class ZipUtilTest extends BaseApplicationTest {
	
	private String source;
	private String dest;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		source = getTestInputBasePath() + "/zip/testZip1.xlsx";
		dest = getTestOutputBasePath() + "/" + new Date().getTime() + "testZip1.zip";
		assertTrue( new File(source).exists());
		assertFalse( new File(dest).exists());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		File destFile = new File(dest);
		if (destFile.exists()) {
			assertTrue(destFile.delete());
		}
	}
	
	public void testZip() throws Exception {
		String[] files = new String[1];
		files[0] = source;
		int compressionLevel = 9;
		ZipUtil.zipFiles(files, dest, true, compressionLevel);
		
		File destFile = new File(dest);
		File sourceFile = new File(source);
		
		assertTrue( destFile.exists() );
		assertTrue( sourceFile.exists() );
		assertTrue( destFile.length() < sourceFile.length() );
		assertTrue( destFile.length() > 0);
	}

}
