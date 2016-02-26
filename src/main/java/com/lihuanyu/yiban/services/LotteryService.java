package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.model.PrizeList;
import com.lihuanyu.yiban.model.PrizeListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

/**
 * Created by echao on 2016/2/25.
 */
@Service
public class LotteryService {

    @Autowired
    private PrizeListDao prizeListDao;


    public int lottery() {
        Random random = new Random();
        int a = random.nextInt(1000);
        return a;
    }

    public String Savelotteryer(int yibanid, int lotteryid, String yibanname, int probability1, int probability2, int probability3, int probability4) {
        if (yibanname == null) {
            return "false";
        }
        PrizeList prizeList = new PrizeList();
        prizeList.setLotteryid(lotteryid);
        prizeList.setYibanid(yibanid);
        prizeList.setYibanname(yibanname);//向PrizeList中插入id、name
        int random = lottery();
        if (random >= 0 && random < probability1) {
            prizeList.setPrize("一等奖");
        } else if (random >= probability1 && random < probability1 + probability2) {
            prizeList.setPrize("二等奖");
        } else if (random >= probability1 + probability2 && random < probability1 + probability2 + probability3) {
            prizeList.setPrize("三等奖");
        } else if (random >= probability1 + probability2 + probability3 && random < probability1 + probability2 + probability3 + probability4) {
            prizeList.setPrize("四等奖");
        } else {
            prizeList.setPrize("未中奖");
        }
        prizeListDao.save(prizeList);

        return "secesses";
    }
}

