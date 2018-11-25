/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.autentizace;

import java.io.Serializable;

/**
 *
 * @author martin
 */
public class MyObject1 implements Serializable {

    public Long lon;
    public String str;

    public MyObject1(Long lon, String str) {
        this.lon = lon;
        this.str = str;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

}
