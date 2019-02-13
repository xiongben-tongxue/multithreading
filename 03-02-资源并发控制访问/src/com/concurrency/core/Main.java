package com.concurrency.core;

import com.concurrency.task.Job;
import com.concurrency.task.PrintQueue;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 13:10
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个打印队列的对象
        PrintQueue printQueue = new PrintQueue();

        //创建10个线程
        Thread threads[] = new Thread[10];
        //启动10个线程
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue),"Thread " + i);
            threads[i].start();
        }
    }
}
