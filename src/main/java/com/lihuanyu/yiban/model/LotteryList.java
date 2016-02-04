package com.lihuanyu.yiban.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * Created by skyADMIN on 16/2/4.
 */
@Entity
@Table(name = "lotterylist")
public class LotteryList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long creatorid;
    private String lotteryname;
    private String lotteryintro;
    private Timestamp lotterytimebegin;
    private Timestamp lotterytimeend;
    private int prize1;
    private int prize2;
    private int prize3;
    private int przie4;
    private Date createtime;
    private int ispass; //是否审核通过.未审核为0,通过为1,不通过为2

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(long creatorid) {
        this.creatorid = creatorid;
    }

    public String getLotteryname() {
        return lotteryname;
    }

    public void setLotteryname(String lotteryname) {
        this.lotteryname = lotteryname;
    }

    public String getLotteryintro() {
        return lotteryintro;
    }

    public void setLotteryintro(String lotteryintro) {
        this.lotteryintro = lotteryintro;
    }

    public Timestamp getLotterytimebegin() {
        return lotterytimebegin;
    }

    public void setLotterytimebegin(Timestamp lotterytimebegin) {
        this.lotterytimebegin = lotterytimebegin;
    }

    public Timestamp getLotterytimeend() {
        return lotterytimeend;
    }

    public void setLotterytimeend(Timestamp lotterytimeend) {
        this.lotterytimeend = lotterytimeend;
    }

    public int getPrize1() {
        return prize1;
    }

    public void setPrize1(int prize1) {
        this.prize1 = prize1;
    }

    public int getPrize2() {
        return prize2;
    }

    public void setPrize2(int prize2) {
        this.prize2 = prize2;
    }

    public int getPrize3() {
        return prize3;
    }

    public void setPrize3(int prize3) {
        this.prize3 = prize3;
    }

    public int getPrzie4() {
        return przie4;
    }

    public void setPrzie4(int przie4) {
        this.przie4 = przie4;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getIspass() {
        return ispass;
    }

    public void setIspass(int ispass) {
        this.ispass = ispass;
    }
}
