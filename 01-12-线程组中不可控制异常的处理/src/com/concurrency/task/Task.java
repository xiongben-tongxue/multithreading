package com.concurrency.task;

import java.util.Random;

/**
 * @Classname Task
 * @Description 创建一个任务类，实现了Runnable接口
 * @Date 2019-02-11 15:00
 * @Created by wangdong
 */
public class Task implements Runnable{
    @Override
    public void run() {
        int result;
        //创建一个随机数生成器
        Random random = new Random(Thread.currentThread().getId());
        while (true){
            // 生成一个[0, 1000)内有随机整数，并且有1000除以这个数，求得商
            result = (int) (1000 / (random.nextDouble() * 1000));
            System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
            //检测当前线程是否被中断
            if (Thread.currentThread().isInterrupted()){
                System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }
}
