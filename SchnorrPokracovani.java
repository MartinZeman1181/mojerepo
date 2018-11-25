/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.autentizace;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author martin
 */
public class SchnorrPokracovani {

    /*
    p = 1048583 ,a = 1538555875425171824452679064909200268 ,g = 5858 ,s = 2929 ,v = 10000004 ,r = 3905 ,x = 1
     */
    public static void main(String[] arg) throws UnsupportedEncodingException {
        BigInteger p = BigInteger.valueOf(1048583L);
        BigInteger a = new BigInteger("1538555875425171824452679064909200268");
        BigInteger g = BigInteger.valueOf(5858L); // dělitel čísla p - 1
        BigInteger s = BigInteger.valueOf(2929L); // secret 
        BigInteger v = BigInteger.valueOf(10000004L); // public
        BigInteger r = BigInteger.valueOf(3905L); // public
        BigInteger x = BigInteger.valueOf(970176L);
        int iii = 999;
        String text = "Vytřel bych ti zrak ....";
        byte[] bytes = text.getBytes("UTF-8");
        byte[] toByteArray = x.toByteArray();
        byte[] celkem = new byte[bytes.length + toByteArray.length];
        int index = 0;
        for (int i = 0; i < bytes.length; i++) {
            celkem[index++] = bytes[i];
        }
        for (int i = 0; i < toByteArray.length; i++) {
            celkem[index++] = toByteArray[i];
        }
        byte[] sha1Hex2 = DigestUtils.sha1(celkem);
        BigInteger e = new BigInteger(sha1Hex2);
        BigInteger y = ((s.multiply(e)).add(r)).mod(g);
        BigInteger xp = x.mod(p);
        //--
        byte[] toByteArray2 = xp.toByteArray();
        byte[] celkem2 = new byte[bytes.length + toByteArray2.length];
        //--
        index = 0;
        for (int i = 0; i < bytes.length; i++) {
            celkem2[index++] = bytes[i];
        } for (int i = 0; i < toByteArray2.length; i++) {
            celkem2[index++] = toByteArray2[i];
        }
        byte[] sha1Hex4 = DigestUtils.sha1(celkem2);
        BigInteger ep = new BigInteger(sha1Hex4);
        if (e.compareTo(ep) == 0) {
            System.out.println("ověřeno");
        } else {
            System.out.println("neověřeno");
        }

    }
}
