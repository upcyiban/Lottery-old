package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.model.PrizeList;
import com.lihuanyu.yiban.model.PrizeListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Random;

/**
 * Created by echao on 2016/2/25.
 */
@Service
public class LotteryService {

    @Autowired
    private PrizeListDao prizeListDao;

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private HttpSession httpSession;

    public boolean canLottery(Timestamp lotterytimebegin, Timestamp lotterytimeend, int yibanid, int lotteryid) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if (date.after(lotterytimebegin) && date.before(lotterytimeend)) {
            Collection<PrizeList> result = prizeListDao.findByLotteryidAndYibanid(lotteryid, yibanid);
            return result.isEmpty();
        }
        return false;
    }

    public String lottery(int p1,int p2,int p3,int p4,int pb1,int pb2,int pb3,int pb4) {
        Random random = new Random();
        int rand = random.nextInt(1000);
        if (rand<pb1){
            if (p1 != 0){
                return "一等奖";
            }
        }else if (rand < pb2){
            if (p2 != 0){
                return "二等奖";
            }
        }else if(rand<pb3){
            if(p3!=0){
                return "三等奖";
            }
        }else if(rand<pb4){
            if(p4!=0){
                return "四等奖";
            }
        }
        return "未中奖";
    }//化简代码

    public void saveLottery(int yibanid,int lotteryid,String yibanname,String prize) {
        try {
            PrizeList prizeList = new PrizeList();
            prizeList.setPrize(prize);
            prizeList.setYibanname(yibanname);
            prizeList.setYibanid(yibanid);
            prizeList.setLotteryid(lotteryid);
            prizeListDao.save(prizeList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void dealLottery(int lotteryid,String prizeresult){
        if (prizeresult.equals("未中奖")){
            return;
        }
        LotteryList lotteryList = lotteryListDao.findById(lotteryid);
        switch (prizeresult) {
            case "一等奖":
                int p1 = lotteryList.getPrize1() - 1;
                lotteryList.setPrize1(p1);
                break;
            case "二等奖":
                int p2 = lotteryList.getPrize2() - 1;
                lotteryList.setPrize2(p2);
                break;
            case "三等奖":
                int p3 = lotteryList.getPrize3() - 1;
                lotteryList.setPrize3(p3);
                break;
            case "四等奖":
                int p4 = lotteryList.getPrize4() - 1;
                lotteryList.setPrize4(p4);
                break;
        }
        lotteryListDao.save(lotteryList);
    }

    public String showLottery(long lotteryid, Model model){
        if (httpSession.getAttribute("userid") != null) {
            LotteryList lotteryList = lotteryListDao.findById(lotteryid);
            model.addAttribute("lottery", lotteryList);
            Iterable<PrizeList> prizeList = prizeListDao.findByLotteryidAndPrizeNot((int) lotteryid, "未中奖");
            model.addAttribute("newLineChar", '\n');
            model.addAttribute("prizeList", prizeList);
            model.addAttribute("time1",lotteryList.getLotterytimebegin());
            model.addAttribute("time2",lotteryList.getLotterytimeend());
            return "lottery";
        }else {
            httpSession.setAttribute("lotteryid", lotteryid);
            return "redirect:/";
        }
    }


    public String dealLotteryResult(long id,Model model){
        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        LotteryList lotteryList = lotteryListDao.findById(id);
        if (canLottery(lotteryList.getLotterytimebegin(),lotteryList.getLotterytimeend(),yibanid, (int) id)){
            //判断是否符合抽奖条件
            String result = lottery(lotteryList.getPrize1(),lotteryList.getPrize2(),lotteryList.getPrize3(),lotteryList.getPrize4(),lotteryList.getProbability1(),lotteryList.getProbability2(),lotteryList.getProbability3(),lotteryList.getProbability4());
            //如果符合，进行抽奖

            saveLottery(yibanid, (int) id,yibanname,result);
            dealLottery((int) id,result);
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

