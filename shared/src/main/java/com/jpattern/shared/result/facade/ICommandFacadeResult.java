package com.jpattern.shared.result.facade;

import com.jpattern.shared.result.IResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 25 Feb 2011
 */
public interface ICommandFacadeResult<E> extends IResult   {

    E getReturnedObject();
    
}
