package com.jpattern.service.log;

import java.util.Map.Entry;

import com.jpattern.core.IServiceBuilder;
import com.jpattern.service.log.file.ILogFileExecutorStrategy;
import com.jpattern.service.log.file.ILogFileWriter;
import com.jpattern.service.log.file.LogFileExecutor;
import com.jpattern.service.log.file.RollingLogFileExecutorStrategy;
import com.jpattern.service.mail.IMailService;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class SimpleLoggerServiceBuilder implements IServiceBuilder<ILoggerService> {

	private ILoggerConfig loggerConfig;
	private IMailService mailService;
	private final IExecutor executor;

	public SimpleLoggerServiceBuilder(ILoggerConfig loggerConfig, IMailService mailService) {
		this(loggerConfig, mailService, new NullExecutor());
	}
	
	public SimpleLoggerServiceBuilder(ILoggerConfig loggerConfig, IMailService mailService, IExecutor executor) {
		this.loggerConfig = loggerConfig;
		this.mailService = mailService;
		this.executor = executor;
	}

	public ILoggerService buildService() {
		
		//Configuro Logging su File
		ILogFileExecutorStrategy fileExecutorStrategy = new RollingLogFileExecutorStrategy(loggerConfig.getLogFile(), loggerConfig.getMaxFileSize(), ILogFileWriter.MEGABYTE, loggerConfig.getMaxFileBackupNumber());
		IExecutor fileExecutor = new LogFileExecutor(fileExecutorStrategy, executor);
		fileExecutor.setLoggerLevel(loggerConfig.getFileLoggerlevel());
		
		//Configuro Logging su console
		IExecutor consoleExecutor = new SystemOutExecutor( fileExecutor);
		consoleExecutor.setLoggerLevel(loggerConfig.getConsoleLoggerlevel());
		
		//Configuro Logging su Mail
		IExecutor mailExecutor = new MailExecutor(mailService, loggerConfig.getMailToList(), loggerConfig.getMailFrom(), loggerConfig.getMailSubject(), consoleExecutor);
		mailExecutor.setLoggerLevel(loggerConfig.getMailLoggerlevel());
		
		
		ILoggerService loggerService = new LoggerServiceBuilder( mailExecutor ).buildService(); 
		for( Entry<String,String> entry : loggerConfig.getClasspathLoggerLevelMap().entrySet() ) {
			loggerService.setLoggerLevel(entry.getKey(), entry.getValue());
		}
		
		return loggerService;
	}

}
