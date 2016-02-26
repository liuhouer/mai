package com.mai.lottery.service.impl;

import com.mai.activity.dao.LotteryDao;
import com.mai.framework.exception.BaseException;
import com.mai.lottery.Lottery;
import com.mai.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by denghao on 15/12/28.
 */
@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private LotteryDao lotteryDao;

    @Override
    public List<Lottery> getLotteryList() throws BaseException {
        return lotteryDao.getLotteryList();
    }

    @Override
    public Lottery findLotteryByPhoneAndObjID(String phoneNum, String objID) throws BaseException {
        Lottery lottery = new Lottery();
        lottery.setPhoneNum(phoneNum);
        lottery.setObjID(objID);
        return this.lotteryDao.findLotteryByPhoneAndObjID(lottery);
    }

    @Override
    public int insertOrUpdateLotteryNum(Lottery lottery) throws BaseException {
        return this.lotteryDao.insertOrUpdateLotteryNum(lottery);
    }

    @Override
    public int updateLotteryStatus(String phoneNum,String objID,Integer lotteryStatus) throws BaseException {
        Lottery lottery = new Lottery();
        lottery.setPhoneNum(phoneNum);
        lottery.setObjID(objID);
        lottery.setLotteryStatus(lotteryStatus);
        return this.lotteryDao.updateLotteryStatus(lottery);
    }

}
