package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by echao on 2016/2/24.
 */
@Service
public class CreateService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private CreatorDao creatorDao;

    /**
     * 将以下字段存入数据库
     *
     * @param lotteryname
     * @param lotteryintro
     * @param lotterytimebegin
     * @param lotterytimeend
     * @param prize1
     * @param prize2
     * @param prize3
     * @param prize4
     * @return
     */
    public String saveCreatorAndLottery(int yibanid, String yibanname, String lotteryname, String lotteryintro, Timestamp lotterytimebegin, Timestamp lotterytimeend, int prize1, int prize2, int prize3, int prize4,int probability1,int probability2,int probability3,int probability4) {
        try {
            Collection<Creator> creators = creatorDao.findByYibanid(yibanid);
            //System.out.println(creators.isEmpty());
            Creator creator = null;
            if (creators.isEmpty()){
                creator = new Creator();
                creator.setYibanid(yibanid);
                creator.setYibanname(yibanname);
                creatorDao.save(creator);
            }else {
                creator = creators.iterator().next();
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
            lotteryList.setPrize4(prize4);
            lotteryList.setCreatetime(new Date(System.currentTimeMillis()));
            lotteryList.setProbability1(probability1);
            lotteryList.setProbability2(probability2);
            lotteryList.setProbability3(probability3);
            lotteryList.setProbability4(probability4);
            lotteryList.setIspass(0);
            lotteryListDao.save(lotteryList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "success";
    }
}
