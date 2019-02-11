package com.concurrency.task;

import java.io.File;

/**
 * @Classname FileSearch
 * @Description 文件的搜索类
 * @Date 2019-02-11 09:43
 * @Created by wangdong
 */
public class FileSearch implements Runnable {

    /**
     * 搜索的初始路径
     */
    private String initPath;

    /**
     * 需要搜索的文件名
     */
    private String fileName;

    /**
     * 构造函数
     * @param initPath
     * @param fileName
     */
    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            directoryProcess(new File(initPath));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空资源，在本例中为空
     */
    private void cleanResources(){

    }

    private void directoryProcess(File file) throws InterruptedException {
        File[] list = file.listFiles();//获取当前目录中的所有文件
        if (list != null){//如果当前目录下有文件
            //遍历所有文件
            for (int i = 0; i < list.length; i++) {
                //如果是一个目录
                if (list[i].isDirectory()){
                    //递归处理
                    directoryProcess(list[i]);
                }else {
                    //如果是一个文件，调用文件的处理方法
                    fileProcess(list[i]);
                }
            }
        }
    }

    /**
     * 文件处理方法
     * @param file 待处理的文件名
     */
    private void fileProcess(File file) throws InterruptedException {
        //如果当前文件名和要查找的文件名同名，就输出信息
        if (file.getName().equals(this.fileName)) { // 当前文件名与要查找的文件同名，就输出信息
            System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        if (Thread.interrupted()){
            throw new InterruptedException();
        }
    }
}
