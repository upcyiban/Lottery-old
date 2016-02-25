package com.lihuanyu.yiban.controller;


import com.google.gson.Gson;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
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
    private HttpSession httpSession;

    @Autowired
    private LotteryListDao lotteryListDao;

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "verify_request")
    public String testString(String verify_request, Model model) throws Exception {
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        Gson gson = new Gson();
        SessionUser sessionUser = new SessionUser();
        sessionUser = gson.fromJson(output, SessionUser.class);
        httpSession.setAttribute("visit_time", sessionUser.visit_time);
        httpSession.setAttribute("userid", sessionUser.visit_user.userid);
        httpSession.setAttribute("username", sessionUser.visit_user.username);
        httpSession.setAttribute("usernick", sessionUser.visit_user.usernick);
        httpSession.setAttribute("usersex", sessionUser.visit_user.usersex);
        httpSession.setAttribute("access_token", sessionUser.visit_oauth.access_token);
        httpSession.setAttribute("token_expires", sessionUser.visit_oauth.token_expires);
        Iterable<LotteryList> lotteryList = lotteryListDao.findAll();
        model.addAttribute("lotteryLists", lotteryList);
        model.addAttribute("username",sessionUser.visit_user.username);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testOauth(Model model) {
        if (httpSession.getAttribute("userid")!=null){
            Iterable<LotteryList> lotteryList = lotteryListDao.findAll();
            model.addAttribute("lotteryLists", lotteryList);
            String username = (String) httpSession.getAttribute("username");
            model.addAttribute("username",username);
            return "index";
        }
        String client_id = "07f11a3f2773e24e";
        String redirect_uri = "http://f.yiban.cn/iapp28401";
        return "redirect:https://openapi.yiban.cn/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_uri;
    }
}
