package com.concurrency.core;

import com.concurrency.task.PrimeGenerator;

import java.util.concurrent.TimeUnit;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 09:35
 * @Created by wangdong
 */
public class Main {

    public static void main(String[] args) {

        Thread thread = new PrimeGenerator();
        //启动质数生成线程
        thread.start();

        try {
            //主线程休眠5秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //质数生成线程中断
        thread.interrupt();
    }
}
