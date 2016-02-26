package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mai.activity.dao.ActivityDetailDao;
import com.mai.activity.entity.ActivityDetail;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/9/30.
 */
@Repository
public class ActivityDetailDaoImpl extends BaseDaoImpl implements ActivityDetailDao {

    @Override
    public ActivityDetail insertActivityDetail(ActivityDetail activityDetail) throws BaseException {
        this.insert("Mapper.ActivityDetail.insert", activityDetail);
        return activityDetail;
    }

    @Override
    public ActivityDetail findActivityDetailByID(String id) throws BaseException {
        ActivityDetail actd = null;
        List<ActivityDetail> actds = this.findByParam("Mapper.ActivityDetail.findActivityDetailByID",id);
        if(actds!=null && actds.size() > 0){
            actd = actds.get(0);
        }
        return actd;
    }

    @Override
    public Integer updateActivityDetailByID(ActivityDetail activityDetail) throws BaseException {
        return this.update("Mapper.ActivityDetail.updateActivityDetailByID",activityDetail);
    }


}
