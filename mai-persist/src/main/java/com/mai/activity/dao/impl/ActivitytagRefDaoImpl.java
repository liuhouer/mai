package com.mai.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mai.activity.dao.ActivitytagRefDao;
import com.mai.activity.entity.ActivitytagRef;
import com.mai.framework.dao.impl.BaseDaoImpl;
import com.mai.framework.exception.BaseException;

/**
 * Created by denghao on 15/10/5.
 */
@Repository
public class ActivitytagRefDaoImpl extends BaseDaoImpl implements ActivitytagRefDao {
    @Override
    public List<ActivitytagRef> findActivitytagRefByActID(String actid) throws BaseException {
        return this.findByParam("Mapper.Tag_activity_ref.findActivitytagRefByActID",actid);
    }

    @Override
    public Integer insertBatch(List<ActivitytagRef> atrlist) throws BaseException {
               return this.insert("Mapper.Tag_activity_ref.insertBatch", atrlist);
    }

    @Override
    public Integer deleteActivitytagRefByActID(String actid) throws BaseException {
              return this.delete("Mapper.Tag_activity_ref.deleteActivitytagRefByActID", actid);
    }
}
