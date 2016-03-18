package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.services.AdminService;
import com.lihuanyu.yiban.services.LoginService;
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
    private LoginService loginService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin")
    public String showLogin(){
        return "loginadmin";
    }

    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        return loginService.loginAdmin(username,password,model);
    }

    @RequestMapping("/detail")
    public String showDetail(int lotteryid,Model model){
        return adminService.showAdminTable(lotteryid,model);
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String check(long id,String pass,Model model){
        return adminService.changeState(id,pass,model);
    }
}
