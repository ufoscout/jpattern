package com.jpattern.core.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.core.util.CharacterEncoding;
import com.jpattern.core.xml.DefaultXmlParserStrategy;
import com.jpattern.core.xml.IXmlAttribute;
import com.jpattern.core.xml.IXmlElement;
import com.jpattern.core.xml.IXmlReaderStrategy;
import com.jpattern.core.xml.IXmlWriterStrategy;
import com.jpattern.core.xml.XmlElement;
import com.jpattern.core.xml.XmlFileWriterStrategy;
import com.jpattern.core.xml.XmlReaderCommand;
import com.jpattern.core.xml.XmlWriterCommand;
import com.jpattern.shared.util.GenericWrapper;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class XmlWriterCommandTest extends BaseApplicationTest {

	private String xmlCorrettoFilePath;
	private XmlElement sourceXmlRootElement;
	private String newXmlFileName;
	private String newXmlFilePath;

	protected void setUp() throws Exception {
		super.setUp();
		
		xmlCorrettoFilePath = getTestInputBasePath() + "/xml/xmlTest1.xml";
		assertTrue( new File(xmlCorrettoFilePath).exists() );
		
		GenericWrapper<Boolean> trimValues = new GenericWrapper<Boolean>(true);
		sourceXmlRootElement = new XmlElement();		
		IXmlReaderStrategy xmlParserStrategy = new DefaultXmlParserStrategy( sourceXmlRootElement );
		
		ACommand<IProvider> command = new XmlReaderCommand( new FileInputStream(xmlCorrettoFilePath), trimValues , xmlParserStrategy, new NullErrorHandler());
		ACommandResult result = command.exec(getProvider());
		assertTrue( result.isValid() );
		
		newXmlFilePath = "./target";
		newXmlFileName = "testXmlCreate" + new Date().getTime();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		File fileToDelete = new File(newXmlFilePath + "/" + newXmlFileName);
		if ( fileToDelete.exists() ) {
			assertTrue( fileToDelete.delete() );
		}
	}

	public void testXmlWriter() throws Exception {
		
		IXmlWriterStrategy xmlWriterStrategy = new XmlFileWriterStrategy(new FileOutputStream( newXmlFilePath + "/" +  newXmlFileName ), CharacterEncoding.UTF_8);
		
		ACommand<IProvider> command = new XmlWriterCommand( xmlWriterStrategy, sourceXmlRootElement, CharacterEncoding.UTF_8 );
		ACommandResult result = command.exec(getProvider());
		assertTrue(result.isValid());
		
		IXmlElement createdXmlElement = new XmlElement();
		command = new XmlReaderCommand(new FileInputStream( newXmlFilePath + "/" + newXmlFileName ), new GenericWrapper<Boolean>(true) , new DefaultXmlParserStrategy( createdXmlElement ), new NullErrorHandler());
		result = command.exec(getProvider());
		assertTrue( result.isValid() );
	
		testEquals( sourceXmlRootElement , createdXmlElement );
	}
	
	public void testXmlWriter2() throws Exception {
		
		IXmlElement element = new XmlElement();
		element.setName("padre");
		
		element.addSubElement().setName("figlio");
		element.addSubElement().setName("figlio");
		element.addSubElement().setName("figlio");
		
		IXmlWriterStrategy xmlWriterStrategy = new XmlFileWriterStrategy(new FileOutputStream( newXmlFilePath + "/" +  newXmlFileName ) , CharacterEncoding.UTF_8);
		
		ACommand<IProvider> command = new XmlWriterCommand( xmlWriterStrategy, element, CharacterEncoding.UTF_8 );
		ACommandResult result = command.exec(getProvider());
		assertTrue(result.isValid());
		
		
		IXmlElement createdXmlElement = new XmlElement();
		command = new XmlReaderCommand(new FileInputStream( newXmlFilePath + "/" + newXmlFileName  ), new GenericWrapper<Boolean>(true) , new DefaultXmlParserStrategy( createdXmlElement ), new NullErrorHandler());
		result = command.exec(getProvider());
		assertTrue( result.isValid() );
	
		testEquals( element , createdXmlElement );
	}
	
	private void testEquals( IXmlElement elementA, IXmlElement elementB) {
		
		assertEquals( elementA.getName() , elementB.getName() );
		assertEquals( elementA.getValue() , elementB.getValue() );
		assertEquals( elementA.getAttributesName().size() , elementB.getAttributesName().size() );
		assertEquals( elementA.getSubElementsName().size() , elementB.getSubElementsName().size() );
		
		for ( String attributeName : elementA.getAttributesName()) {
			IXmlAttribute attributeA = elementA.getAttribute(attributeName);
			IXmlAttribute attributeB = elementB.getAttribute(attributeName);
			assertEquals( attributeA.getName() , attributeB.getName() );
			assertEquals( attributeA.getValue() , attributeB.getValue() );
		}
		
		for ( String childName : elementA.getSubElementsName()) {
			List<IXmlElement> childsA = elementA.getSubElements(childName);
			List<IXmlElement> childsB = elementB.getSubElements(childName);
			assertEquals(childsA.size() , childsB.size());
			
			for ( int i=0; i<childsA.size(); i++ ) {
				testEquals(childsA.get(i), childsB.get(i));
			}
		}
		
	}
	
}
