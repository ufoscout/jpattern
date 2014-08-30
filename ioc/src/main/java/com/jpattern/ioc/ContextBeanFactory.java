package com.jpattern.ioc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.jpattern.ioc.exception.ConfigException;
import com.jpattern.ioc.reflection.ReflectionUtil;
import com.jpattern.ioc.util.FilePath;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xstream.XStreamReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/feb/2011
 */
public final class ContextBeanFactory {

    private AbstractReader _reader = new XStreamReader();
    private Context _context = new Context();
    private BeanMapper _beanMapper = new BeanMapper();
    private ReflectionUtil _reflectionUtil = new ReflectionUtil();
    private IContextCreator _icontextCreator;

    public ContextBeanFactory(String aConfigFile) throws ConfigException {
    	FileInputStream fileInputStream = null;
    	try {
    		File file = new File(aConfigFile);
    		fileInputStream = new FileInputStream(file); 
			_context.init(_reader, fileInputStream , FilePath.getPath(file));
	        _icontextCreator = new ContextCreator(_reflectionUtil, _beanMapper, _context);
		} catch (IOException e) {
			throw new ConfigException(e);
		} finally {
			if (fileInputStream!=null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }

//    public ContextBeanFactory(InputStream aInputStream) throws ConfigException {
//        try {
//			_context.init(_reader, aInputStream);
//	        _icontextCreator = new ContextCreator(_reflectionUtil, _beanMapper, _context);
//	        createPropertyReader();
//		} catch (IOException e) {
//			throw new ConfigException(e);
//		}
//    }

    public Object get(String aBeanIdName) throws ConfigException {
        try {
            return _icontextCreator.create(aBeanIdName);
        }
        catch (ClassNotFoundException e) {
            throw new ConfigException(e);
        }
    }

}
