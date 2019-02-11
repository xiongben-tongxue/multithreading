package com.concurrency.core;

import com.concurrency.task.FileSearch;

import java.util.concurrent.TimeUnit;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 10:03
 * @Created by wangdong
 */
public class Main {

    public static void main(String[] args) {
        //创建一个运行对象和一个运行它的线程
        FileSearch fileSearch = new FileSearch("/Users/wangdong/Documents/","不止代码.pdf");
        Thread thread = new Thread(fileSearch);
        //启动线程
        thread.start();

        try {
            //这里主线程休眠10s的意思就是，等待10s种就会执行后续的中断代码了
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断线程
        thread.interrupt();
    }
}
