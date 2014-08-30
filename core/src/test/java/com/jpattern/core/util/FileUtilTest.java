package com.jpattern.core.util;

import java.io.File;
import java.util.Date;

import com.jpattern.core.util.FileUtil;
import com.jpattern.core.BaseTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/lug/2010
 */
public class FileUtilTest extends BaseTest {

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
	
	public void testRemovePath() throws Exception {
		String filenameWithPath = "directory" + File.separatorChar + "nomefile";
		assertEquals( "nomefile" , FileUtil.removePath(filenameWithPath) );
		
		filenameWithPath = "c:" + File.separatorChar + "directory" + File.separatorChar + "nomefile";
		assertEquals( "nomefile" , FileUtil.removePath(filenameWithPath) );
		
		filenameWithPath = File.separatorChar + "directory" + File.separatorChar + "nomefile";
		assertEquals( "nomefile" , FileUtil.removePath(filenameWithPath) );
	}
	
	public void testremoveExtension() throws Exception {
		String nomefile = "ciao.txt";
		assertEquals( "ciao" , FileUtil.removeExtension(nomefile) );
		
		nomefile = "ciao.t";
		assertEquals( "ciao" , FileUtil.removeExtension(nomefile) );
		
		nomefile = "ciao";
		assertEquals( "ciao" , FileUtil.removeExtension(nomefile) );
	}
	
	public void testGetExtension() throws Exception {
		String nomefile = "ciao.txt";
		assertEquals( "txt" , FileUtil.getExtension(nomefile) );
		
		nomefile = "ciao.t";
		assertEquals( "t" , FileUtil.getExtension(nomefile) );
		
		nomefile = "ciao.";
		assertEquals( "" , FileUtil.getExtension(nomefile) );
		
		nomefile = "ciao";
		assertEquals( "" , FileUtil.getExtension(nomefile) );
	}
}
