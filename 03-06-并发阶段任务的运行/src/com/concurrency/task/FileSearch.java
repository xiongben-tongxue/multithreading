package com.concurrency.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Classname FileSearch
 * @Description 文件查找类,它将在一个文件夹及子文件夹中查找过去24小时内修改过的指定拓展名的文件
 * @Date 2019-02-12 16:29
 * @Created by wangdong
 */
public class FileSearch implements Runnable{

    /**
     * 用于查找的文件夹
     */
    private String initPath;
    /**
     * 要查找的文件的扩展名
     */
    private String end;
    /**
     * 查找到的文件的完整路径，可能会有多个结果，所以用List
     */
    private List<String> results;

    /**
     * 阶段对象，用来控制任务不同阶段的同步
     */
    private Phaser phaser;

    /**
     * 构造函数，初始化声明的属性
     * @param initPath
     * @param end
     * @param phaser
     */
    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        this.results = new ArrayList<>();
    }


    /**
     * 核心方法
     * 在Phaser中，它把多个线程协作执行的任务划分为多个阶段，编程时需要明确各个阶段的任务
     * 每个阶段都可以有任意个参与者，线程都可以随时注册并参与到某个阶段
     * 每个Phaser实例都会维护一个phase number，初始值为0。
     * 每当所有注册的任务都到达Phaser时，phase number累加，并在超过Integer.MAX_VALUE后清零。
     * arrive()和arriveAndDeregister()方法用于记录到达；
     * 其中arrive()，某个参与者完成任务后调用；
     * arriveAndDeregister()，任务完成，取消自己的注册。
     * arriveAndAwaitAdvance()，自己完成等待其他参与者完成，进入阻塞，直到Phaser成功进入下个阶段。
     * 例如。同时启动了三个线程1、2、3。
     * 执行结果就是：
     * 线程1执行完成，等待其他任务执行
     * 线程2执行完成，等待其他任务执行
     * 线程3执行完成，等待其他任务执行
     * 在执行中调用了arriveAndAwaitAdvance()方法
     * 当三个线程都执行完了，每个线程才会执行arriveAndAwaitAdvance()下面的代码
     */
    @Override
    public void run() {
        //等待所有文件对象被创建
        //在这前面有线程在创建文件，只有线程创建文件的工作完成后，才会执行它下面的代码
        this.phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

        //第一个阶段：查找所有的文件
        File file = new File(initPath);
        //如果文件是文件夹(目录)
        if (file.isDirectory()){
            directoryProcess(file);
        }

        //如果没有找到结果，就从这个阶段对象中注销，并且退出程序
        if (!checkResults()){
            return;
        }

        //第二阶段：过滤查找到的结果
        filterResults();

        //如果过滤后没有结果，就从这个阶段对象中注销，并且退出程序
        if (!checkResults()){
            return;
        }

        //第三阶段：显示查找的信息
        showInfo();
        //通知phaser对象，当前线程已经结束这个阶段，并且不再参与接下来的阶段操作
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
    }

    /**
     * 将结果集中的元素打印到控制台中
     */
    private void showInfo(){
        for (String result: this.results) {
            File file = new File(result);
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        //通知Phaser对象，当前线程已经完成了当前阶段，需要被阻塞直到其他线程都完成当前阶段
        this.phaser.arriveAndAwaitAdvance();
    }

    /**
     * 文件过滤的方法，将不是24小时内修改过的文件删除掉
     */
    private void filterResults(){
        List<String> newResults = new ArrayList<>();
        //获取现在的时间
        long actualDate = new Date().getTime();
        for (String result: results) {
            File file = new File(result);
            //获取文件的修改时间
            long fileDate = file.lastModified();
            //表明修改是在24小时前发生的
            //这个一过滤，只是将24小时内修改过的文件留下了了
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)){
                newResults.add(result);
            }
        }
        results = newResults;
    }

    /**
     * 检查结果集是否为空，如果结果集为空就从阶段对象中注销
     * 否则等待其他的线程完成同样的任务阶段
     * @return false结果集为空，true结果集不为空
     */
    private boolean checkResults(){
        if (this.results.isEmpty()){
            //方法getPhase()获取的是已经到达第几个屏障。
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
            //通知Phaser对象，当前线程已经结束这个阶段，并且将不再参与接下来的阶段操作
            this.phaser.arriveAndDeregister();
            return false;
        }else {
            System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            //通知Phaser对象，当前线程已经完成了当前阶段，需要被阻塞到直到其他线程都完成了当前阶段
            this.phaser.arriveAndAwaitAdvance();
            return true;
        }

    }

    /**
     * @Title directoryProcess
     * @Description 文件夹目录处理方法
     * @Param file
     * @Return void
     * @Date 2019-02-12 16:52
     * @Auther wangdong
     */
    private void directoryProcess(File file){
        //获取file目录下的所有的文件和目录
        File list[] = file.listFiles();
        //不为空
        if (list != null){
            //遍历它
            for (File aFile: list) {
                //如果是文件目录，递归处理它
                if (aFile.isDirectory()){
                    directoryProcess(aFile);
                    //如果是文件，就调用文件处理的方法
                }else {
                    fileProcess(aFile);
                }
            }
        }
    }

    /**
     * @Title fileProcess
     * @Description 文件处理方法
     * @Param file
     * @Return void
     * @Date 2019-02-12 16:52
     * @Auther wangdong
     */
    private void fileProcess(File file){
        //如果文件是以指定的后缀点结束的，就将文件的绝对路径保存到结合结果中去
        if (file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }
}
