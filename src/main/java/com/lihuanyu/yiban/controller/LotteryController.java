package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.model.PrizeList;
import com.lihuanyu.yiban.model.PrizeListDao;
import com.lihuanyu.yiban.services.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by echao on 2016/2/25.
 */
@Controller
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @RequestMapping("/lottery")
    public String lottery(long lotteryid, Model model) {
        return lotteryService.showLottery(lotteryid,model);
    }

    @RequestMapping("/lotteryresult")
    public String  lotteryResult(long id,Model model) {
       return lotteryService.dealLotteryResult(id,model);
    }
}
