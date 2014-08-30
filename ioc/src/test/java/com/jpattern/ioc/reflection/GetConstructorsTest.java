package com.jpattern.ioc.reflection;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.model.xml.ManyConstructors;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class GetConstructorsTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCostruttore1() throws Exception {

		Constructor<?>[] costruttori = ManyConstructors.class.getConstructors();
		System.out.println("trovati " + costruttori.length + " costruttori");

		for (int i = 0; i<costruttori.length; i++){
			System.out.println(costruttori[i]);
		}
		
		//provo ad istanziare un oggetto dal costruttore senza parametri
		Class<?>[] parameterTypes = new Class[0];
		Object[] parameterObjects = new Object[0];
		Constructor<ManyConstructors> costruttore = ManyConstructors.class.getConstructor(parameterTypes);
		ManyConstructors manyConstructor = costruttore.newInstance(parameterObjects);
		assertEquals( "costruttore senza parametri" , manyConstructor.getDettagli() );

		
		//provo ad istanziare un oggetto dal costruttore con 1 parametro String
		parameterTypes = new Class[]{String.class};
		parameterObjects = new Object[]{""};
		costruttore = ManyConstructors.class.getConstructor(parameterTypes);
		manyConstructor = costruttore.newInstance(parameterObjects);
		assertEquals( "costruttore con 1 parametro String" , manyConstructor.getDettagli() );
		
		
		//provo ad istanziare un oggetto dal costruttore con 1 parametro int
		parameterTypes = new Class[]{Integer.class};
		parameterObjects = new Object[]{new Integer(123)};
		costruttore = ManyConstructors.class.getConstructor(parameterTypes);
		manyConstructor = costruttore.newInstance(parameterObjects);
		assertEquals( "costruttore con 1 parametro Integer = 123" , manyConstructor.getDettagli() );
		assertEquals( 3 , manyConstructor.getQualeCostruttore() );
		
		
		//provo ad istanziare un oggetto dal costruttore con 1 parametro double passandogli un Double
		parameterTypes = new Class[]{double.class};
		parameterObjects = new Object[]{new Double(123)};
		costruttore = ManyConstructors.class.getConstructor(parameterTypes);
		manyConstructor = costruttore.newInstance(parameterObjects);
		assertEquals( "costruttore con 1 parametro double = 123.0" , manyConstructor.getDettagli() );
		assertEquals( 4 , manyConstructor.getQualeCostruttore() );
		
		
		//provo ad istanziare un oggetto dal costruttore con 1 parametro double passandogli un Double
		parameterTypes = new Class[]{int.class,String.class,Double.class};
		parameterObjects = new Object[]{new Integer(4), "", new Double(123)};
		costruttore = ManyConstructors.class.getConstructor(parameterTypes);
		manyConstructor = costruttore.newInstance(parameterObjects);
		assertEquals( "costruttore con 3 parametri int,String,Double" , manyConstructor.getDettagli() );
		assertEquals( 5 , manyConstructor.getQualeCostruttore() );

	}
	
	public void testTipiPrimitivi() throws Exception {
		
		Class<?> classePrimitiva = Integer.TYPE;
		System.out.println("classePrimitiva = " + classePrimitiva);
		System.out.println("classePrimitiva.isPrimitive() = " + classePrimitiva.isPrimitive());
		assertTrue( classePrimitiva.isPrimitive() );
		
	}
	
	@SuppressWarnings("rawtypes")
	public void testIsInstance() {
		boolean instance = Object.class.isInstance(new String());
		assertTrue(instance);
		
		instance = Map.class.isInstance(new HashMap());
		assertTrue(instance);
		
		instance = String.class.isInstance(new String());
		assertTrue(instance);

	}
}