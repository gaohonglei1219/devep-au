package com.devep.common;

public class ThreadUtil {
    public static void sleepBySecond(int second){
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
