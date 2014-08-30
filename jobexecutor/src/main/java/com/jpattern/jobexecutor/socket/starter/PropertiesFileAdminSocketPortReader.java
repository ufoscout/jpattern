package com.jpattern.jobexecutor.socket.starter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class PropertiesFileAdminSocketPortReader implements IAdminSocketPortReader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String filePath;
	private String adminSocketPropertyKey;
	
	public PropertiesFileAdminSocketPortReader(String filePath, String adminSocketPropertyKey) {
		this.filePath = filePath;
		this.adminSocketPropertyKey = adminSocketPropertyKey;
	}
	
	
	public int getPort() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		FileInputStream fileInputStream = new FileInputStream(filePath);
		props.load(fileInputStream);
		String adminPort = props.getProperty(adminSocketPropertyKey);
		fileInputStream.close();
        return Integer.parseInt( adminPort );
	}

}
