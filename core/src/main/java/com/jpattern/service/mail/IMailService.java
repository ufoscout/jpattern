package com.jpattern.service.mail;

import com.jpattern.core.IService;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public interface IMailService extends IService {

	IMailSender mailSender();

}
