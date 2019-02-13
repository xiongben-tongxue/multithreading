package com.concurrency.task;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Classname PricesInfo
 * @Description 价格信息类，这个类存储了两个价格，一个写者写这个价格，多个读者读这个价格
 * @Date 2019-02-11 20:48
 * @Created by wangdong
 */
public class PricesInfo {

    /**
     * 两个价格
     */
    private double price1;
    private double price2;

    /**
     * 读写锁
     * 控制价格访问的锁
     */
    private ReadWriteLock lock;

    /**
     * 构造函数，初始化价格和锁
     */
    public PricesInfo() {
        this.price1 = 1.0;
        this.price2 = 2.0;
        this.lock = new ReentrantReadWriteLock();
    }

    /**
     * 使用读锁控制读价格
     * 获取第一个价格
     */
    public double getPrice1() {
        //上锁
        lock.readLock().lock();
        double value = price1;
        //解锁
        lock.readLock().unlock();
        return value;
    }

    /**
     * 获取第二个锁
     * @return
     */
    public double getPrice2() {
        //上锁
        lock.readLock().lock();
        double value = price2;
        //解锁
        lock.readLock().unlock();
        return value;
    }

    /**
     * @Description 设置两个价格
     * @Param: [price1, price2]
     * @Return: void
     * @Date 2019-02-12 09:26
     * @Auther wangdong
     */
    public void setPrices(double price1,double price2) {
        //上锁
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        //解锁
        lock.writeLock().unlock();
    }
    
}
