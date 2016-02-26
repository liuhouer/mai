package com.mai.activity.service.impl;

import java.util.List;

import com.mai.activity.dao.SocietyRedisDao;
import com.mai.society.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.LevelRuleDao;
import com.mai.activity.dao.SocietyDao;
import com.mai.activity.entity.LevelRule;
import com.mai.activity.service.LevelRuleService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.entity.Society;

/**
 * Created by Administrator on 2015/9/7.
 */
@Service("levelruleService")
public class LevelRuleServiceImpl implements LevelRuleService {
    @Autowired
    private LevelRuleDao levelruleDao;
    @Autowired
    private SocietyDao societyDao;
	@Autowired
	private SocietyService societyService;
	@Autowired
	private SocietyRedisDao societyRedisDao;
	@Override
	public int add(LevelRule lr) {
		// TODO Auto-generated method stub
		return levelruleDao.add(lr);
	}

	@Override
	public Page<LevelRule> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return levelruleDao.findAllByPage(paginationParameters);
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return levelruleDao.getMaxLevel();
	}

	@Override
	public LevelRule findByID(String ruleid) {
		// TODO Auto-generated method stub
		return levelruleDao.findByID(ruleid);
	}

	@Override
	public int updateByID(Integer praiseNum, Integer followNum, String ruleid) {
		// TODO Auto-generated method stub
		return levelruleDao.updateByID( praiseNum,  followNum,  ruleid);
	}

	@Override
	public void updateAllStar() {
		// TODO Auto-generated method stub
		try {
			List<LevelRule> list  = levelruleDao.findAll();
			List<Society> sl = societyDao.getSocietyListByZTOver0();
			if(list!=null&&list.size()>0){
				for(LevelRule m: list){
					int zan = m.getPraiseNum();
					int fan = m.getFollowNum();
					String level = m.getLevel();
					if(sl!=null&&sl.size()>0){
						for(Society s:sl){
							int myzan = s.getPraiseNum()==null?0:s.getPraiseNum();
							int myfan = s.getFollowNum()==null?0:s.getFollowNum();
							if(myzan>=zan && myfan>=fan){
								s.setLevel(Integer.parseInt(level));
								if(societyDao.updateSociety(s)>0){
									Society society = societyService.getSocietyByID(s.getSocietyID());
									society.setLevel(Integer.parseInt(level));
									societyRedisDao.updateSociety(society);
								}
							}
						}
					}
				}
			}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
