package com.lihuanyu.yiban.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by skyADMIN on 16/2/4.
 */
public interface PrizeListDao extends CrudRepository<PrizeList,Long> {
    public Iterable<PrizeList> findByLotteryidAndPrizeNot(int lotteryid,String prize);
}