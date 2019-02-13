package com.concurrency.core;

import com.concurrency.task.Job;
import com.concurrency.task.PrintQueue;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 09:58
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个打印队列
        PrintQueue printQueue = new PrintQueue();

        //创建10个打印任务并且将其放入到不同的线程中运行
        Thread threads[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue));
        }

        //每隔0.1s运行一个线程，一共有10个线程
        for (int i = 0; i < 10; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
