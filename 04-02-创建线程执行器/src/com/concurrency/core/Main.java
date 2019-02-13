package com.concurrency.core;

import com.concurrency.task.Server;
import com.concurrency.task.Task;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-13 09:43
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();

        //发送100个任务到服务器对象，并且完成任务
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }

        server.endServer();
    }
}
