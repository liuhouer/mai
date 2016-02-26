package com.mai.activity.dao;

import java.util.List;

import com.mai.activity.entity.Photo;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.vo.PhotoVO;

/**
 * Created by bruce on 2015/9/29.
 */
public interface PhotoDao {

	public int add(Photo lr);

	public Page<PhotoVO> findAllByPage(PaginationParameters paginationParameters) throws BaseException;
	
	public Page<Photo> findWallByPage(PaginationParameters paginationParameters,String wheresql) throws BaseException;

	public int getMaxLevel();

	public Photo findByID(String ruleid);

	public int updateByID(Integer praiseNum, Integer followNum, String ruleid);

	public List<PhotoVO> findAll() throws BaseException;

	/**
	 * 更新投诉表
	 * @param photoID
	 * @param status
	 * @return
	 */
	public int updateST(String photoID, String status);

	/**
	 * 更新photo主表
	 * @param photoID
	 * @param status
	 * @return
	 */
	public int updateRE(String photoID, String status);

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

	public Integer delPic(String photoID) throws BaseException;

	public List<Photo> findPhotoByActivityID(String activityID) throws BaseException;


}
