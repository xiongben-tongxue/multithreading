package com.concurrency.task;

import com.concurrency.utils.Buffer;
import com.concurrency.utils.FileMock;

/**
 * @Classname Producer
 * @Description 数据生产者 将文件对象中的数据，插入到缓冲区
 * @Date 2019-02-12 10:46
 * @Created by wangdong
 */
public class Producer implements Runnable{

    /**
     * 文件模拟对象
     */
    private FileMock fileMock;

    /**
     * 缓冲对象
     */
    private Buffer buffer;

    public Producer(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }

    /**
     * 核心方法，读取文件中的数据
     * 并且将读取到的数据插入到缓冲区
     */
    @Override
    public void run() {
        //设置为可追加行
        this.buffer.setPendingLines(true);
        //如果文件对象有可处理的数据就处理
        while (this.fileMock.hasMoreLines()){
            //取出一行
            String line = this.fileMock.getLine();
            //插入到缓冲区
            this.buffer.insert(line);
        }
        //设置不可追加行
        this.buffer.setPendingLines(false);
    }
}
