package com.jpattern.jobexecutor.socket.starter;

import java.io.File;

import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.socket.starter.IAdminSocketPortReader;
import com.jpattern.jobexecutor.socket.starter.PropertiesFileAdminSocketPortReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class PropertiesFileAdminSocketPortReaderTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testReadProperties() throws Exception {
		String key = "adminSocketPort";
		String filePath = TEST_FILES_BASE_PATH + "/properties.txt";
		assertTrue( new File(filePath).exists() );
		IAdminSocketPortReader reader = new PropertiesFileAdminSocketPortReader(filePath, key);
		assertEquals(12345,reader.getPort());
	}
}
