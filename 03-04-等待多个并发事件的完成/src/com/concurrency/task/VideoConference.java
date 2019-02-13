package com.concurrency.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Classname VideoConference
 * @Description 视频会类  使用倒计时闩来控制所有的参与者都达到后才发生事件
 * @Date 2019-02-12 14:05
 * @Created by wangdong
 */
public class VideoConference implements Runnable{

    /**
     * 倒计时闩对象
     */
    private final CountDownLatch controller;

    /**
     * 构造函数，初始化倒计时门闩
     * 在这个整数controller倒数到0之前，主线程需要等待在门口
     * @param
     */
    public VideoConference(int number ) {
        this.controller = new CountDownLatch(number);
    }

    /**
     * @Description 每个参与者参与到视频会议都会调用此方法
     * 例如，假设controller初始值为3，.countDown()方法调用一次就会减1
     * @Param: [name]
     * @Return: void
     * @Date 2019-02-12 14:09
     * @Auther wangdong
     */
    public void arrive(String name){
        System.out.printf("%s has arrived.\n", name);
        //用于使计数器减1
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }

    /**
     * 核心方法，当所有参与者都达到了，就开始视频会议
     * 这个主线程会等待其他线程倒计时，如果在10秒钟后，还没到齐就不等了
     */
    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            //这个视频会议线程会等待其他参与者线程倒计时，如果在10秒钟没有倒计时到0，就不等了
            controller.await(10, TimeUnit.SECONDS);
            // Starts the conference
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
