package com.jpattern.ioc.util;

import com.jpattern.ioc.IEncrypter;
import com.jpattern.ioc.desencrypter.DesEncrypter;

public class Encrypt {
    
    private IEncrypter _encrypter;

    public Encrypt(IEncrypter aEncrypter) {
        
        _encrypter = aEncrypter;
    }
    
    public Encrypt() {
        
        _encrypter= new DesEncrypter();
    }    
    
    private void encrypt(String aString) {
        String crypted =  _encrypter.encrypt(aString);
        System.out.println("Orginal  string: " + aString);
        System.out.println("Crypted string: " + crypted);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        
        if (args.length== 0 ) {
            System.out.println("Usage: ");
            System.out.println("java org.jod.configreader.util.Encrypt <encrypted string>");
        } else {
            new Encrypt().encrypt(args[0]);
        }
    }

}
