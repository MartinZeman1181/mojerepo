/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.autentizace;

import java.math.BigInteger;

/**
 *
 * @author martin
 */
public class FiatShamir {

    public static void main(String[] arg) {
        BigInteger s = BigInteger.valueOf(7877L);  //tajný klíč
        //veřejné parametry n a v
        BigInteger n = BigInteger.valueOf(1123L);
        BigInteger v = (s.multiply(s)).mod(n);
        System.out.println("v = (s.multiply(s)).mod(n) = " + v.toString());
        //uživatel zvolí náhodnou hodnotu r
        BigInteger r = BigInteger.valueOf(9973L);
        //a ověřivateli zašle výraz
        BigInteger c = (r.multiply(r)).mod(n);
        System.out.println("c = (r.multiply(r)).mod(n) = " + c.toString());
        //ověřovatel vybere náhodnou hodnotu e
        BigInteger e = BigInteger.valueOf(17L);
        //--
        BigInteger se = s.pow(e.intValue()); // se = s ** e
        //--   uživatel odešle ověřovateli vypočtený vztah a     
        BigInteger a = (se.multiply(r)).mod(n);
        System.out.println("a = (se.multiply(r)).mod(n) = " + a.toString());
        //-- ověřovatel kontroluje zda (a * a).mod(n) =(c * v **e).mod(n)
        BigInteger ve = v.pow(e.intValue()); // ve = v ** e
        //--       
        BigInteger srovnej1 = (ve.multiply(c)).mod(n);
        System.out.println("(ve.multiply(c)).mod(n) = " + srovnej1.toString());
        BigInteger srovnej2 = (a.multiply(a)).mod(n);
        System.out.println("(a.multiply(a)).mod(n) = " + srovnej2.toString());
        if (srovnej1.compareTo(srovnej2) == 0) {
            System.out.println("OVĚŘENO");
        } else {
            System.out.println("NEOVĚŘENO");
        }

    }

}
