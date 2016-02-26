package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.services.CreateService;
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
    private CreateService createService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveToDatabase(Model model,String lotteryname, String lotteryintro, String lotterytimebegin, String lotterytimeend, int prize1, int prize2, int prize3, int prize4, int probability1, int probability2, int probability3, int probability4){
        if (isEmpty(lotteryname)||isEmpty(lotteryintro)||isEmpty(lotterytimebegin)||isEmpty(lotterytimeend)||isEmpty(prize1)||isEmpty(prize2)||isEmpty(prize3)||isEmpty(prize4)||isEmpty(probability1)||isEmpty(probability2)||isEmpty(probability3)||isEmpty(probability4)){
            String result = "出错了!";
            String word = "请检查是否有漏填项";
            model.addAttribute("result",result);
            model.addAttribute("word",word);
            return "createresult";
        }

        Timestamp timebegin = tranFromDatetimeLocal(lotterytimebegin);
        Timestamp timeend = tranFromDatetimeLocal(lotterytimeend);

        int yibanid = (int) httpSession.getAttribute("userid");
        String yibanname = (String) httpSession.getAttribute("username");

        String result = createService.saveCreatorAndLottery(yibanid,yibanname,lotteryname,lotteryintro,timebegin,timeend,prize1,prize2,prize3,prize4,probability1,probability2,probability3,probability4);

        if (result.equals("success")){
            String pageresult = "成功了!";
            String word = "创建成功,等待管理员审核,急用可联系石大易班发展中心成员";
            model.addAttribute("result",pageresult);
            model.addAttribute("word",word);
            return "createresult";
        }else {
            String pageresult = "失败了";
            String word = "未知原因失败,再次尝试也不行请反馈到石大易班发展中心,等技术人员检查.";
            model.addAttribute("result",pageresult);
            model.addAttribute("word",word);
            return "createresult";
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

    /**
     * 转换从HTML表单提交的Datetime-local到JAVA的Timestamp
     * @param datetimeLocal
     * @return Timestamp
     */
    public Timestamp tranFromDatetimeLocal(String datetimeLocal){
        if (StringUtils.countMatches(datetimeLocal, ":") == 1) {
            datetimeLocal += ":00";
        }
        return Timestamp.valueOf(datetimeLocal.replace("T"," "));
    }
}
