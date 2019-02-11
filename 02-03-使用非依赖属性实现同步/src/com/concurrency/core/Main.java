package com.concurrency.core;

import com.concurrency.task.Cinema;
import com.concurrency.task.TicketOffice1;
import com.concurrency.task.TicketOffice2;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2019-02-11 19:17
 * @Created by wangdong
 */
public class Main {
    public static void main(String[] args) {
        //创建一个电影院对象
        Cinema cinema = new Cinema();

        //创建一个售票窗口1的对象，并且让其在一个线程中运行
        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1,"ticketOffice1");

        //创建一个售票窗口2的对象，并且让其在一个线程中运行
        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2,"ticketOffice2");

        //启动两个售票窗口线程
        thread1.start();
        thread2.start();

        //等待两个线程的完成
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出电影院剩余的票数
        System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());
    }
}
