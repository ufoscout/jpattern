package com.jpattern.gwt.property;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.property.IProperty;
import com.jpattern.gwt.client.property.Property;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class PropertyTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testProperty() throws Exception {
		IProperty properties = new Property(null);
		
		assertTrue( properties.getKeys().isEmpty() );
		assertEquals("",properties.getProperty(null));
		assertEquals("",properties.getProperty(""));
		assertEquals("",properties.getProperty("hello"));
		assertFalse(properties.containsKey(null));
		assertFalse(properties.containsKey(""));
		assertFalse(properties.containsKey("hello"));
	}
	
	public void testProperty1() throws Exception {
		Map<String,String> propertyMap = new HashMap<String, String>();
		propertyMap.put("hello", "valuehello");
		propertyMap.put("hello2", "valuehello2");
		IProperty properties = new Property(propertyMap);
		
		assertEquals( 2 , properties.getKeys().size() );
		assertTrue( properties.getKeys().contains("hello") );
		assertTrue( properties.getKeys().contains("hello2") );
		assertEquals( 2 , properties.getKeys().size() );
		assertEquals("",properties.getProperty(null));
		assertEquals("",properties.getProperty(""));
		assertEquals("valuehello",properties.getProperty("hello"));
		assertEquals("valuehello2",properties.getProperty("hello2"));
		assertFalse(properties.containsKey(null));
		assertFalse(properties.containsKey(""));
		assertTrue(properties.containsKey("hello"));
		assertTrue(properties.containsKey("hello2"));
	}

}
