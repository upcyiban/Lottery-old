package com.lihuanyu.yiban.controller;


import com.google.gson.Gson;
import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.services.LonginServe;
import com.lihuanyu.yiban.services.UserLoginService;
import com.lihuanyu.yiban.session.SessionUser;
import com.lihuanyu.yiban.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


/**
 * Created by skyADMIN on 16/2/5.
 */
@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private LonginServe longinServe;

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "verify_request")
    public String oauthProcess(String verify_request, Model model) throws Exception {
        return longinServe.dealVerify(verify_request, model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testOauth(Model model) {
        return longinServe.redirectControl(model);
    }
}