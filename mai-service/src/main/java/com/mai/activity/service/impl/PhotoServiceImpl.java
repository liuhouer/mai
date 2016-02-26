package com.mai.activity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mai.activity.dao.PhotoDao;
import com.mai.activity.dao.PhotoTagDao;
import com.mai.activity.entity.Photo;
import com.mai.activity.service.PhotoService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.DateUtil;
import com.mai.framework.utils.UUIDUtil;
import com.mai.util.CommonUtil;
import com.mai.vo.PhotoVO;
import com.qiniu.util.Auth;

/**
 * Created by fengdzh on 2015/9/11.
 */
@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
	
	 @Autowired
	 private PhotoDao photoDao;
	 @Autowired
	 private PhotoTagDao phototagDao;

    /**
     * 分页查询相册信息
     *
     * @param activityID
     * @param paginationParameters
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Page<Photo> getPhotoPage(String activityID, PaginationParameters paginationParameters) throws BaseException {
        List<Photo> photoListMock = getPhotoListMock();
        Page<Photo> photoPage = new Page<Photo>();
        photoPage.setDataList(photoListMock);
        return photoPage;
    }

    /**
     * 删除活动图册
     *
     * @param personID
     * @param activityID
     * @param photoID
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Integer deletePhoto(String personID, String activityID, String photoID) throws BaseException {
        //todo 删除图册
        return 1;
    }

    /**
     * 模拟数据，临时使用
     *
     * @return
     */
    private List<String> getURLList() {
        List<String> fList = new ArrayList<String>();
        fList.add("http://img1.imgtn.bdimg.com/it/u=3628525114,3840212713&fm=21&gp=0.jpg");
        fList.add("http://pic24.nipic.com/20121017/8636708_160141680110_2.jpg");
        fList.add("http://pic2.ooopic.com/10/72/61/66b1OOOPIC7d.jpg");
        fList.add("http://pic11.nipic.com/20101208/6266500_083722007324_2.jpg");
        fList.add("http://www.sfsh.tnc.edu.tw/uploadfiles/images/20120608050339.jpg");
        fList.add("http://cs.house.sina.com.cn/img/2011/0905/U5648P648DT20110905105544.jpg");
        fList.add("http://img4.cache.netease.com/game/2013/7/29/20130729112636b4a3d.jpg");
        fList.add("http://pic18.nipic.com/20120103/8993051_170522203339_2.jpg");
        fList.add("http://pic8.nipic.com/20100730/668573_232220093693_2.jpg");
        fList.add("http://pic18.nipic.com/20111227/3109857_150221190353_2.jpg");
        fList.add("http://pic12.nipic.com/20110214/5168276_143259583376_2.jpg");
        fList.add("http://pic12.nipic.com/20110216/2898199_150353470373_2.jpg");
        fList.add("http://pic21.nipic.com/20120505/7196122_114448860000_2.jpg");
        fList.add("http://pic6.nipic.com/20100406/1771973_081343071729_2.jpg");
        return fList;
    }

    /**
     * 模拟数据
     *
     * @return
     */
    private List<Photo> getPhotoListMock() {
        List<Photo> photoList = new ArrayList<Photo>();
        List<String> urlList = getURLList();
        int i = 0;
        for (String url : urlList) {
            i++;
            Photo photo = getPhoto();
            photo.setPhotoURL(url);
            Double d = Math.random() * 10;
            int s = d.intValue() + 1;
            photo.setWidth((double)13 * s);
            d = Math.random() * 10;
            s = d.intValue() + 1;
            photo.setHeight((double)12 * s);
            photoList.add(photo);
        }
        return photoList;
    }

    private Photo getPhoto() {
        Photo photo = new Photo();
        photo.setActivityID("1");
        photo.setCreateTime(DateUtil.currentTimestampToLong());
        photo.setNotes("我的图片");
        photo.setPhotoID(UUIDUtil.getUUID());
        photo.setSocietyID("1");
        return photo;
    }

    /**
     * 批量保存图片信息
     *
     * @param photoList
     * @param activityID
     * @throws com.mai.framework.exception.BaseException
     */
    @Override
    public Integer addPhotoBatch(List<String> photoList, String activityID) throws BaseException {
        //TODO
        return 1;
    }

	@Override
	public Page<PhotoVO> findAllByPage(PaginationParameters paginationParameters) throws BaseException {
		// TODO Auto-generated method stub
		return photoDao.findAllByPage(paginationParameters);
	}

	@Override
	public int updateSt(String photoID, String status) {
		// TODO Auto-generated method stub
		return photoDao.updateST(photoID, status);
	}

	@Override
	public int updateRe(String photoID, String status) {
		// TODO Auto-generated method stub
		 /**
	     * // 状态     0:待审核                   1:删除                           2:忽略
	     */
		return photoDao.updateRE(photoID, status);
	}

    @Override
    public List<Photo> findPhotoByWH() throws BaseException {
        return photoDao.findPhotoByWH();
    }

    @Override
    public Integer updateWH(Photo photo) throws BaseException {
        return photoDao.updateWH(photo);
    }

    @Override
	public Page<Photo> findWallByPage(PaginationParameters paginationParameters,String wheresql)
			throws BaseException {
		// TODO Auto-generated method stub
		return photoDao.findWallByPage(paginationParameters, wheresql);
	}

	@Override
	public Integer addPhoto(Photo photo) throws BaseException {
		// TODO Auto-generated method stub
		return photoDao.add(photo);
	}

	@Override
	 public void delPic(String photoID,String access,String secret,String bucket) throws BaseException {
		// TODO Auto-generated method stub
		Auth auth = CommonUtil.getQiniuAuth(access, secret);
		
		//删7妞
		Photo p = photoDao.findByID(photoID);
		String purl = p.getPhotoURL();
		CommonUtil.deleteQiniuFile(auth, bucket, purl);
		
		//删标签
		phototagDao.delTagsByPhotoID(photoID);
		
		
		//删图片
		photoDao.delPic(photoID);
		
	}

    @Override
    public List<Photo> findPhotoByActivityID(String activityID) throws BaseException {
        return photoDao.findPhotoByActivityID(activityID);
    }
}
