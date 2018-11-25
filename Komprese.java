/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.autentizace;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author martin
 */
public class Komprese {

    public static void main(String[] arg) throws IOException {
        MyObject1 myObj1 = new MyObject1(3333L, "Anka");
        MyObject2 myObj2 = new MyObject2(112, 777777L);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
        ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut);
        for (int i = 0; i < 10000; i++) {
            objectOut.writeObject(myObj1);
        }
        for (int i = 0; i < 10000; i++) {
            objectOut.writeObject(myObj2);
        }
        objectOut.close();
        byte[] bytes = baos.toByteArray();
    }
}
