package com.jpattern.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.zip.ZipException;

import org.junit.Test;

import com.jpattern.BaseTest;

/**
 * 
 * @author Francesco Cina'
 *
 * Jul 22, 2012
 */
public class ResourceUtilTest extends BaseTest {

	private String CLASS_PATH_FILE_TEST_EXTENSION = "txt";
	private String CLASS_PATH_FILE_TEST = "CLASS_PATH_FILE_TEST." + CLASS_PATH_FILE_TEST_EXTENSION;

	@Test
	public void testClassNames() throws ZipException, IOException {
		final String[] classPathClassNames = ResourceUtil.getClasspathClassNames();
		assertNotNull(classPathClassNames);
		assertTrue( classPathClassNames.length > 0 );

		for (final String className : classPathClassNames) {
			System.out.println("Class found: " + className);
			assertNotNull(className);
		}

	}

	@Test
	public void testClasspathFileNames() throws ZipException, IOException {
		final String[] classPathFileNames = ResourceUtil.getClasspathFileNames();
		assertNotNull(classPathFileNames);
		assertTrue( classPathFileNames.length > 0 );

		boolean foundTestFile = false;
		for (final String fileName : classPathFileNames) {
			System.out.println("File found: " + fileName);
			assertNotNull(fileName);
			if (fileName.contains(CLASS_PATH_FILE_TEST)) {
				foundTestFile = true;
			}
		}
		assertTrue(foundTestFile);
	}

	@Test
	public void testClasspathFileNamesByExtension() throws ZipException, IOException {
		final String[] classPathFileNames = ResourceUtil.getClasspathFileNamesWithExtension(CLASS_PATH_FILE_TEST_EXTENSION);
		assertNotNull(classPathFileNames);
		assertTrue( classPathFileNames.length > 0 );

		boolean foundTestFile = false;
		for (final String fileName : classPathFileNames) {
			System.out.println("File found: " + fileName);
			assertNotNull(fileName);
			assertTrue(fileName.endsWith(CLASS_PATH_FILE_TEST_EXTENSION));
			if (fileName.contains(CLASS_PATH_FILE_TEST)) {
				foundTestFile = true;
			}
		}
		assertTrue(foundTestFile);
	}
}
