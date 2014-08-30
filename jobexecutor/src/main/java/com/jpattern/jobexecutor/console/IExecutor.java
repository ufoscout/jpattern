package com.jpattern.jobexecutor.console;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public interface IExecutor<E> extends Serializable {

	void exec(E e);

}
