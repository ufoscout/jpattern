package com.jpattern.jobexecutor;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 02/apr/2010
 */
public interface IWrappedResult extends Serializable {

        void add(String aLine);
        
        String result();
        
        void clean();
}
