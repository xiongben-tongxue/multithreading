package com.concurrency.core;

import com.concurrency.task.FileSearch;

import java.util.concurrent.Phaser;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 18:33
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个阶段对象，它有三个参与者
        Phaser phaser = new Phaser(3);

        //创建三个文件搜索对象，每个搜索不同的目录
        FileSearch system = new FileSearch("/Users/wangdong/TestSpace/Windows","log",phaser);
        FileSearch apps = new FileSearch("/Users/wangdong/TestSpace/Program","log", phaser);
        FileSearch documents = new FileSearch("/Users/wangdong/TestSpace/Documents","log", phaser);

        //创建一个线程运行文件搜索对象，并且启动线程
        Thread systemThread = new Thread(system,"system");
        systemThread.start();

        //创建一个线程运行文件搜索对象，并且启动线程
        Thread appsThread = new Thread(apps,"apps");
        appsThread.start();

        //创建一个线程运行文件搜索对象，并且启动线程
        Thread documentsThread = new Thread(documents,"documents");
        documentsThread.start();

        //等待所有的线程都结束
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * isTerminated表示结束
         */
        System.out.printf("Terminated: %s\n", phaser.isTerminated());

    }
}
