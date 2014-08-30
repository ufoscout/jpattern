package com.jpattern.core.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * @author Francesco Cina'
 *
 * 17/ago/2011
 */
public class NullErrorHandler implements ErrorHandler {

	@Override
	public void error(SAXParseException exception) throws SAXException {
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
	}

}
