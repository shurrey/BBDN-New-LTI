/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdn.sample.lti11.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SourcedIdGen {

    public static String generate() throws SourcedIdGenException {
    	Random r = new Random();
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        	Logger.getLogger(SourcedIdGen.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

        byte[] entropy = new byte[1024];
        r.nextBytes(entropy);
        md.update(entropy, 0, 1024);

        BigInteger number = new BigInteger(1, md.digest());
        
        String hashtext = number.toString(16);
        
        return hashtext;
        
    }
}
