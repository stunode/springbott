package com.nzxs2.rabbitmq;

import java.io.Serializable;

/**
 * @Author Ryan
 * @Date 2017/9/22 12:09
 * @Function
 */
public class User implements Serializable {
    private String name;
    private String pass;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
