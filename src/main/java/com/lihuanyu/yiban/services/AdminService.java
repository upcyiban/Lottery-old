package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by skyADMIN on 16/3/18.
 */
@Service
public class AdminService {

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private CreatorDao creatorDao;

    public String showAdminTable(int lotteryid, Model model){
        LotteryList lotteryList = lotteryListDao.findById(lotteryid);
        model.addAttribute("lotteryList",lotteryList);
        Creator creator = creatorDao.findOne(lotteryList.getCreatorid());
        model.addAttribute("username",creator.getYibanname());
        model.addAttribute("userid",creator.getYibanid());
        String state = null;
        if (lotteryList.getIspass() == 0){
            state = "未审核";
        }else if (lotteryList.getIspass() == 1){
            state = "已通过";
        }else if (lotteryList.getIspass() == 2){
            state = "未通过";
        }
        model.addAttribute("state",state);
        return "detail";
    }

    public String changeState(long id,String pass,Model model){
        if (pass.equals("同意")){
            LotteryList lotteryList = lotteryListDao.findById(id);
            lotteryList.setIspass(1);
            lotteryListDao.save(lotteryList);
        }else if (pass.equals("否决")){
            LotteryList lotteryList = lotteryListDao.findById(id);
            lotteryList.setIspass(2);
            lotteryListDao.save(lotteryList);
        }else if (pass.equals("删除")){
            lotteryListDao.delete(id);
        }
        Iterable<LotteryList> lotteryList = lotteryListDao.findAll();
        model.addAttribute("adminLists",lotteryList);
        return "admin";
    }
}
