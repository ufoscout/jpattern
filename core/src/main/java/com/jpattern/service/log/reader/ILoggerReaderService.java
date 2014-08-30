package com.jpattern.service.log.reader;

import java.io.Serializable;

import com.jpattern.core.IService;

/**
 * 
 * @author Francesco Cinà 07/ago/2009
 *
 */
public interface ILoggerReaderService extends IService, Serializable {
    
	IMessageReader messageReader();
	
	IMessageReader messageReader(IFilter aFilter);
}
