package com.concurrency.core;

import com.concurrency.task.Participant;
import com.concurrency.task.VideoConference;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-12 14:37
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个视频会议对象，它有10个参与者
        VideoConference conference = new VideoConference(10);
        //去创建一个线程来运行视频会议
        Thread threadConference = new Thread(conference);
        threadConference.start();

        //创建是个参与者，每个参与者在一个单独的线程中运行
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(conference,"Participant " + i);
            Thread t = new Thread(participant);
            t.start();
        }
    }
}
