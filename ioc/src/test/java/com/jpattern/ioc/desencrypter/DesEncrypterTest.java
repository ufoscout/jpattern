package com.jpattern.ioc.desencrypter;

import java.security.NoSuchAlgorithmException;


import com.jpattern.ioc.BaseTest;
import com.jpattern.ioc.desencrypter.DesEncrypter;

/**
 * 
 * @author Francesco Cina'
 *
 * 4 Feb 2011
 */
public class DesEncrypterTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
    public void testCrypt() throws NoSuchAlgorithmException {
        
        // Create encrypter/decrypter class
        DesEncrypter encrypter = new DesEncrypter();
    
        // Encrypt
        String encrypted = encrypter.encrypt("mypassword");
    
        // Decrypt
        String decrypted = encrypter.decrypt(encrypted);
        
        assertEquals("mypassword", decrypted);
    }

}
