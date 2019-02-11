package com.concurrency.core;

import com.concurrency.task.Account;
import com.concurrency.task.Bank;
import com.concurrency.task.Company;

/**
 * @Classname Main
 * @Description 这个没有使用synchronized进行加锁
 * 在多线程环境下，就会发生数据错误，每次的结果都不一样甚至会出现负数
 * @Date 2019-02-11 15:59
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个账户对象
        Account account = new Account();
        //账户初始值为1000
        account.setBalance(1000);

        //公司和银行共享的是同一个账号

        //创建一个公司对象，公司需要一个账户，并且让公司对象在一个线程中运行
        //公司的是增加
        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        //创建一个银行对象，并且让银行对象在一个线程中运行
        //银行的是减少
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);


        // 输出初始的余额
        System.out.printf("Account : Initial Balance: %f\n", account.getBalance());


        //启动公司和银行两个线程
        companyThread.start();
        bankThread.start();

        //用join等待两个线程的完成
        try {
            companyThread.join();
            bankThread.join();
            // 输出最后的余额
            System.out.printf("Account : Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
