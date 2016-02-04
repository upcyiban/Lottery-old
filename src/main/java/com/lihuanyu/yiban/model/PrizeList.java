package com.lihuanyu.yiban.model;

import javax.persistence.*;

/**
 * Created by skyADMIN on 16/2/4.
 */
@Entity
@Table(name = "prizelist")
public class PrizeList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int lotteryid;
    private int yibanid;
    private String yibanname;
    private String prize;
}
