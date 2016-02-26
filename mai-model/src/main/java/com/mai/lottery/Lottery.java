package com.mai.lottery;

import java.io.Serializable;

/**
 * Created by denghao on 15/12/28.
 */
public class Lottery implements Serializable {

    private static final long serialVersionUID = -8091252072038838042L;

    //砸金蛋活动ID
    public static final String objID_GOLDEGG = "1";

    //抽奖记录，中奖名单不显示
    public static final Integer lotteryStatus_0 = 0;

    //未中奖
    public static final Integer lotteryStatus_1 = 1;

    //中奖
    public static final Integer lotteryStatus_2 = 2;

    private String lotteryID;
    private String phoneNum;
    private String winner;
    private Integer level;
    private Long createTime;
    private Long winnerTime;
    private Integer orderNum;
    private String objID;
    private Integer lotteryStatus;
    private String personID;
    private Integer lotteryNum;
    private String showCreateTime;
    private String showWinnerTime;


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLotteryID() {
        return lotteryID;
    }

    public void setLotteryID(String lotteryID) {
        this.lotteryID = lotteryID;
    }

    public Integer getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(Integer lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Long getWinnerTime() {
        return winnerTime;
    }

    public void setWinnerTime(Long winnerTime) {
        this.winnerTime = winnerTime;
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getShowWinnerTime() {
        return showWinnerTime;
    }

    public void setShowWinnerTime(String showWinnerTime) {
        this.showWinnerTime = showWinnerTime;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }
}
