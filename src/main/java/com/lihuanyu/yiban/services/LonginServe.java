package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.config.CustomConfig;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by echao on 2016/3/19.
 */
@Service
public class LonginServe {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LotteryListDao lotteryListDao;

    public String dealVerify(String verify_request, Model model) throws Exception {
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        userLoginService.saveSession(output);
        if (httpSession.getAttribute("lotteryid") != null) {
            long lotteryid = (long) httpSession.getAttribute("lotteryid");
            return "redirect:/lottery?lotteryid=" + lotteryid;
        }
        return "redirect:/";
    }

    public String redirectControl(Model model) {
        if (httpSession.getAttribute("userid") != null) {
            Iterable<LotteryList> lotteryList = lotteryListDao.findByIspass(1);
            model.addAttribute("lotteryLists", lotteryList);
            String username = (String) httpSession.getAttribute("username");
            model.addAttribute("username", username);
            return "index";
        }
        return "redirect:https://openapi.yiban.cn/oauth/authorize?client_id=" + CustomConfig.client_id + "&redirect_uri=" + CustomConfig.redirect_uri;
    }
}


