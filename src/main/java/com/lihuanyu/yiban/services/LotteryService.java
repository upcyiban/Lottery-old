package com.lihuanyu.yiban.services;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by echao on 2016/2/25.
 */
@Service
public class LotteryService {

    public int lottery(){
        Random random = new Random();
        int a = random.nextInt(1000);
        //System.out.println(a);
        return a;
    }
}
