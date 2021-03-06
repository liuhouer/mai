package com.mai.activity.dao;

import com.mai.activity.entity.PhotoTag;
import com.mai.framework.exception.BaseException;

import java.util.List;

/**
 * Created by denghao on 15/12/3.
 */
public interface PhotoTagDao {
    /**
     * 批量插入
     * @param photoTagList
     * @throws BaseException
     */
    public Integer insertBatch(List<PhotoTag> photoTagList) throws BaseException;
    
    /**
     * 单个插入
     * @param photoTagList
     * @throws BaseException
     */
    public Integer addModel(PhotoTag model) throws BaseException;
    
    /**
     * 根据图片id获取图片已有标签
     * @param photoTagList
     * @throws BaseException
     */
    public List<PhotoTag> findAllByPhotoID(String photoID) throws BaseException;
    
    /**
     * 根据主键删除
     * @param photoTagList
     * @throws BaseException
     */
    public Integer delModel(String tagID ) throws BaseException;
    
    /**
     * 根据照片删除标签
     * @param photoTagList
     * @throws BaseException
     */
    public Integer delTagsByPhotoID(String photoID ) throws BaseException;
}
