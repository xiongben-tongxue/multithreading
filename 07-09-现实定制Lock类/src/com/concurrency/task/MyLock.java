package com.concurrency.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Classname MyLock 自定义锁实现类
 * @Description TODO
 * @Date 2019-02-02 16:16
 * @Created by wangdong
 */
public class MyLock implements Lock {

    /**
     * 用来实现锁的同步器
     */
    private AbstractQueuedSynchronizer sync;

    public MyLock() {
        sync = new MyAbstractQueuedSynchronizer();
    }

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * 获取锁
     * 如果获取不到锁
     * 会阻塞线程直到锁释放
     * 阻塞的线程可以被中断
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 获取锁
     * 如果获取到锁就返回true
     * 如果获取不到锁就返回false
     * @return
     */
    @Override
    public boolean tryLock() {

        try {
            return sync.tryAcquireNanos(1,1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在指定的时间内获取锁
     * 如果获取到就返回true
     * 否则就返回false
     * @param time 时间量
     * @param unit 时间单位
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,TimeUnit.NANOSECONDS.convert(time, unit));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * 创建一个新的条件变量
     * @return
     */
    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }
}
