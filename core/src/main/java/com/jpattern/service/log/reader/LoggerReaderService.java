package com.jpattern.service.log.reader;

/**
 * @author Francesco Cinà 07/ago/2009
 */
public class LoggerReaderService implements ILoggerReaderService {

	public static String LOGGER_READER_SERVICE = "loggerReaderService";
	private static final long serialVersionUID = 1L;

	private IQueueMessages _aQueue;

    public LoggerReaderService(IQueueMessages aInfoQueue) {
        _aQueue = aInfoQueue;
    }

    @Override
    public String getName() {
        return LOGGER_READER_SERVICE;
    }

	public IMessageReader messageReader() {
		return new MessageReader(_aQueue);
	}

	public IMessageReader messageReader(IFilter aFilter) {
		return new MessageFilteredReader( new MessageReader(_aQueue), aFilter);
	}

	@Override
	public void stopService() {
	}
	


}
