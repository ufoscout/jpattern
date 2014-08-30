package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ParameterMap  implements IParameter  {
        
        private List<IEntry> entries = new ArrayList<IEntry>();
        
        public ParameterMap() {
        }

        
        public void add(Entry aEntry) {
            entries.add(aEntry);
        }

        public List<IEntry> getEntries() {
            return entries;
        }

        public Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
            Map<String, Object> returnedMap = new HashMap<String, Object>();
            ListIterator<IEntry> entriesIterator = getEntries().listIterator();
            IEntry iEntry;
            while (entriesIterator.hasNext()) {
                iEntry = (IEntry)entriesIterator.next();
                returnedMap.put(iEntry.getKey().key(), iEntry.typedValue(aContextCreator));
            }
            return returnedMap;
        }


		@SuppressWarnings("rawtypes")
		public Class<HashMap> typedClass(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
			return HashMap.class;
		}

}
