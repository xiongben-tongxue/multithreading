package com.concurrency.task;

/**
 * @Classname TicketOffice1
 * @Description 售票窗口类，售票窗口1类，这个窗口既可以售退影厅1也可以售退影厅2的票
 * @Date 2019-02-11 17:05
 * @Created by wangdong
 */
public class TicketOffice1 implements Runnable{
    /**
     * 电影院对象
     */
    private Cinema cinema;

    /**
     * 构造一个函数
     * @param cinema
     */
    public TicketOffice1(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets1(3);
        cinema.sellTickets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTickets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.returnTickets2(3);
    }
}
