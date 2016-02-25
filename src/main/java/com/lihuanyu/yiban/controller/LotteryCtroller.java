package com.lihuanyu.yiban.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/2/25.
 */
@Controller
public class LotteryCtroller {

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/lottery")
    public String lottery(long lotteryid){
        if(httpSession.getAttribute("userid")==null||httpSession.getAttribute("username")==null) {
            return "redirect:/";
        }
        long yibanid = (long) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        return "lottery";
    }
}
