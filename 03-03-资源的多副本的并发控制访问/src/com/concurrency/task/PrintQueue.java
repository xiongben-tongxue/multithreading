package com.concurrency.task;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname PrintQueue
 * @Description 打印队列对象，可以控制三台打印机，使用信号量来控制打印机的访问
 * 当有一个打印机作业时，如果有空闲的打印机就将作业分配到某个打印机上，否则就等待打印机空闲，再分配打印机
 * @Date 2019-02-12 13:23
 * @Created by wangdong
 */
public class PrintQueue {
    /**
     * 资源信号量
     */
    private Semaphore semaphore;

    /**
     * 标记打印机是否空闲的数组
     */
    private boolean[] freePrinters;

    /**
     * 锁，控制打印机是否空闲的数组的访问
     */
    private Lock lockPrinters;

    /**
     * 构造函数，初始化变量
     */
    public PrintQueue() {
        //资源信号量的个数为3，说明有3个打印机
        semaphore = new Semaphore(3);
        //针对打印机是否空闲的数组也为3
        freePrinters = new boolean[3];
        //初始值，都为空闲
        for (int i = 0; i < freePrinters.length; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document){
        try {
            //请求信号量，如果有一个打印机是空闲的，就会访问其中的一个空闲的打印机
            semaphore.acquire();

            //获取分配的空闲打印机的编号
            int assignedPrinter = getPrinter();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n", Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            //释放打印机状态数组
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放信号量
            semaphore.release();
        }
    }

    /**
     * 获取分配的打印机编号
     * @return
     */
    private int getPrinter(){
        int ret = -1;
        try {
            //获取打印机状态数组的访问能力
            //上锁
            lockPrinters.lock();
            //查找一个空闲的打印机
            for (int i = 0; i < freePrinters.length; i++) {
                //如果空闲，进入执行
                if (freePrinters[i]){
                    //将打印机的编号赋值给ret,并将此i打印机数组改为false不空闲
                    ret =i;
                    freePrinters[i]=false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放打印机状态数组的访问能力
            lockPrinters.unlock();
        }
        return ret;
    }
}
