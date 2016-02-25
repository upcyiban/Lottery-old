package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.model.PrizeList;
import com.lihuanyu.yiban.model.PrizeListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/lottery")
    public String lottery(long lotteryid, Model model){
        if(httpSession.getAttribute("userid")==null||httpSession.getAttribute("username")==null) {
            return "redirect:/";
        }
        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        LotteryList lotteryList = lotteryListDao.findById(lotteryid);
        model.addAttribute("lottery",lotteryList);
        Iterable<PrizeList> prizeList = prizeListDao.findByLotteryidAndPrizeNot((int) lotteryid,"未中奖");
        model.addAttribute("lotterylist",prizeList);
        return "lottery";
    }
}
