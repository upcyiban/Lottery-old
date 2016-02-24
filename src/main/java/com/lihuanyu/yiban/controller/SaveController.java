package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.services.CreateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by echao on 2016/2/24.
 */
@Controller
public class SaveController {

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveToDatabase(String lotteryname, String lotteryintro, Timestamp lotterytimebegin, Timestamp lotterytimeend, int prize1, int prize2, int prize3, int prize4, Date createtime){
        if (isEmpty(lotteryname)||isEmpty(lotteryintro)||isEmpty(lotterytimebegin)||isEmpty(lotterytimeend)||isEmpty(prize1)||isEmpty(prize2)||isEmpty(prize3)||isEmpty(prize4)||isEmpty(createtime)){
            return "false";
        }
        CreateService createService = new CreateService();
        String result = createService.saveCreatorAndLottery(name,);
        if (result.equals("success")){
            return "success";
        }else {
            return "false";
        }
    }

    /**
     * 判断表单提交是否为空
     * @param obj
     * @return true or false
     */
    public boolean isEmpty(Object obj){
        if (obj!=null){
            return false;
        }else{
            return true;
        }
    }
}
