package com.concurrency.core;

import com.concurrency.task.Job;
import com.concurrency.task.PrintQueue;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 20:41
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个打印队列
        PrintQueue printQueue = new PrintQueue();

        //创建10个打印线程
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue),"Thread"+i);
            thread[i].start();
        }

    }
}
