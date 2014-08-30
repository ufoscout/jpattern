package com.jpattern.service.log.file;

import java.io.File;
import com.jpattern.core.BaseApplicationTest;
import com.jpattern.logger.ILogger;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.SystemOutExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;
import com.jpattern.service.log.file.ILogFileExecutorStrategy;
import com.jpattern.service.log.file.ILogFileWriter;
import com.jpattern.service.log.file.LogFileExecutor;
import com.jpattern.service.log.file.RollingLogFileExecutorStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public class FileExecutorTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
		File path = new File("target/temp");
		if (!path.exists()) {
			path.mkdirs();
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFileExecutor() throws Exception {
		
		String logfile = "target/temp/testLogFile.log";
		int maxFileBackupNumber = 10;
		int maxFileSize = 5;
		
		ILogFileExecutorStrategy fileExecutorStrategy = new RollingLogFileExecutorStrategy(logfile, maxFileSize, ILogFileWriter.KILOBYTE, maxFileBackupNumber);
		IExecutor executor = new LogFileExecutor(fileExecutorStrategy);
		executor = new SystemOutExecutor( executor);
		executor.execute( new DebugEvent());
		executor.execute( new TraceEvent());
		executor.execute( new InfoEvent());
		executor.execute( new WarnEvent());
		executor.execute( new ErrorEvent());
		
		assertTrue( new File(logfile).exists() );
	}
	
	public void testFileExecutor2() throws Exception {
		
		ILogger logger =  getProvider().getLoggerService().logger(this.getClass());
		
		logger.info("testFileExecutor2", "informazione test");
		logger.error("testFileExecutor2", "simulo un NullPointerException" , new NullPointerException() );
		
	}
	
}
