package com.concurrency.utils;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname Buffer
 * @Description 缓冲区
 * @Date 2019-02-12 10:22
 * @Created by wangdong
 */
public class Buffer {

    /**
     * 集合对象，被当作缓冲区使用
     */
    private LinkedList<String> buffer;

    /**
     * 缓冲区的最大大小
     */
    private int maxSize;

    /**
     * 控制缓冲区的锁
     */
    private ReentrantLock lock;

    /**
     * 缓冲区有数据的条件
     */
    private Condition lines;

    /**
     * 缓冲区为空的条件
     */
    private Condition space;

    /**
     * 是否追加行
     */
    private boolean pendingLines;

    /**
     * 构造函数，初始化属性
     *
     * @param maxSize 缓冲最大大小
     */
    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.lines = lock.newCondition();
        this.space = lock.newCondition();
        this.pendingLines = true;
    }

    /**
     * 向缓冲区中插入一行数据
     * @param line
     */
    public void insert(String line){
        //上锁
        this.lock.lock();

        try {
            //如果已经满了就等待
            while (this.buffer.size() == this.maxSize){
                this.space.await();
            }
            //不满则继续往下走,插入数据
            this.buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), this.buffer.size());
            //唤醒所有等待在Condition上的线程
            this.lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 获取数据
     * @return
     */
    public String get(){
        String line = null;
        this.lock.lock();
        try {
            //缓冲区为空，则等待
            while (this.buffer.size() == 0 && hasPendingLines()){
                this.lines.await();
            }
            //不为空，则去取数据
            if (hasPendingLines()){
                line=this.buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), this.buffer.size());
                //唤醒所有条件下等待的线程
                this.space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
        return line;
    }

    /**
     * 设置是否追加行
     * @param pendingLines
     */
    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    /**
     * 判断是否有数据可以进行处理
     * @return
     */
    public boolean hasPendingLines(){
        return this.pendingLines || this.buffer.size() > 0;
    }
}
