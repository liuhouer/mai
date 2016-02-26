package com.mai.activity.dao.impl;

import com.mai.activity.dao.LotteryDao;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;
import com.mai.lottery.Lottery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by denghao on 15/12/28.
 */

@Component
public class LotteryDaoImpl extends BaseDaoImpl implements LotteryDao{
    @Override
    public List<Lottery> getLotteryList() throws BaseException {
        return this.findByParam("Mapper.Lottery.getLotteryList",null);
    }

    @Override
    public Lottery findLotteryByPhoneAndObjID(Lottery lottery) throws BaseException {
        List<Lottery> lotteryList = this.findByParam("Mapper.Lottery.findLotteryByPhoneAndObjID",lottery);
        if(lotteryList!=null && lotteryList.size() > 0){
            return lotteryList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int transIncreaseLotteryNum(Lottery lottery) throws BaseException {
        return this.update("Mapper.Lottery.transIncreaseLotteryNum",lottery);
    }

    @Override
    public int insertOrUpdateLotteryNum(Lottery lottery) throws BaseException {
        return this.insert("Mapper.Lottery.insertOrUpdateLotteryNum",lottery);
    }

    @Override
    public int updateLotteryStatus(Lottery lottery) throws BaseException {
        return this.update("Mapper.Lottery.updateLotteryStatus",lottery);
    }
}
