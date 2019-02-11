package com.concurrency.task;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Classname MyAbstractQueuedSynchronizer 自定义抽象队列同步器
 * @Description TODO
 * @Date 2019-02-02 16:24
 * @Created by wangdong
 */
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer{

    private static final long serialVersionUID = 1L;

    //原子变量，存储锁的状态，0闲，1忙
    private AtomicInteger state;

    /**
     * 构造函数
     * 默认state是闲的
     */
    public MyAbstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    /**
     * 获取锁
     * @param arg （在本方法中不使用）
     * @return true获取，false未获取
     * compareAndSet 通过原子操作实现了CAS操作
     * 底层通过判断当前状态值是否等于期望值0，如果等于，则将值更新为给定的更新值1
     */
    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0,1);
    }

    /**
     * 释放锁
     * @param arg
     * @return true成功，false失败
     */
    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1,0);
    }
}
