package com.concurrency.task;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SearchTask
 * @Description 如果线程完成了任务，并且没有中断，就存储线程的名字
 * @Date 2019-02-11 14:29
 * @Created by wangdong
 */
public class SearchTask implements Runnable{

    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s: Start\n", name);

        //捕捉异常
        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s: Interrupted\n", name);
            return;
        }
        System.out.printf("Thread %s: End\n", name);
    }

    /**
     * 模拟搜索的操作
     * @throws InterruptedException 中断异常
     */
    private void doTask() throws InterruptedException {
        Random random = new Random((new Date()).getTime());
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);

    }
}
