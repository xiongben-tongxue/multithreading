package com.concurrency.core;

import com.concurrency.task.UnSafeTask;

import java.util.concurrent.TimeUnit;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 14:08
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建线程不安全的任务
        UnSafeTask task = new UnSafeTask();

        //将任务进入三个不同的线程中
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
