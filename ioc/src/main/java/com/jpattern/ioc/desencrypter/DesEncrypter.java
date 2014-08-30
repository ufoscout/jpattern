package com.jpattern.ioc.desencrypter;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


import com.jpattern.ioc.IEncrypter;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class DesEncrypter implements IEncrypter {
    Cipher ecipher;

    Cipher dcipher;

    byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

    int iterationCount = 19;

    public DesEncrypter() {
        try {
            String temp = "org_jod_config_reader";
            KeySpec keySpec = new PBEKeySpec(temp.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        }
        
        catch (Exception e) {
        }        
    }

    public String encrypt(String aString) {
        try {
            byte[] utf8 = aString.getBytes("UTF8");

            byte[] enc = ecipher.doFinal(utf8);

            return new Base64Encoder().encode(enc);
        }
        catch (Exception e) {
        }
      
        return null;
    }

    public String decrypt(String aString) {
        try {
            byte[] dec = new Base64Encoder().decode(aString);
            byte[] utf8 = dcipher.doFinal(dec);

            return new String(utf8, "UTF8");
        }
        
        catch (Exception e) {
        }
        return null;
    }
}
