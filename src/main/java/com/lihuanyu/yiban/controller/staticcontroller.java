package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.services.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/2/25.
 */
@Controller
public class StaticController {

    @Autowired
    private StaticService staticService;

    @RequestMapping("/about")
    public String showAbout() {
        return "about";
    }

    @RequestMapping("/contact")
    public String showContact() {
        return "contact";
    }

    @RequestMapping("/create")
    public String creat(Model model) {
        return staticService.giveInfo(model);
    }
}
