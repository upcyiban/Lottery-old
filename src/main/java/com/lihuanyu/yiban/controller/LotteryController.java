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
        LotteryList lotteryList = lotteryListDao.findById(lotteryid);
        model.addAttribute("lottery", lotteryList);
        Iterable<PrizeList> prizeList = prizeListDao.findByLotteryidAndPrizeNot((int) lotteryid, "未中奖");
        model.addAttribute("prizeList", prizeList);
        model.addAttribute("time1",lotteryList.getLotterytimebegin());
        model.addAttribute("time2",lotteryList.getLotterytimeend());
        return "lottery";
    }

    @RequestMapping("/lotteryresult")
    public String  lotteryResult(long id,Model model) {
        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        LotteryList lotteryList = lotteryListDao.findById(id);
        if (lotteryService.canLottery(lotteryList.getLotterytimebegin(),lotteryList.getLotterytimeend(),yibanid, (int) id)){
            //判断是否符合抽奖条件
            String result = lotteryService.lottery(lotteryList.getPrize1(),lotteryList.getPrize2(),lotteryList.getPrize3(),lotteryList.getPrize4(),lotteryList.getProbability1(),lotteryList.getProbability2(),lotteryList.getProbability3(),lotteryList.getProbability4());
            //如果符合，进行抽奖

            lotteryService.saveLottery(yibanid, (int) id,yibanname,result);
            lotteryService.dealLottery((int) id,result);
            if (result.equals("未中奖")){
                model.addAttribute("result","可惜没中奖");
                model.addAttribute("word","还是欢迎您关注参加我们的活动~");
            }else {
                model.addAttribute("result", "中奖啦！");
                model.addAttribute("word", "恭喜您获得" + result);
            }
            model.addAttribute("lotteryid", id);
            return "lotteryresult";
        }else {
            model.addAttribute("title","出错了!");
            model.addAttribute("result","不具备抽奖资格(时间不对或已抽过奖)");
            return "message";
        }
    }
}
