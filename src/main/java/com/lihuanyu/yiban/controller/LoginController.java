package com.lihuanyu.yiban.controller;


import com.lihuanyu.yiban.util.MCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by skyADMIN on 16/2/5.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/",method = RequestMethod.GET,params = "verify_request")
    public String testString(String verify_request) throws Exception {
        MCrypt mCrypt = new MCrypt();
        String output = new String(mCrypt.decrypt(verify_request));
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testOauth() {
            String client_id = "07f11a3f2773e24e";
            String redirect_uri = "http://f.yiban.cn/iapp28401";
            return new ModelAndView("redirect:https://openapi.yiban.cn/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_uri);
    }
}
