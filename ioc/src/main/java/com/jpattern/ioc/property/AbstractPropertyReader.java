package com.jpattern.ioc.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jpattern.ioc.IPropertyReader;
import com.jpattern.ioc.exception.ConfigException;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ott/08 11:58:39
 * @version $Id: $
 */
public abstract class AbstractPropertyReader implements IPropertyReader {

    protected Properties _properties = new Properties();
    
    private String location;
    
    public AbstractPropertyReader() {
        super();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void load(String basePath) throws ConfigException {
    	InputStream inputStream = null;
            try {
            	inputStream = new FileInputStream(  new File(basePath + File.separator + getLocation())  );
                _properties.load( inputStream );
            }
            catch (FileNotFoundException e) {
                throw new ConfigException(e);
            }
            catch (IOException e) {
                throw new ConfigException(e);
            } finally {
            	if (inputStream!=null) {
            		try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
            	}
            }
    }

    public abstract String property(String aProperty);
    
    protected String parse(String aProperty, String prefix, String suffix) {
        int start = aProperty.indexOf(prefix);
        int stop = aProperty.indexOf(suffix);
        aProperty = aProperty.substring(start + (prefix.length()),stop);
        return aProperty ;
    }    
}
