/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.autentizace;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

/*
p: prvočíslo
g: dělitel čísla p - 1
a: (a.pow(g)).mod(p) = 1 // hledáme takové a
vyber tajný klíč s: 0 < s < g
veřejně v: v1 = a.pow(s);  (v1 * v).mod(p) = 1
vyber r: 0 < r < g
vypočti: x = (a.pow(r).mod(p)
vypočti e: e = SHA1(byty zprávy || byty čísla x);
vypočti y: y = (r + s*e).mod(g);
Odešli oběřovazteli:
1) zprávu M
2) podpis: (e, y)
----------------- veriikace prováděná ověřovatelem ----------------
a) přijato: M, e, y
b) veřejně známo: a, p, g, v
vypočti xp = x.mod(p);
vypočti: ep = SHA1(byty zprávy || byty čísla xp);
pokud: ep = e potom je podpis ověřen
*/
public class Schnorr {

    public static void main(String[] arg) {
       
        Random rand = new Random();
        long nextLong = rand.nextLong();
        BigInteger a = BigInteger.valueOf(nextLong);
        a = a.multiply(a);
        
        BigInteger p = BigInteger.valueOf(2L);
        p = (p.pow(20)).subtract(BigInteger.ONE);
        while (!p.isProbablePrime(32000)) {
            p = p.add(BigInteger.ONE);
        }
        /*
        BigInteger p = BigInteger.valueOf(1048583L);
        BigInteger a = new BigInteger("38106514387444973277982379189722939393");
        BigInteger g = BigInteger.valueOf(8525L); // dělitel čísla p - 1
        BigInteger s = BigInteger.valueOf(4262L); // secret 
        BigInteger v = BigInteger.valueOf(5174L); // public
        */
      
        BigInteger pm1 = p.subtract(BigInteger.ONE);
        BigInteger g = BigInteger.valueOf(2L);
        BigInteger g_max = BigInteger.ZERO;
        BigInteger a_max = BigInteger.ZERO;
        int i = 0;
        c0:
        while (i < 10000) {
            BigInteger[] divideAndRemainder = pm1.divideAndRemainder(g);
            if (divideAndRemainder[1].longValue() == 0L) {
                long j = 0;
                while (j < 10000000) {
                    if ((a.modPow(g, p)).longValue() == 1L) {
                        //System.out.println("a = " + a.toString(10) + " , g = " + g.toString(10));
                        g_max = g;
                        a_max = a;
                        //break c0;
                    }
                    a = a.add(BigInteger.ONE);
                    j++;
                }

            }
            g = g.add(BigInteger.ONE);
            i++;
        }
        a = a_max;
        g = g_max;
        //System.out.println("a = " + a.toString(10) + " , g = " + g.toString(10));
        //System.out.println("konec");

        BigInteger s = g.divide(BigInteger.ONE.add(BigInteger.ONE)); // s = g/2
        //System.out.println("s = " + s.toString(10));
        BigInteger v = BigInteger.valueOf(2L);
        long mez = 0;
        BigInteger pom = a.pow(s.intValue());
        while (!(((pom.multiply(v)).mod(g)).longValue() == 1L)) {
            v = v.add(BigInteger.ONE);           
            if (mez > 10000000) {
                
                System.out.println("VYSKOK");
                break;
            }
            mez++;
        }
        
        BigInteger r = (g.multiply(BigInteger.valueOf(2L))).divide(BigInteger.valueOf(3L));
        BigInteger x  = a.modPow(r, p);
        System.out.println("p = " + p.toString(10) + 
                " ,a = " + a.toString(10) + 
                " ,g = " + g.toString(10) + 
                " ,s = " + s.toString(10) + 
                " ,v = " + v.toString(10) +         
                " ,r = " + r.toString(10) +  
                " ,x = " + x.toString(10)         
        );
       
    }

}
