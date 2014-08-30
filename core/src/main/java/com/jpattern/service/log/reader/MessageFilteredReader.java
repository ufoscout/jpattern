package com.jpattern.service.log.reader;

public class MessageFilteredReader implements IMessageReader {

    private static final long serialVersionUID = 1L;

    private IMessageReader _messageReader;

    private IFilter _filter;

    public MessageFilteredReader(IMessageReader aMessageReader, IFilter aFilter) {
        _filter = aFilter;
        _messageReader = aMessageReader;
    }

    public IQueueMessage read() {
        return _filter.what(_messageReader.read());
    }

	public IQueueMessage read(long lastReadedMessageId) {
		return _filter.what(_messageReader.read(lastReadedMessageId));
	}

}
