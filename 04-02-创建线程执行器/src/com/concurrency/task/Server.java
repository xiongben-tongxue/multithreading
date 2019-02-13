package com.concurrency.task;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname Server
 * @Description 服务类，模拟一个服务器
 * @Date 2019-02-13 09:22
 * @Created by wangdong
 */
public class Server {

    /**
     * 线程池执行器
     * 管理请求线程的执行
     */
    private ThreadPoolExecutor executor;

    /**
     * 构造函数，创建线程执行器对象
     * 本质是一个缓冲线程池对象
     */
    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    /**
     * @Title executeTask
     * @Description 任务执行方法 接收Task对象作为参数并将其提交到执行者
     * @Param task
     * @Return void
     * @Date 2019-02-13 09:29
     * @Auther wangdong
     */
    public void executeTask(Task task){
        // 首先，写入一条信息到控制台，表明有一个新的任务到达。
        System.out.printf("Server: A new task has arrived\n");
        //然后，调用执行者的Execute()方法来提交这个任务
        executor.execute(task);
        // 最后，将执行者的数据写入到控制台来看它们的状态
        System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
    }

    /**
     * 结束执行的方法
     */
    public void endServer(){
        //调用执行者的shutdown()方法来结束任务执行
        executor.shutdown();
    }
}
