package com.lihuanyu.yiban.service;

import com.lihuanyu.yiban.ChoujiangApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by skyADMIN on 16/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChoujiangApplication.class)
@WebAppConfiguration
public class LotteryServiceTest {

    String test = "测试抽奖！\n" +
            "该抽奖仅为测试用途，也作为抽奖活动样板。后面是乱打的：大贵雇一个我古月哥很反感idhjg阿萨德刚速度快V看阿萨德刚按施工队打出一个的春节后该的价格。\n" +
            "活动地点：东环301\n" +
            "时间：2016.6.31 中午11点半参与活动并领取奖品\n" +
            "奖品详情：\n" +
            "一等奖：啥也没有\n" +
            "二等奖：还是啥也没有\n" +
            "三等奖：还用说么？\n" +
            "四等奖：呵呵哒\n" +
            "中奖概率：\n" +
            "一等奖千分之10。二等奖千分之100。三等奖千分之500。四等奖千分之800。";

    @Test
    public void testShowLotteryDetail() {
        System.out.println(test.replaceAll("\\n","&lt;br /&gt;"));
    }
}
