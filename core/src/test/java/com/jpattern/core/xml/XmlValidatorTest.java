package com.jpattern.core.xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


import com.jpattern.core.BaseTest;
import com.jpattern.core.validator.IValidator;
import com.jpattern.core.xml.XmlValidator;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/mag/2010
 */
public class XmlValidatorTest extends BaseTest {
	
	String xmlDirectory = "";
	String xmlSchema = "";
	String xmlValido1 = "";
	String xmlNonValido1 = "";
	String xmlValidoConSchema1 = "";
	
	private List<IErrorMessage> validateMessages;

	protected void setUp() throws Exception {
		super.setUp();
		
		xmlDirectory = getTestInputBasePath() + "/xml";
		xmlSchema = xmlDirectory + "/validator.xsd";
		xmlValido1 = xmlDirectory + "/xmlValido1.xml";
		xmlNonValido1 = xmlDirectory + "/xmlNonValido1.xml";
		xmlValidoConSchema1 = xmlDirectory + "/xmlValidoConSchema1.xml";
		
		assertTrue( new File( xmlSchema ).exists() );
		assertTrue( new File( xmlValido1 ).exists() );
		assertTrue( new File( xmlNonValido1 ).exists() );
		assertTrue( new File( xmlValidoConSchema1 ).exists() );
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testValidation1() throws Exception {
		validateMessages = new ArrayList<IErrorMessage>();
		IValidator xmlValidator = new XmlValidator(new FileInputStream(xmlValido1), new FileInputStream(xmlSchema) , validateMessages);
		xmlValidator.validate();
		
		for (IErrorMessage message: validateMessages) {
			System.out.println("message.getName() " + message.getName() );
			System.out.println("message.getMessage() " + message.getMessage() );
		}
		
		assertEquals( 0, validateMessages.size() );
	}
	
	public void testValidation2() throws Exception {
		validateMessages = new ArrayList<IErrorMessage>();
		IValidator xmlValidator = new XmlValidator( new FileInputStream(xmlNonValido1), new FileInputStream(xmlSchema) , validateMessages);
		xmlValidator.validate();
		
		for (IErrorMessage message: validateMessages) {
			System.out.println("message.getName() " + message.getName() );
			System.out.println("message.getMessage() " + message.getMessage() );
		}
		
		assertEquals( 1, validateMessages.size() );
	}
	
	public void testValidation3() throws Exception {
		validateMessages = new ArrayList<IErrorMessage>();
		IValidator xmlValidator = new XmlValidator(new FileInputStream(xmlValidoConSchema1), new FileInputStream(xmlSchema) , validateMessages);
		xmlValidator.validate();
		
		for (IErrorMessage message: validateMessages) {
			System.out.println("message.getName() " + message.getName() );
			System.out.println("message.getMessage() " + message.getMessage() );
		}
		
		assertEquals( 0, validateMessages.size() );
	}

}
