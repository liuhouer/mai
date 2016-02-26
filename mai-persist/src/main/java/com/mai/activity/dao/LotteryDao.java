package com.mai.activity.dao;

import com.mai.framework.exception.BaseException;
import com.mai.lottery.Lottery;

import java.util.List;

/**
 * Created by denghao on 15/12/28.
 */
public interface LotteryDao {

    /**
     * 获取中奖名单
     * @return
     * @throws BaseException
     */
    public List<Lottery> getLotteryList() throws BaseException;

    /**
     * 获取中奖人根据手机号和摇奖活动ID
     * @param lottery
     * @return
     * @throws BaseException
     */
    public Lottery findLotteryByPhoneAndObjID(Lottery lottery) throws BaseException;

    int transIncreaseLotteryNum(Lottery lottery) throws BaseException;



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
     *
     * @param lottery
     * @return
     * @throws BaseException
     */
    public int updateLotteryStatus(Lottery lottery) throws BaseException;

}
