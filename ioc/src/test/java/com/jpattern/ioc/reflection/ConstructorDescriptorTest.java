package com.jpattern.ioc.reflection;

import java.util.List;
import java.util.ArrayList;


import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.BeanMapper;
import com.jpattern.ioc.ContextCreator;
import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.reflection.ConstructorDescriptor;
import com.jpattern.ioc.reflection.ReflectionUtil;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xml.IParameter;
import com.jpattern.ioc.xml.Parameter;
import com.jpattern.ioc.xml.Type;
import com.jpattern.ioc.xml.Value;
import com.jpattern.ioc.xstream.XStreamReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ConstructorDescriptorTest extends BaseTest {

	private XStreamReader _reader;
	private Context _context;
	private BeanMapper _beanMapper;
	private IContextCreator _contextCreator;

	protected void setUp() throws Exception {
		super.setUp();
		_reader = new XStreamReader();
	    _context = _reader.read(TEST_FILE);
	    _beanMapper = new BeanMapper();
		ReflectionUtil reflectionUtil = new ReflectionUtil();
        _contextCreator = new ContextCreator(reflectionUtil, _beanMapper, _context);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testConstructorDescriptor() throws Exception {
		List<IParameter> parameters = new ArrayList<IParameter>();
		
		ConstructorDescriptor constructorDescriptor = new ConstructorDescriptor(_contextCreator, parameters);
		assertEquals( 0 , constructorDescriptor.getParameterTypes().length );
		assertEquals( 0 , constructorDescriptor.getParameterObjects().length );
		
		parameters.add(new Parameter( new Value("10"), new Type("String") ));
		constructorDescriptor = new ConstructorDescriptor(_contextCreator, parameters);
		assertEquals( 1 , constructorDescriptor.getParameterTypes().length );
		assertEquals( 1 , constructorDescriptor.getParameterObjects().length );
		
		
		parameters.add(new Parameter( new Value("valore2") , new Type("String") ));
		constructorDescriptor = new ConstructorDescriptor(_contextCreator, parameters);
		assertEquals( 2 , constructorDescriptor.getParameterTypes().length );
		assertEquals( 2 , constructorDescriptor.getParameterObjects().length );
		for ( int i = 0 ; i<constructorDescriptor.getParameterTypes().length; i++ ){
			System.out.println("ParameterTypes[" + i + "] = " + constructorDescriptor.getParameterTypes()[i]);
			System.out.println("ParameterObjects[" + i + "] = " + constructorDescriptor.getParameterObjects()[i]);
		}
	}
}
