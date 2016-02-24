package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

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

    public String saveCreatorAndLottery(String lotteryName,String lotteryIntro){
        long yibanid = (long) httpSession.getAttribute("userid");

        Creator creator = creatorDao.findByYibanid(yibanid);
        if (creator==null){
            creator = new Creator();
            creator.setYibanid(yibanid);
            creatorDao.save(creator);
        }
        LotteryList lotteryList = new LotteryList();
        lotteryList.setLotteryname(lotteryName);
        lotteryList.setLotteryintro(lotteryIntro);
        lotteryListDao.save(lotteryList);
        return "success";
    }
}
