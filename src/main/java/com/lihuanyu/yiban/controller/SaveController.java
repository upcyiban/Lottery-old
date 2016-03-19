package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.services.CreateService;
import com.lihuanyu.yiban.services.SaveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by echao on 2016/2/24.
 */
@Controller
public class SaveController {

    @Autowired
    private SaveService saveService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveToDatabase(Model model,String lotteryname, String lotteryintro, String lotterytimebegin, String lotterytimeend, int prize1, int prize2, int prize3, int prize4, int probability1, int probability2, int probability3, int probability4){
        //变量有点多，以后要优化
       return saveService.saveDateBase(model,lotteryname,lotteryintro,lotterytimebegin,lotterytimeend,prize1,prize2,prize3,prize4,probability1,probability2,probability3,probability4);
    }



}
