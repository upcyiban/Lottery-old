package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/1.
 */
@Service
public class LoginService {

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private HttpSession httpSession;

    public String loginAdmin(String username, String password, Model model){
        if (username.equals(DevConfig.adminUsername)&&password.equals(DevConfig.adminPassword)) {
            Iterable<LotteryList> lotteryList = lotteryListDao.findAll();
            model.addAttribute("adminLists",lotteryList);
            return "admin";
        }else {
            String result = "出错了!";
            String word = "账号或密码有误!";
            model.addAttribute("title",result);
            model.addAttribute("result",word);
            return "message";
        }
    }
}
