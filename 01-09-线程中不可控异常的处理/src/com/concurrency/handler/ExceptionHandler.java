package com.concurrency.handler;

/**
 * @Classname ExceptionHandler
 * @Description 异常处理类，处理线程中抛出的未捕获的异常
 * @Date 2019-02-11 13:48
 * @Created by wangdong
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    /**
     * 处理线程中抛出的未捕获的异常
     * @param t 异常的线程
     * @param e 抛出的异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getId());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n", t.getState());
    }
}
