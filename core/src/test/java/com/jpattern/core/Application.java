package com.jpattern.core;

import com.jpattern.logger.ILogger;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.LoggerServiceBuilder;
import com.jpattern.service.log.SystemOutExecutor;
import com.jpattern.service.log.file.ILogFileExecutorStrategy;
import com.jpattern.service.log.file.ILogFileWriter;
import com.jpattern.service.log.file.LogFileExecutor;
import com.jpattern.service.log.file.RollingLogFileExecutorStrategy;
import com.jpattern.service.log.reader.IQueueMessages;
import com.jpattern.service.log.reader.LoggerReaderServiceBuilder;
import com.jpattern.service.log.reader.QueueExecutor;
import com.jpattern.service.log.reader.QueueMessages;
import com.jpattern.service.mail.MailConfig;
import com.jpattern.service.mail.MailSender;
import com.jpattern.service.mail.MailServiceBuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * Feb 14, 2012
 */
public class Application implements IApplication {

	private final String applicationName;
	private ILogger logger;
	private final int mailServerPort;
	private final SystemProvider systemProvider;

	public Application(SystemProvider systemProvider, String applicationName, int mailServerPort) {
		this.systemProvider = systemProvider;
		this.applicationName = applicationName;
		this.mailServerPort = mailServerPort;
	}
	
	@Override
	public void startApplication() throws Exception {

//		IMainConfigBean mainConfig = (IMainConfigBean) new ContextBeanFactory(configFile()).get("realmmainconfig");

		
		// -- BEGIN - Configurazione LoggerService e LoggerReaderService
		String logfile = "target/log/logger.log";
		int maxFileBackupNumber = 10;
		int maxFileSize = 5;
		
		ILogFileExecutorStrategy fileExecutorStrategy = new RollingLogFileExecutorStrategy(logfile, maxFileSize, ILogFileWriter.MEGABYTE, maxFileBackupNumber);
		IExecutor fileExecutor = new LogFileExecutor(fileExecutorStrategy);
		
		IExecutor executor = new SystemOutExecutor( fileExecutor);

		IQueueMessages queueMessages = new QueueMessages(10);
        QueueExecutor queueExecutor = new QueueExecutor(queueMessages, executor);
		
        systemProvider.setLoggerServiceBuilder(new LoggerServiceBuilder( queueExecutor ));
        systemProvider.setLoggerReaderServiceBuilder(new LoggerReaderServiceBuilder( queueMessages));
		// -- END - Configurazione LoggerService e LoggerReaderService
		
		
		// -- BEGIN - Configurazione MailService
		MailConfig mailConfig = new MailConfig();
		mailConfig.setStmphost("localhost");
		mailConfig.setStmpport("" + mailServerPort);
		mailConfig.setRequiredAuthentication(true);
		mailConfig.setUsername("username");
		mailConfig.setPassword("password");
		
		systemProvider.setMailServiceBuilder(new MailServiceBuilder(new MailSender(mailConfig)));
		// -- END - Configurazione MailService
		
		// -- BEGIN - Cache Service Configuration
//		CacheManager cacheManager = new CacheManager(getClass().getResource("/test-ehcache.xml"));
//		applicationService.buildCacheService(new EhCacheServiceBuilder(new EhCacheServiceConfig(cacheManager, new InMemoryCacheStatisticsManager())));
		// -- END - Cache Service Configuration
		
		logger = systemProvider.getLoggerService().logger(this.getClass());
		logger.info("startApplication" , applicationName + " STARTED");

	}

	@Override
	public void stopApplication() {
		logger.info( "stopApplication" , applicationName + " STOPPED");
	}
	
}
