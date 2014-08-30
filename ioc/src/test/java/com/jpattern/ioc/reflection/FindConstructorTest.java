package com.jpattern.ioc.reflection;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;


import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.BeanMapper;
import com.jpattern.ioc.ContextCreator;
import com.jpattern.ioc.model.xml.BeanUno;
import com.jpattern.ioc.model.xml.ManyConstructors;
import com.jpattern.ioc.reflection.ConstructorDescriptor;
import com.jpattern.ioc.reflection.FindConstructor;
import com.jpattern.ioc.reflection.ReflectionUtil;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xml.Entry;
import com.jpattern.ioc.xml.IParameter;
import com.jpattern.ioc.xml.Key;
import com.jpattern.ioc.xml.Parameter;
import com.jpattern.ioc.xml.ParameterMap;
import com.jpattern.ioc.xml.Type;
import com.jpattern.ioc.xml.Value;
import com.jpattern.ioc.xstream.XStreamReader;

public class FindConstructorTest extends BaseTest {

	private ConstructorDescriptor constructorDescriptor;
	private XStreamReader _reader;
	private Context _context;
	private BeanMapper _beanMapper;
	private ContextCreator _contextCreator;

	protected void setUp() throws Exception {
		super.setUp();
		
		_reader = new XStreamReader();
	    _context = _reader.read(TEST_FILE);
	    _beanMapper = new BeanMapper();
		ReflectionUtil reflectionUtil = new ReflectionUtil();
        _contextCreator = new ContextCreator(reflectionUtil, _beanMapper, _context);
		
		
		List<IParameter> parameters = new ArrayList<IParameter>();
		parameters.add(new Parameter( new Value("10") , new Type("String") ));
		ParameterMap parameterMap = new ParameterMap();
		parameterMap.add(new Entry(new Key("key") , new Value("value") ));
		parameters.add( parameterMap );
		constructorDescriptor = new ConstructorDescriptor(_contextCreator, parameters);
		assertEquals( 2 , constructorDescriptor.getParameterTypes().length );
		assertEquals( 2 , constructorDescriptor.getParameterObjects().length );
		for ( int i = 0 ; i<constructorDescriptor.getParameterTypes().length; i++ ){
			System.out.println("ParameterTypes[" + i + "] = " + constructorDescriptor.getParameterTypes()[i]);
			System.out.println("ParameterObjects[" + i + "] = " + constructorDescriptor.getParameterObjects()[i]);
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFindConstructor() throws Exception {
		Class<ManyConstructors> aClass = ManyConstructors.class;
		
		FindConstructor findConstructor = new FindConstructor( aClass, constructorDescriptor);
		Constructor<?> constructor = findConstructor.getConstructor();
		assertNotNull( constructor );
		
		ManyConstructors manyConstructors = (ManyConstructors) constructor.newInstance(constructorDescriptor.getParameterObjects());
		
		assertEquals( 6 , manyConstructors.getQualeCostruttore());
		assertEquals( "costruttore con 2 parametri String,Map" , manyConstructors.getDettagli());
	}
	
	
	public void testFindConstructor2() throws Exception {
		Class<BeanUno> aClass = BeanUno.class;
		
		FindConstructor findConstructor = new FindConstructor( aClass, constructorDescriptor);
		try {
			Constructor<?> constructor = findConstructor.getConstructor();
			constructor.getName();
			assertTrue(false);
		}
		catch (NoSuchMethodException e) {
			System.out.println("lanciata eccezione NoSuchMethodFound: corretto!!");
			assertTrue(true);
		}
	}
	
	
	public void testFindConstructor5() throws Exception {
		
		List<IParameter> parameters = new ArrayList<IParameter>();
		parameters.add(new Parameter( new Value("10") , new Type("StringBuffer") ));
		parameters.add(new Parameter( new Value("10") , new Type("int") ));
		parameters.add(new Parameter( new Value("10") , new Type("Character") ));
		parameters.add(new Parameter( new Value("true") , new Type("boolean") ));
		parameters.add(new Parameter( new Value("10") , new Type("String") ));
		parameters.add(new Parameter( new Value("10") , new Type("String") ));

		
		ConstructorDescriptor constructorDescriptor2 = new ConstructorDescriptor(_contextCreator, parameters);
		assertEquals( 6 , constructorDescriptor2.getParameterTypes().length );
		assertEquals( 6 , constructorDescriptor2.getParameterObjects().length );
		for ( int i = 0 ; i<constructorDescriptor2.getParameterTypes().length; i++ ){
			System.out.println("ParameterTypes[" + i + "] = " + constructorDescriptor2.getParameterTypes()[i]);
			System.out.println("ParameterObjects[" + i + "] = " + constructorDescriptor2.getParameterObjects()[i]);
		}
		
		Class<ManyConstructors> aClass = ManyConstructors.class;
		
		FindConstructor findConstructor = new FindConstructor( aClass, constructorDescriptor2);
		Constructor<?> constructor = findConstructor.getConstructor();
		assertNotNull( constructor );
		
		ManyConstructors manyConstructors = (ManyConstructors) constructor.newInstance(constructorDescriptor2.getParameterObjects());
		
		assertEquals( 25 , manyConstructors.getQualeCostruttore());
		assertEquals( "costruttore con 6 parametri StringBuffer=10, int=10, Character=1, boolean=true, String=10, Object=10" , manyConstructors.getDettagli());
	}
	
}
