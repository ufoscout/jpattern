package com.jpattern.service.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.shared.util.StringUtil;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2010
 */
public class LoggerConfig implements ILoggerConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String MAIL_SEPARATOR = ";";
	
	private String logFile = "";
	private int maxFileBackupNumber = 0;
	private int maxFileSize = 0;
	private String fileLoggerlevel = ILoggerService.OFF;
	private String consoleLoggerlevel = ILoggerService.OFF;
	private String mailLoggerlevel = ILoggerService.OFF;
	private String mailFrom = "";
	private List<String> mailToList = new ArrayList<String>();
	private String mailSubject = "";

	private Map<String, String> classpathLoggerLevelMap = new HashMap<String, String>();;

	public int getMaxFileBackupNumber() {
		return maxFileBackupNumber;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public void setMaxFileBackupNumber(int maxFileBackupNumber) {
		this.maxFileBackupNumber = maxFileBackupNumber;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getFileLoggerlevel() {
		return fileLoggerlevel;
	}

	public void setFileLoggerlevel(String fileLoggerlevel) {
		this.fileLoggerlevel = fileLoggerlevel;
	}

	public String getConsoleLoggerlevel() {
		return consoleLoggerlevel;
	}

	public void setConsoleLoggerlevel(String consoleLoggerlevel) {
		this.consoleLoggerlevel = consoleLoggerlevel;
	}

	public String getMailLoggerlevel() {
		return mailLoggerlevel;
	}

	public void setMailLoggerlevel(String mailLoggerlevel) {
		this.mailLoggerlevel = mailLoggerlevel;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public List<String> getMailToList() {
		return mailToList;
	}

	public void setMailTo(String mailTo) {
		this.mailToList = new ArrayList<String>();
		String[] mails = StringUtil.tokenize(mailTo, MAIL_SEPARATOR);
		for (int i=0; i<mails.length; i++) {
			String mail = mails[i].trim();
			if (mail.length()>0) {
				this.mailToList.add(mail);
			}
		}
	}
	
	public void setMailToList(List<String> mailToList) {
		this.mailToList = mailToList;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public Map<String, String> getClasspathLoggerLevelMap() {
		return classpathLoggerLevelMap;
	}
	
	public void setClasspathLoggerLevelMap(Map<String, String> classpathLoggerlevelMap) {
		this.classpathLoggerLevelMap = classpathLoggerlevelMap;
	}
	
}
