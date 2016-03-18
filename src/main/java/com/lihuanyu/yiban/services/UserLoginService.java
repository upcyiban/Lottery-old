package com.lihuanyu.yiban.services;

import com.google.gson.Gson;
import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import com.lihuanyu.yiban.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/1.
 */
@Service
public class UserLoginService {

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private HttpSession httpSession;

    /*
    向session存入数据
     */
    public String saveSession(String s) {
        Gson gson = new Gson();
        try {
            SessionUser sessionUser = gson.fromJson(s, SessionUser.class);
            httpSession.setAttribute("visit_time", sessionUser.visit_time);
            httpSession.setAttribute("userid", sessionUser.visit_user.userid);
            httpSession.setAttribute("username", sessionUser.visit_user.username);
            httpSession.setAttribute("usernick", sessionUser.visit_user.usernick);
            httpSession.setAttribute("usersex", sessionUser.visit_user.usersex);
            httpSession.setAttribute("access_token", sessionUser.visit_oauth.access_token);
            httpSession.setAttribute("token_expires", sessionUser.visit_oauth.token_expires);
            return "success";
        }catch (Exception ex){
            ex.printStackTrace();
            return "error";
        }
    }
}
