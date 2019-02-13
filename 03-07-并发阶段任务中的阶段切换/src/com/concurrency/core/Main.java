package com.concurrency.core;

import com.concurrency.task.MyPhaser;
import com.concurrency.task.Student;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 19:33
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个阶段对象
        MyPhaser phaser = new MyPhaser();
        //创建五个学生对象，将他们注册到同一个阶段对象中
        Student student[] = new Student[5];
        for (int i = 0; i < student.length; i++) {
            student[i] = new Student(phaser);
            //注册
            phaser.register();
        }

        //创建5个线程来运行5个学生对象，并且启动线程
        Thread[] threads = new Thread[5];
        for (int i = 0; i < student.length; i++) {
            threads[i] = new Thread(student[i]);
            threads[i].start();
        }

        //等待所有线程完成执行
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //检测阶段是否已经达到了完成状态
        System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
    }
}
