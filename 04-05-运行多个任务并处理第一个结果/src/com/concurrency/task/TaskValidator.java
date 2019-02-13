package com.concurrency.task;

import java.util.concurrent.Callable;

/**
 * @Classname TaskValidator
 * @Description 任务验证类，用来执行UserValidator对象作为并发任务的验证过程
 * 指定它实现Callable接口，并参数化为String类型
 * @Date 2019-02-13 11:01
 * @Created by wangdong
 */
public class TaskValidator implements Callable<String> {

    /**
     * 声明一个私有的、类型为UserValidator、名为validator的属性。
     */
    private UserValidator validator;
    /**
     * 声明私有的、类型为String、名为user的属性。
     */
    private String user;
    /**
     * 声明私有的、类型为String、名为password的属性。
     */
    private String password;

    /**
     * 构造函数，初始化属性
     * @param validator
     * @param user
     * @param password
     */
    public TaskValidator(UserValidator validator, String user, String password) {
        this.validator = validator;
        this.user = user;
        this.password = password;
    }

    /**
     * 核心方法
     * 使用用户验证对象进行用户名和密码的验证
     * 如果验证通过就返回验证的名字
     * 否则就抛出异常
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        if (validator.validate(user,password)){
            // 否则，写入一条信息到控制台表明用户已通过验证，并返回UserValidator对象的名称。
            System.out.printf("%s: The user has been found\n", validator.getName());
            return validator.getName();
        }
        // 如果用户没有通过UserValidator对象验证，写入一条信息到控制台，表明这种情况，并且抛出一个Exception异常
        System.out.printf("%s: The user has not been found\n", validator.getName());
        throw new Exception("Error validating user");
    }
}
