package com.jpattern.service.log;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2010
 */
public interface ILoggerConfig extends Serializable {

	String getFileLoggerlevel();
	String getConsoleLoggerlevel();
	String getMailLoggerlevel();
	
	String getLogFile();
	int getMaxFileBackupNumber();
	int getMaxFileSize();
	
	String getMailFrom();
	List<String> getMailToList();
	String getMailSubject();
	
	Map<String, String> getClasspathLoggerLevelMap();
}
