package com.jpattern.shared.result;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public interface IErrorMessage extends Serializable  {
    
    String getMessage();

    String getName();
    
    List<String> getParameters();
}
