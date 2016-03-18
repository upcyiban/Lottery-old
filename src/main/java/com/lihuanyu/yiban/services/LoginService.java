package com.lihuanyu.yiban.services;

import com.lihuanyu.yiban.config.DevConfig;
import com.lihuanyu.yiban.model.LotteryList;
import com.lihuanyu.yiban.model.LotteryListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by skyADMIN on 16/3/1.
 */
@Service
public class LoginService {

    @Autowired
    private LotteryListDao lotteryListDao;

    @Autowired
    private HttpSession httpSession;


}
