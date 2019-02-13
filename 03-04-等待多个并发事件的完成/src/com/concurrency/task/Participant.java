package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * @Classname Participant
 * @Description 参与者的类
 * @Date 2019-02-12 14:23
 * @Created by wangdong
 */
public class Participant implements Runnable {

    /**
     * 视频会议对象
     */
    private VideoConference conference;

    /**
     * 参与者的名称，仅仅为了记录使用
     */
    private String name;

    /**
     * @Description 构造函数
     * @Param: [conference, name]
     * @Return:
     * @Date 2019-02-12 14:32
     * @Auther wangdong
     */
    public Participant(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    /**
     * 核心方法，等待一个随机时间就进入视频会议
     */
    @Override
    public void run() {
        //生成10以内的随机数
        long duration = (long) (Math.random() * 10);
        //可以休眠duration秒进入视频会议
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
}
