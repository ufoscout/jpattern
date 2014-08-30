package com.jpattern.core.xml;

import java.io.Writer;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.core.util.CharacterEncoding;
import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class XmlWriterCommand extends ACommand<IProvider> {

	private CharacterEncoding characterEncoding;
	private IXmlElement xmlElement;
	private IXmlWriterStrategy xmlWriterStrategy;

	public XmlWriterCommand(IXmlWriterStrategy xmlWriterStrategy, IXmlElement xmlElement, CharacterEncoding characterEncoding ) {
		this.xmlWriterStrategy = xmlWriterStrategy;
		this.characterEncoding = characterEncoding;
		this.xmlElement = xmlElement;
	}

	@Override
	protected void rollback(ACommandResult result) {
	}

	@Override
	protected void execute(ACommandResult result) {
		
		ILogger logger = getProvider().getLoggerService().logger(this.getClass());
		logger.info("execute", "Start Writing Response Xml");
		
		try {
	        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        Document document = documentBuilder.newDocument();
	
	        Element root = document.createElement( xmlElement.getName() );
	        document.appendChild(root);
	
	        buildElementRecursively(document, root, xmlElement);
	
	        //set up a transformer
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", 2);
	        Transformer transformer = transformerFactory.newTransformer();
	        //trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.ENCODING, characterEncoding.getCharset() );
	        
	
			//create the xml tree
	        Writer xmlWriter = xmlWriterStrategy.getWriter();
	        StreamResult streamResult = new StreamResult( xmlWriter );
	        DOMSource source = new DOMSource(document);
	        transformer.transform(source, streamResult);
	        xmlWriter.close();	        
	        
			logger.debug("execute", "End Writing Response Xml");
		
		} catch (Exception e) {
			result.addErrorMessage(new ErrorMessage(getClass().getName(), e.getMessage()));
			logger.error("execute", "", e);
		} finally {
			try {
				xmlWriterStrategy.close();
			} catch (Exception e) {
				result.addErrorMessage(new ErrorMessage(getClass().getName(), e.getMessage()));
			}
		}
	}

	
	private void buildElementRecursively(Document document, Element element, IXmlElement xmlElement) {
		
		// set attributes
		for ( String attributeName : xmlElement.getAttributesName()) {
			IXmlAttribute attribute = xmlElement.getAttribute(attributeName);
			element.setAttribute(attribute.getName(), attribute.getValue());
		}
		
		// set value
		if ( xmlElement.getValue().length() != 0 ) {
			//element.setTextContent( xmlElement.getValue() );
			element.appendChild( document.createTextNode( xmlElement.getValue() ) );
		}
		
        //create children element
		for ( String childName : xmlElement.getSubElementsName() ) {
			
			List<IXmlElement> childs = xmlElement.getSubElements(childName);
			
			for (IXmlElement childElement : childs) {
//				IXmlElement childElement = xmlElement.getSubElement(childName);
				Element child = document.createElement( childElement.getName() );
		        element.appendChild(child);
		        buildElementRecursively(document, child, childElement);
			}
	        
		}
	}
	
}
