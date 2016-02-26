package com.mai.activity.service;

import java.util.List;

import com.mai.activity.entity.Photo;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.vo.PhotoVO;

/**
 * Created by fengdzh on 2015/9/11.
 */
public interface PhotoService {
    /**
     * 分页查询相册信息
     *
     * @param activityID
     * @param paginationParameters
     * @return
     * @throws BaseException
     */
    public Page<Photo> getPhotoPage(String activityID, PaginationParameters paginationParameters) throws BaseException;

    /**
     * 删除活动图册
     *
     * @param personID
     * @param activityID
     * @param photoID
     * @return
     * @throws BaseException
     */
    public Integer deletePhoto(String personID, String activityID, String photoID) throws BaseException;

    /**
     * 批量保存图片信息
     *
     * @param photoList
     * @param activityID
     * @throws BaseException
     */
    public Integer addPhotoBatch(List<String> photoList, String activityID) throws BaseException;
    
    /**
     * 保存图片
     *
     * @param photo
     * @throws BaseException
     */
    public Integer addPhoto(Photo photo) throws BaseException;

	public Page<PhotoVO> findAllByPage(PaginationParameters paginationParameters) throws BaseException;
	

	public Page<Photo> findWallByPage(PaginationParameters paginationParameters,String wheresql) throws BaseException;

	/**
	 * 更新投诉表
	 * @param photoID
	 * @param status
	 * @return
	 */
	public int updateSt(String photoID, String status);

	/**
	 * 更新photo主表
	 * @param photoID
	 * @param status
	 * @return
	 */
	public int updateRe(String photoID, String status);

    /**
     * 获取宽或高为空或为0的图片
     * @return
     * @throws BaseException
     */
    public List<Photo> findPhotoByWH() throws BaseException;

    /**
     * 更新宽和高
     * @return
     * @throws BaseException
     */
    public Integer updateWH(Photo photo) throws BaseException;
    
    /**
     * 删除标签+图片+7牛的图片
     * @return
     * @throws BaseException
     */
    public void delPic(String photoID,String access,String secret,String bucket) throws BaseException;

    public List<Photo> findPhotoByActivityID(String activityID) throws BaseException;
}
