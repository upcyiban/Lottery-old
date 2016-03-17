package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.model.PrizeList;
import com.lihuanyu.yiban.model.PrizeListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

