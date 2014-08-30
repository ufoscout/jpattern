package com.jpattern.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 21/lug/2010
 */
public class FileUtilTest extends BaseTest {

	private String source;
	private String dest;

	@Before
	public void setUp() throws Exception {
		source = getTestInputBasePath() + "/zip/testZip1.xlsx";
		dest = getTestOutputBasePath() + "/" + new Date().getTime() + "testZip1.zip";
		assertTrue( new File(source).exists());
		assertFalse( new File(dest).exists());
	}

	@After
	public void tearDown() throws Exception {
		final File destFile = new File(dest);
		if (destFile.exists()) {
			assertTrue(destFile.delete());
		}
	}

	@Test
	public void testRemovePath() throws Exception {
		String filenameWithPath = "directory" + File.separatorChar + "nomefile";
		assertEquals( "nomefile" , FileUtil.removePath(filenameWithPath) );

		filenameWithPath = "c:" + File.separatorChar + "directory" + File.separatorChar + "nomefile";
		assertEquals( "nomefile" , FileUtil.removePath(filenameWithPath) );

		filenameWithPath = File.separatorChar + "directory" + File.separatorChar + "nomefile";
		assertEquals( "nomefile" , FileUtil.removePath(filenameWithPath) );
	}

	@Test
	public void testRemoveExtension() throws Exception {
		String nomefile = "ciao.txt";
		assertEquals( "ciao" , FileUtil.removeExtension(nomefile) );

		nomefile = "ciao.t";
		assertEquals( "ciao" , FileUtil.removeExtension(nomefile) );

		nomefile = "ciao";
		assertEquals( "ciao" , FileUtil.removeExtension(nomefile) );
	}

	@Test
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
