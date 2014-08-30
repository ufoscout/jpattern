package com.jpattern.ioc.property;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.ioc.IPropertyReader;
import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/dic/2010
 */
public class PropertyReaderComposite implements IPropertyReader {

	private List<IPropertyReader> propertyReaderList = new ArrayList<IPropertyReader>();
	
	public void load(String basePath) throws ConfigException {
		for (int i=0; i<propertyReaderList.size(); i++) {
			( (IPropertyReader) propertyReaderList.get(i)).load(basePath);
		}
	}

	public String property(String aProperty) {
		String result = aProperty;
		for (int i=0; i<propertyReaderList.size(); i++) {
			result = ( (IPropertyReader) propertyReaderList.get(i)).property(aProperty);
			if ( result!=null && !result.equals(aProperty)) {
				break;
			}
		}
		return result;
	}
	
	public void setPropertyReaderList(List<IPropertyReader> propertyReaderList) {
		for (int i=0; i<propertyReaderList.size(); i++) {
			if (propertyReaderList.get(i) instanceof IPropertyReader) {
				addPropertyReader( (IPropertyReader) propertyReaderList.get(i));
			}
		}		
	}

    public void addPropertyReader(IPropertyReader propertyReader) {
    	propertyReaderList.add(propertyReader);
    }
}
