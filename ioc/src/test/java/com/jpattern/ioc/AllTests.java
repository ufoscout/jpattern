package com.jpattern.ioc;


import com.jpattern.ioc.desencrypter.DesEncrypterTest;
import com.jpattern.ioc.property.PropertyReaderTest;
import com.jpattern.ioc.reflection.BeanContextReflectionTest;
import com.jpattern.ioc.reflection.ConstructorDescriptorTest;
import com.jpattern.ioc.reflection.FindConstructorTest;
import com.jpattern.ioc.reflection.GetConstructorsTest;
import com.jpattern.ioc.spike.reflection.ReflectionTest;
import com.jpattern.ioc.util.FilePathTest;
import com.jpattern.ioc.xml.EntryTest;
import com.jpattern.ioc.xml.typebuilder.ITypeBuilderTest;
import com.jpattern.ioc.xstream.XStreamReaderTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.jod.configreader");
        //spike
        suite.addTestSuite(ReflectionTest.class);
        
        // tests
        suite.addTestSuite(BeanContextReflectionTest.class);
        suite.addTestSuite(ContextBeanFactoryTest.class);
        suite.addTestSuite(ContextBeanFactoryPropertyReaderTest.class);
        suite.addTestSuite(PropertyReaderTest.class);
        suite.addTestSuite(EntryTest.class);
        suite.addTestSuite(ConstructorDescriptorTest.class);
        suite.addTestSuite(GetConstructorsTest.class);
        suite.addTestSuite(FindConstructorTest.class);
        suite.addTestSuite(ITypeBuilderTest.class);
        suite.addTestSuite(ExtraContextBeanFactoryTest.class);
        suite.addTestSuite(DesEncrypterTest.class);
        suite.addTestSuite(XStreamReaderTest.class);
        suite.addTestSuite(FilePathTest.class);
        
        return suite;
    }

}
