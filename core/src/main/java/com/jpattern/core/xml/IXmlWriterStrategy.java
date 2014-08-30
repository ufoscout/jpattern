package com.jpattern.core.xml;

import java.io.Writer;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public interface IXmlWriterStrategy {
	
	Writer getWriter() throws Exception ;
	
	void close()  throws Exception;
	
}
