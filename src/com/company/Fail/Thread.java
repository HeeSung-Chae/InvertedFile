package com.company.Fail;

public class Thread extends java.lang.Thread {
    public void run(){
        try {
            Thread.sleep((long)0.1);
        } catch (Exception e) {

        }
    }
}
