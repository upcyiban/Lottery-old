package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.services.CreateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by echao on 2016/2/24.
 */
@RestController
public class SaveController {

    @Autowired
    private CreateService createService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveToDatabase(String lotteryname, String lotteryintro, String lotterytimebegin, String lotterytimeend, int prize1, int prize2, int prize3, int prize4){
        if (isEmpty(lotteryname)||isEmpty(lotteryintro)||isEmpty(lotterytimebegin)||isEmpty(lotterytimeend)||isEmpty(prize1)||isEmpty(prize2)||isEmpty(prize3)||isEmpty(prize4)){
            return "false";
        }
        //System.out.println(lotterytimebegin);
        if (StringUtils.countMatches(lotterytimebegin, ":") == 1) {
            lotterytimebegin += ":00";
        }
        if (StringUtils.countMatches(lotterytimeend, ":") == 1) {
            lotterytimeend += ":00";
        }
        Timestamp timebegin = Timestamp.valueOf(lotterytimebegin.replace("T"," "));
        Timestamp timeend = Timestamp.valueOf(lotterytimeend.replace("T"," "));
        //System.out.println(timebegin);
        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");
        String result = createService.saveCreatorAndLottery(yibanid,yibanname,lotteryname,lotteryintro,timebegin,timeend,prize1,prize2,prize3,prize4);
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
