package com.concurrency.core;

import com.concurrency.event.Event;
import com.concurrency.task.CleanerTask;
import com.concurrency.task.WriterTask;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 11:13
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个用于存放事件对象的队列
        Deque<Event> deque = new ArrayDeque<>();

        //创建一个写任务的对象，并且创建3个线程去调用这个对象
        //这个实现了Runable接口，所以它需要放在Thread对象中去启动
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }

        //创建一个事件清除任务，并且启动这个任务
        //因为CleanerTask继承了Thread类，所有它可以直接启动
        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();
    }
}
