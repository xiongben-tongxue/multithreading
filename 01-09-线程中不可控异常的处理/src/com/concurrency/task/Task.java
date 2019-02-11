package com.concurrency.task;

/**
 * @Classname Task
 * @Description 任务类，专门抛出异常
 * 实现Runnable接口
 * @Date 2019-02-11 13:42
 * @Created by wangdong
 */
public class Task implements Runnable{
    @Override
    public void run() {
        //下面的语句会抛出异常,因为TTT是没法转换成int的
        int number = Integer.parseInt("TTT");
    }
}
