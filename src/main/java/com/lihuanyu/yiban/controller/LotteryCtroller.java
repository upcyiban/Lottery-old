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
import java.util.Random;

/**
 * Created by echao on 2016/2/25.
 */
@Controller
public class LotteryCtroller {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private PrizeListDao prizeListDao;

    @Autowired
    private LotteryService lotteryService;

    @RequestMapping("/lottery")
    public String lottery(long lotteryid, Model model) {
        if (httpSession.getAttribute("userid") == null || httpSession.getAttribute("username") == null) {
            return "redirect:/";
        }
        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        LotteryList lotteryList = lotteryListDao.findById(lotteryid);
        model.addAttribute("lottery", lotteryList);
        Iterable<PrizeList> prizeList = prizeListDao.findByLotteryidAndPrizeNot((int) lotteryid, "未中奖");
        model.addAttribute("prizeList", prizeList);
        return "lottery";
    }

    @RequestMapping("/lotteryresult")
    public String  lotteryResult(long id,Model model) {
        int yibanid = (int) httpSession.getAttribute("userid");
        LotteryList lotteryList = lotteryListDao.findById(id);
        if (lotteryService.canLottery(lotteryList.getLotterytimebegin(),lotteryList.getLotterytimeend(),yibanid,id)){
            //next
            String result = lotteryService.lottery();
            lotteryService.saveLottery();

        }else {
            model.addAttribute("title","出错了!");
            model.addAttribute("word","不具备抽奖资格(时间不对或已抽过奖)");
            return "message";
        }
    }
}
