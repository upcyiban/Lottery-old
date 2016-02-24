package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.services.CreateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by echao on 2016/2/24.
 */
@Controller
public class SaveController {

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveToDatabase(String name){
        CreateService createService = new CreateService();
        String result = createService.saveCreatorAndLottery(name,);
        if (result.equals("success")){
            return "success";
        }else {
            return "false";
        }
    }
}
