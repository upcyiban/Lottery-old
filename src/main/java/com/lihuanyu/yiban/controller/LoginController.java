package com.lihuanyu.yiban.controller;


import com.google.gson.Gson;
import com.lihuanyu.yiban.session.SessionUser;
import com.lihuanyu.yiban.util.MCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * Created by skyADMIN on 16/2/5.
 */
@RestController
public class LoginController {

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/",method = RequestMethod.GET,params = "verify_request")
    public String testString(String verify_request) throws Exception {
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        Gson gson = new Gson();
        SessionUser sessionUser = new SessionUser();
        sessionUser = gson.fromJson(output,SessionUser.class);
        httpSession.setAttribute("visit_time",sessionUser.visit_time);
        httpSession.setAttribute("userid",sessionUser.visit_user.userid);
        httpSession.setAttribute("username",sessionUser.visit_user.username);
        httpSession.setAttribute("usernick",sessionUser.visit_user.usernick);
        httpSession.setAttribute("usersex",sessionUser.visit_user.usersex);
        httpSession.setAttribute("access_token",sessionUser.visit_oauth.access_token);
        httpSession.setAttribute("token_expires",sessionUser.visit_oauth.token_expires);
        return output;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testOauth() {
            String client_id = "07f11a3f2773e24e";
            String redirect_uri = "http://f.yiban.cn/iapp28401";
            return new ModelAndView("redirect:https://openapi.yiban.cn/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_uri);
    }
}
