package com.lihuanyu.yiban.services;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by echao on 2016/2/25.
 */
public class TestLotteryService {

    public void test(){
        LotteryService lotteryService = new LotteryService();
        for (int i = 0;i<10;i++) {
            System.out.println(lotteryService.lottery());
        }
    }
}
