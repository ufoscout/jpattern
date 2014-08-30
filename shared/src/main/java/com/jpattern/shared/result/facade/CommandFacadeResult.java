package com.jpattern.shared.result.facade;

import java.util.List;

import com.jpattern.shared.result.IErrorMessage;
import com.jpattern.shared.result.IResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 25 Feb 2011
 */
public class CommandFacadeResult<E> implements ICommandFacadeResult<E>  {

    private static final long serialVersionUID = 1L;
    private IResult result;
    private E returnedObject;
    
    public CommandFacadeResult(IResult aResult, E returnedObject) {
            result = aResult;
            this.returnedObject = returnedObject;
    }

    @Override
    public List<IErrorMessage> getErrorMessages() {
        return result.getErrorMessages();
    }

    @Override
    public E getReturnedObject() {
        return returnedObject;
    }

}
