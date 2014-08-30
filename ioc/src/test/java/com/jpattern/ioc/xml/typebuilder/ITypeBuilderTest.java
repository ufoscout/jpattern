package com.jpattern.ioc.xml.typebuilder;

import com.jpattern.ioc.xml.typebuilder.DoubleBuilder;
import com.jpattern.ioc.xml.typebuilder.ITypeBuilder;
import com.jpattern.ioc.xml.typebuilder.IntegerBuilder;
import com.jpattern.ioc.xml.typebuilder.Primitive_int_Builder;
import com.jpattern.ioc.xml.typebuilder.ResultType;
import com.jpattern.ioc.xml.typebuilder.StringBuilder;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ITypeBuilderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testTypeBuilderChain() throws Exception {
		ITypeBuilder typeBuilder = new IntegerBuilder();
		typeBuilder = new DoubleBuilder(typeBuilder);
		typeBuilder = new Primitive_int_Builder(typeBuilder);
		// lo StringBuilder DEVE essere l'ultimo della catena poiche' 
		// questo restituisce SEMPRE un risultato valido e non permetterebbe
		// ai TypeBuilder successivi di attivarsi
		typeBuilder = new StringBuilder(typeBuilder);
		
		String value = "13";
		
		ResultType result = typeBuilder.exec("Integer", value);
		assertTrue( result.isValid() );
		assertEquals( Integer.class , result.getClassOfInstance() );
		assertEquals( 13, ((Integer) result.getInstance()).intValue() );
		
		result = typeBuilder.exec("Double", value);
		assertTrue( result.isValid() );
		assertEquals( Double.class , result.getClassOfInstance() );
		assertEquals( new Double(13d) , (Double) result.getInstance() );
		
		value = "13as";
		
		result = typeBuilder.exec("Integer", value);
		assertTrue( result.isValid() );
		assertEquals( Integer.class , result.getClassOfInstance() );
		assertEquals( 0 , ((Integer) result.getInstance()).intValue() );
		
		
		result = typeBuilder.exec("Ciao", value);
		assertTrue( result.isValid() );
		assertEquals( String.class , result.getClassOfInstance() );
		assertEquals( "13as", (String) result.getInstance() );
		
		
		value = "211";
		result = typeBuilder.exec("int", value);
		assertTrue( result.isValid() );
		assertEquals( int.class , result.getClassOfInstance() );
		assertEquals( 211, ((Integer) result.getInstance()).intValue() );
	}

}
