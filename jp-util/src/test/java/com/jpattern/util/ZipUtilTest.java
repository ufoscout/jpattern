package com.jpattern.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class ZipUtilTest extends BaseTest {

	private String source;
	private String dest;

	@Before
	public void setUp() throws Exception {
		source = getTestInputBasePath() + "/zip/testZip1.xlsx";
		dest = getTestOutputBasePath() + "/" + new Date().getTime() + "testZip1.zip";
		assertTrue( new File(source).exists());
		assertFalse( new File(dest).exists());
	}

	@Test
	public void testZip() throws Exception {
		final String[] files = new String[1];
		files[0] = source;
		final int compressionLevel = 9;
		ZipUtil.zipFiles(files, dest, true, compressionLevel);

		final File destFile = new File(dest);
		final File sourceFile = new File(source);

		assertTrue( destFile.exists() );
		assertTrue( sourceFile.exists() );
		assertTrue( destFile.length() < sourceFile.length() );
		assertTrue( destFile.length() > 0);
	}

}
