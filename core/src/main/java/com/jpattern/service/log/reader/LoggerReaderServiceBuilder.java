package com.jpattern.service.log.reader;

import com.jpattern.core.IServiceBuilder;

/**
 * @author Francesco Cinà 07/ago/2009
 */
public class LoggerReaderServiceBuilder implements IServiceBuilder<ILoggerReaderService> {

	private IQueueMessages _aQueue;

    public LoggerReaderServiceBuilder(IQueueMessages aQueue) {
        _aQueue = aQueue;
    }

    public ILoggerReaderService buildService() {
        return new LoggerReaderService(_aQueue);
    }

}
