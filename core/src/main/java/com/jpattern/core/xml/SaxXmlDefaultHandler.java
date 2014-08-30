package com.jpattern.core.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Francesco Cina'
 * 
 *         16/giu/2010
 */
public class SaxXmlDefaultHandler extends DefaultHandler {

	private IXmlReaderStrategy xmlParserStrategy;
	private boolean trimValues;
	private StringBuffer stringBuffer;

	public SaxXmlDefaultHandler(IXmlReaderStrategy xmlParserStrategy, boolean trimValues) {
		this.xmlParserStrategy = xmlParserStrategy;
		this.trimValues = trimValues;
	}

	@Override
	public void startDocument() throws SAXException {
		xmlParserStrategy.startDocument();
		stringBuffer = new StringBuffer();
	}

	@Override
	public void endDocument() throws SAXException {
		xmlParserStrategy.endDocument();
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
		xmlParserStrategy.addText( clean(stringBuffer.toString()) );
		stringBuffer = new StringBuffer();
		xmlParserStrategy.startNewElement( clean(localName) );
		
		// Also, let's print the attributes if there are any...
		for (int i = 0; i < attributes.getLength(); i++) {
			xmlParserStrategy.addAttribute( clean(attributes.getLocalName(i)) , clean(attributes.getValue(i)) );
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		xmlParserStrategy.addText( clean(stringBuffer.toString()) );
		stringBuffer = new StringBuffer();
		xmlParserStrategy.endElement( clean(localName) );
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		stringBuffer.append(ch, start, length);
	}

	
	protected String clean(String text) {
		if (text == null) {
			return "";
		}
		if (trimValues) {
			return text.trim();
		}
		return text;
	}
}
