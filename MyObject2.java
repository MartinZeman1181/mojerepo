package cz.zeman.java9.autentizace;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author martin
 */
public class MyObject2  implements Serializable {

    private int x;
    private long t;

    public MyObject2(int x, long t) {
        this.x = x;
        this.t = t;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

}
