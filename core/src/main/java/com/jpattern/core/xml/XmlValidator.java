package com.jpattern.core.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.jpattern.core.validator.AValidator;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/mag/2010
 */
public class XmlValidator extends AValidator {

	private InputStream xsdSchemaFilePath;
	private InputStream xmlFilePath;

	public XmlValidator(InputStream xmlFilePath, InputStream xsdSchemaFilePath,  List<IErrorMessage> aValidateMessages) {
		super(aValidateMessages);
		this.xmlFilePath = xmlFilePath;
		this.xsdSchemaFilePath = xsdSchemaFilePath;
	}

	@Override
	public void validate() throws Exception {
		StreamSource xsdStreamSource;
		StreamSource xmlStreamSource;
		try {
		      // define the type of schema - we use W3C:
		      String schemaLang = "http://www.w3.org/2001/XMLSchema";

		      // get validation driver:
		      SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

		      // create schema by reading it from an XSD file:
		      xsdStreamSource = new StreamSource(xsdSchemaFilePath);
		      Schema schema = factory.newSchema(xsdStreamSource);
		      javax.xml.validation.Validator validator = schema.newValidator();

		      // at last perform validation:
		      xmlStreamSource = new StreamSource(xmlFilePath);
		      validator.validate(xmlStreamSource);

		    } catch (SAXException ex) {
//		         add(new MessageInvalidate("XmlValidator" , "Error validating file: " + xmlFilePath + " --> " + ex.getMessage()));
		         add(new ErrorMessage("XmlValidator" , ex.getMessage()));
		    } catch (Exception ex) {
		    	 add(new ErrorMessage("XmlValidator" , ex.getMessage()));
		    	 throw ex;
		    } finally {
		    	closeStream(xsdSchemaFilePath);
		    	closeStream(xmlFilePath);
		    }

	}
	
	private void closeStream(InputStream stream) {
		if (stream!=null) {
			try {
				stream.close();
			} catch (IOException e) {
			}
		}
	}
	
}
