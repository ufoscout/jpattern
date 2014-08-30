package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ParameterList  implements IParameter  {
        
        private List<IEntry> entries = new ArrayList<IEntry>();
        
        public ParameterList() {
        }

        
        public void add(Entry aEntry) {
            entries.add(aEntry);
        }

        public List<IEntry> getEntries() {
            return entries;
        }

        public Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
            List<Object> returnedList = new ArrayList<Object>();
            ListIterator<IEntry> entriesIterator = getEntries().listIterator();
            IEntry iEntry;
            while (entriesIterator.hasNext()) {
                iEntry = entriesIterator.next();
                returnedList.add(iEntry.typedValue(aContextCreator));
            }
            return returnedList;
        }


		@SuppressWarnings("rawtypes")
		public Class<List> typedClass(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
			return List.class;
		}

}
