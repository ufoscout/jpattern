package com.jpattern.ioc;

import java.util.List;
import java.util.ListIterator;

import com.jpattern.ioc.exception.ConfigException;
import com.jpattern.ioc.reflection.BeanContextReflection;
import com.jpattern.ioc.reflection.ConstructorDescriptor;
import com.jpattern.ioc.reflection.MethodDescriptor;
import com.jpattern.ioc.reflection.ReflectionUtil;
import com.jpattern.ioc.xml.BeanContext;
import com.jpattern.ioc.xml.Context;
import com.jpattern.ioc.xml.IEntry;

/**
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 25/ott/08 12:06:21
 * @version $Id: $
 */
public class ContextCreator implements IContextCreator {

    private static final long serialVersionUID = 1L;
    private ReflectionUtil _reflectionUtil;
    private BeanMapper _beanMapper;
    private Context _context;

    public ContextCreator(ReflectionUtil aReflectionUtil, BeanMapper aBeanMapper, Context aContext) {
        _reflectionUtil = aReflectionUtil;
        _beanMapper = aBeanMapper;
        _context = aContext;
        _context.visit(this);
    }

    public Object create(String beanIdName) throws ConfigException, ClassNotFoundException {
        if (!_beanMapper.contains(beanIdName)) {
            return createBean(beanIdName);
        }
        return _beanMapper.get(beanIdName);
    }

    public String readProperty(String aProperty) {
        if (!_beanMapper.contains(REPLACER_BEAN_ID)) {
            return aProperty;
        }
        return ((IPropertyReader) _beanMapper.get(REPLACER_BEAN_ID)).property(aProperty);
    }

    private Object createBean(String beanIdName) throws ClassNotFoundException, ConfigException {
        BeanContext _beanContext = _context.get(beanIdName);
        
        if (_beanContext == null)
            throw new ConfigException("Cannot find bean named [" + beanIdName + "]");
        
        
        BeanContextReflection beanContextReflection = new BeanContextReflection(_beanContext);

        String beanObjectName = _beanContext.getClassname().classname();
        ConstructorDescriptor constructorDescriptor = new ConstructorDescriptor(this, _beanContext.getParameters() );
        Object returnedObject = _reflectionUtil.newInstance(beanObjectName , constructorDescriptor);

        List<IEntry> entries = _beanContext.getEntries();
        ListIterator<IEntry> entriesIterator = entries.listIterator();
        IEntry each;
        String eachEntryName;
        Object eachTypedValue;
        MethodDescriptor methodDescriptor;
        while (entriesIterator.hasNext()) {
            each = (IEntry) entriesIterator.next();
            eachEntryName = each.getKey().key();
            eachTypedValue = each.typedValue(this);
            methodDescriptor = beanContextReflection.getDescriptor(eachEntryName);
            _reflectionUtil.setterInvoke(methodDescriptor, new Object[] { eachTypedValue }, returnedObject);
        }
        _beanMapper.put(beanIdName, returnedObject);

        return returnedObject;
    }

    /*
     * private void loadPropertyReader() throws ConfigException { try { _propertyReader = (IPropertyReader)createBean(REPLACER_BEAN_ID);
     * _beanMapper.get(REPLACER_BEAN_ID); } catch (ClassNotFoundException e) { _propertyReader = new NullPropertyReader(); } catch (ConfigException e) {
     * _propertyReader = new NullPropertyReader(); } catch (Throwable e) { _propertyReader = new NullPropertyReader(); } _propertyReader.load(); }
     */
}
