package com.lihuanyu.yiban.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by skyADMIN on 16/2/4.
 */
public interface LotteryListDao extends CrudRepository<LotteryList,Long> {
    public LotteryList findById(long id);
    public LotteryList findByYibanid(int yibanid);
    public Iterable<LotteryList> findByIspass(int ispass);
}
