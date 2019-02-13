package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Classname Student
 * @Description 学生类
 * @Date 2019-02-12 18:53
 * @Created by wangdong
 */
public class Student implements Runnable{
    /**
     * 控制线程执行的阶段对象
     */
    private Phaser phaser;

    /**
     * 构造函数
     * @param phaser 线程执行时阶段性
     */
    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    /**
     * 核心方法
     * 进入考试状态
     * 做三个测试题，每做完一个测试题，每个测试题是一个阶段
     * 它调用阶段对象等待所有其他的学生完成同样的测试题
     *
     */
    @Override
    public void run() {
        //第一个阶段：准备开始做题
        System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        //这里需要等待所有的学生都完成了第一阶段，代码才能往下走

        //第二个阶段：开始做第一道题
        System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise1();
        System.out.printf("%s: Has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
        //这里需要等待所有的学生都完成了第一阶段，代码才能往下走

        //第三个阶段：开始做第二道题
        System.out.printf("%s: Is going to do the second exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise2();
        System.out.printf("%s: Has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s: Is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
        doExercise3();
        System.out.printf("%s: Has finished the exam. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
    }

    /**
     * 做第一个测试题
     * 并且休眠[0,10)
     */
    private void doExercise1() {

        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 做一个测试题，并且休眠[0, 10)秒
     */
    private void doExercise2() {
        try {
            Long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 做一个测试题，并且休眠[0, 10)秒
     */
    private void doExercise3() {
        try {
            Long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
