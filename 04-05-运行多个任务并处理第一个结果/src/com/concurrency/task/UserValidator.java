package com.concurrency.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Classname UserValidator
 * @Description 用户校验类，实现用户验证过程
 * @Date 2019-02-13 10:46
 * @Created by wangdong
 */
public class UserValidator {

    /**
     * 声明一个私有的、类型为String、名为name的属性，用来存储系统验证用户的名称。
     */
    private String name;

    /**
     * 构造函数，初始化用户名称
     *
     * @param name 用户名称
     */
    public UserValidator(String name) {
        this.name = name;
    }

    /**
     * 验证方法，根据用户名和密码进行验证
     * @param name 用户名
     * @param password 密码
     * @return true验证通过，false验证失败
     */
    public boolean validate(String name,String password){
        //创建Random对象
        Random random = new Random();

        //等待一个随机时间，用来模拟用户验证的过程
        Long duration = (long) (Math.random() * 10);
        System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            //抛错了就返回false验证失败
            return false;
        }
        //返回一个随机的Boolean值。如果用户验证通过，这个方法将返回true
        //否则，返回false
        return random.nextBoolean();
    }

    /**
     * 返回name属性值
     * @return
     */
    public String getName(){
        return name;
    }
}
