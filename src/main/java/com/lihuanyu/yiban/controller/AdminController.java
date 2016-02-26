package com.lihuanyu.yiban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by skyADMIN on 16/2/26.
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String showLogin(){
        return "loginadmin";
    }

    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        if (username.equals("admin")&&username.equals("sdyb2016")) {
            return "admin";
        }else {
            String result = "出错了!";
            String word = "账号或密码有误!";
            model.addAttribute("title",result);
            model.addAttribute("word",word);
            return "message";
        }
    }

    @RequestMapping("/")
}
