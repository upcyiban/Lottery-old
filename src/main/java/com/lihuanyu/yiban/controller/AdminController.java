package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by skyADMIN on 16/2/26.
 */
@Controller
public class AdminController {

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private CreatorDao creatorDao;

    @RequestMapping("/admin")
    public String showLogin(){
        return "loginadmin";
    }

    /**
     * 管理员登陆,账号密码暂时直接写入,部署后密码从config类获取
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        if (username.equals(DevConfig.adminUsername)&&password.equals(DevConfig.adminPassword)) {
            Iterable<LotteryList> lotteryList = lotteryListDao.findAll();
            model.addAttribute("adminLists",lotteryList);
            return "admin";
        }else {
            String result = "出错了!";
            String word = "账号或密码有误!";
            model.addAttribute("title",result);
            model.addAttribute("word",word);
            return "message";
        }
    }

    @RequestMapping("/detail")
    public String showDetail(int lotteryid,Model model){
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

    /**
     * 管理操作
     * @param id
     * @param pass
     * @param model
     * @return
     */
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String check(long id,String pass,Model model){
        //System.out.println(pass);
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
