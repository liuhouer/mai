package com.mai.lottery.service;

import com.mai.framework.exception.BaseException;
import com.mai.lottery.Lottery;

import java.util.List;

/**
 * Created by denghao on 15/12/28.
 */
public interface LotteryService {
    /**
     * 获取中奖名单
     * @return
     * @throws BaseException
     */
    public List<Lottery> getLotteryList() throws BaseException;

    /**
     * 获取中奖人根据手机号和摇奖活动ID
     * @param phoneNum
     * @param objID
     * @return
     * @throws BaseException
     */
    public Lottery findLotteryByPhoneAndObjID(String phoneNum,String objID) throws BaseException;

    /**
     * 新增乐透
     *
     * @param lottery
     * @return
     * @throws BaseException
     */
    public int insertOrUpdateLotteryNum(Lottery lottery) throws BaseException;

    /**
     * 修改中奖状态
     * @param phoneNum
     * @param objID
     * @param lotteryStatus
     * @return
     * @throws BaseException
     */
    public int updateLotteryStatus(String phoneNum,String objID,Integer lotteryStatus) throws BaseException;
}
