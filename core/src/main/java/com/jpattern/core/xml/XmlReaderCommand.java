package com.jpattern.core.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.util.GenericWrapper;

/**
 * 
 * @author Francesco Cina'
 * 
 *         16/giu/2010
 */
public class XmlReaderCommand extends ACommand<IProvider> {

	private InputStream xmlFilePath;
	private IXmlReaderStrategy xmlParserStrategy;
	private GenericWrapper<Boolean> trimValues;
	private final ErrorHandler errorHandler;

	public XmlReaderCommand(InputStream xmlFilePath, GenericWrapper<Boolean> trimValues, IXmlReaderStrategy xmlParserStrategy, ErrorHandler errorHandler) {
		this.xmlFilePath = xmlFilePath;
		this.xmlParserStrategy = xmlParserStrategy;
		this.trimValues = trimValues;
		this.errorHandler = errorHandler;
	}

	@Override
	protected void rollback(ACommandResult result) {
	}

	@Override
	protected void execute(ACommandResult result) {
		ILogger logger = getProvider().getLoggerService().logger(XmlReaderCommand.class);
		logger.info("execute", "Starting build POJO from xml inputStream");
		try {
			// Create SAX 2 parser...
			XMLReader xr = XMLReaderFactory.createXMLReader();
			// Set the ContentHandler...
			xr.setContentHandler(new SaxXmlDefaultHandler( xmlParserStrategy , trimValues.getValue() ));
			// Set the ContentHandler...
			xr.setErrorHandler(errorHandler);
			// Parse the file...
			xr.parse(new InputSource(xmlFilePath));

		} catch (Exception e) {
			result.addErrorMessage( new ErrorMessage("XmlParserCommand", "Error parsing xml file " + xmlFilePath.toString()));
			logger.error("execute", "error parsing xml file " + xmlFilePath.toString(), e);
		} finally {
			try {
				if (xmlFilePath!=null) {
					xmlFilePath.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
