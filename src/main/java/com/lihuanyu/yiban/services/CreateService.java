package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by echao on 2016/2/24.
 */
public class CreateService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private CreatorDao creatorDao;

    /**
     * 将以下字段存入数据库
     * @param lotteryname
     * @param lotteryintro
     * @param lotterytimebegin
     * @param lotterytimeend
     * @param prize1
     * @param prize2
     * @param prize3
     * @param prize4
     * @param createtime
     * @return
     */
    public String saveCreatorAndLottery(String lotteryname, String lotteryintro, Timestamp lotterytimebegin, Timestamp lotterytimeend, int prize1, int prize2, int prize3, int prize4, Date createtime){
        long yibanid = (long) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        Creator creator = creatorDao.findByYibanid(yibanid);
        if (creator==null){
            creator = new Creator();
            creator.setYibanid(yibanid);
            creator.setYibanname(yibanname);
            creatorDao.save(creator);
        }
        LotteryList lotteryList = new LotteryList();
        lotteryList.setCreatorid(creator.getId());
        lotteryList.setLotteryname(lotteryname);
        lotteryList.setLotteryintro(lotteryintro);
        lotteryList.setLotterytimebegin(lotterytimebegin);
        lotteryList.setLotterytimeend(lotterytimeend);
        lotteryList.setPrize1(prize1);
        lotteryList.setPrize2(prize2);
        lotteryList.setPrize3(prize3);
        lotteryList.setPrzie4(prize4);
        lotteryList.setCreatetime(new Date(System.currentTimeMillis()));
        lotteryList.setIspass(0);
        lotteryListDao.save(lotteryList);
        return "success";
    }
}
