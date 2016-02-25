package com.lihuanyu.yiban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by echao on 2016/2/25.
 */
@Controller
public class StaticController {
    @RequestMapping("/about")
    public String showAbout(){
        return "about";
    }
    @RequestMapping("/contact")
    public String showContact(){
        return "contact";
    }
    @RequestMapping("/create")
    public String creat(){
        return "create";
    }
}
