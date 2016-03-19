package com.lihuanyu.yiban.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/3/19.
 */
@Service
public class StaticService {

    @Autowired
    private HttpSession httpSession;

    public String giveInfo(Model model){
        String username = (String) httpSession.getAttribute("username");
        int userid = (int) httpSession.getAttribute("userid");
        model.addAttribute("username",username);
        model.addAttribute("userid",userid);
        return "create";
    }
}
