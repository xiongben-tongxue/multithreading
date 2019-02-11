package com.concurrency.group;

/**
 * @Classname MyThreadGroup
 * @Description TODO
 * @Date 2019-02-11 15:05
 * @Created by wangdong
 */
public class MyThreadGroup extends ThreadGroup{

    /**
     * 构造函数
     * @param name 线程组的姓名
     */
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 打印线程的名称
        System.out.printf("The thread %s has thrown an Exception\n", t.getId());
        // 输出异常栈信息
        e.printStackTrace(System.out);
        // 中断线程组中其余的线程
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();
    }
}
