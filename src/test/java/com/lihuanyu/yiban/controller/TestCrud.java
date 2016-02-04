package com.lihuanyu.yiban.controller;

import com.lihuanyu.yiban.model.Creator;
import com.lihuanyu.yiban.model.CreatorDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by skyADMIN on 16/2/4.
 */
public class TestCrud {
    @Autowired
    CreatorDao creatorDao;
    public void insertCreator(){
        Creator creator = new Creator();
        creator.setYibanid(1234);
        creator.setYibanname("test");
        creatorDao.save(creator);
    }
}
