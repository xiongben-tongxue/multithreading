package com.concurrency.core;

import com.concurrency.task.SafeTask;

import java.util.concurrent.TimeUnit;

/**
 * @Classname SafeMain
 * @Description TODO
 * @Date 2019-02-11 14:18
 * @Created by wangdong
 */
public class SafeMain {
    public static void main(String[] args) {
        //创建一个任务
        SafeTask task = new SafeTask();

        //将任务放入三个不同的线程中运行
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
