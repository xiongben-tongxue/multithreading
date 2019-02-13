package com.concurrency.task;

import com.concurrency.utils.Buffer;

import java.util.Random;

/**
 * @Classname Consumer
 * @Description 消费者 从缓冲区中取出数据
 * @Date 2019-02-12 10:53
 * @Created by wangdong
 */
public class Consumer implements Runnable{

    /**
     * 缓冲对象
     */
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * 核心方法，当缓冲区中有数据就去处理
     */
    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            String line = buffer.get();

        }
    }

    /**
     * @Description 模拟处理一行数据，休眠[0,100)毫秒
     * @Param: [line]
     * @Return: void
     * @Date 2019-02-12 10:57
     * @Auther wangdong
     */
    private void processLine(String line){
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
