package com.mai.controller.society;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qiniu.common.QiniuException;

import org.apache.log4j.Logger;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Photo;
import com.mai.activity.entity.PhotoTag;
import com.mai.activity.service.PhotoService;
import com.mai.activity.service.PhotoTagService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.util.ConfigUtil;
import com.mai.util.CurrentUser;
import com.mai.util.ImgCompress;
import com.mai.util.MD5Tools;
import com.mai.util.TimeUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 社团信息
 * Created by bruce on 2015/10/19.
 */

@Controller
@RequestMapping("/running")
public class RunningController {
	private static Logger logger= Logger.getLogger(RunningController.class);
    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoTagService photoTagService;
    @Autowired
    private UploadManager uploadService;

	/**
	 * 上传
	 * @param filearr
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam(required = false) MultipartFile[] filearr,HttpServletRequest request,Model model)  {
    	String result = "success";
    	
    	try {
    		
    		//文件处理
    		if(filearr!=null){
    			if(filearr.length>0){
    				for (int i = 0; i < filearr.length; i++) {
    					
    					//处理图片
    					String fileName = filearr[i].getOriginalFilename();
    					    Photo model_ = new Photo();
    					if(!StringUtils.isBlank(fileName)){
    						String postfix = fileName.substring(fileName.lastIndexOf("."));
    						Map<String,String> map = ImgCompress.getWidth(filearr[i]);
    						String width = map.get("width");
    						String height = map.get("height");
    						
    						byte[] b = ImgCompress.resizeByte(filearr[i], postfix.replaceFirst(".", ""));
    						String _filename = MD5Tools.md5(fileName+"-"+Calendar.getInstance().getTimeInMillis());
    						//String newFilename = _filename + postfix;
    						String up_token = ConfigUtil.getUpToken("qiniu.photo.bucket",null);


							try{
								Response response = uploadService.put(b, _filename,up_token );
								if(response.isOK()){
									model_.setPhotoURL(_filename);
								}
							}catch (QiniuException e) {
								Response r = e.response;
								// 请求失败时简单状态信息
								logger.error(r.toString());
								try {
									// 响应的文本信息
									logger.error(r.bodyString());
								} catch (QiniuException e1) {
									//ignore
								}
							}

    						model_.setCreateTime(new Date().getTime());
    						model_.setPersonID(CurrentUser.getCurrentPersonId());
    						model_.setPersonName(CurrentUser.getPersonName());
    						String photoid = UUIDUtil.getUUID();
    						model_.setPhotoID(photoid);
    						model_.setIsValid(1);
    						model_.setIsReported(0);
    						model_.setWidth(Double.parseDouble( width));
    						model_.setHeight(Double.parseDouble(height));
    						photoService.addPhoto(model_);
    						
    						//TODO 标签处理
    						String tag1 = request.getParameter("tag1@"+i);
    						String tag2 = request.getParameter("tag2@"+i);
    						String tag3 = request.getParameter("tag3@"+i);
    						if(StringUtils.isNotEmpty(tag1)){
    							addTag(photoid, tag1);
    						}
    						if(StringUtils.isNotEmpty(tag2)){
    							addTag(photoid, tag2);
    						}
    						if(StringUtils.isNotEmpty(tag3)){
    							addTag(photoid, tag3);
    						}
    						System.out.println(tag1+"||||||"+tag2+"||||||"+tag3);
    					}
    					
    				}
    			}
            }

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result="exception";
		}
    	
        return  result;
    }

	/**
	 * ---获取标签操作
	 * @param photoID
	 * @param model
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/taglist")
    public String taglist(String photoID,Model model) throws BaseException {
    	String rs = "/photo/tagDiv";
    	try{
	    	if(StringUtils.isNotEmpty(photoID)){
	    		List<PhotoTag> list = photoTagService.findAllByPhotoID(photoID);
	    		model.addAttribute("list",list);
	    		model.addAttribute("limit", "no");
	    		if(list!=null){
	    			if(list.size()>=100){
	    				model.addAttribute("limit", "yes");
	    			}
	    		}
	    		
	    	}
    	}catch(Exception e){
    		rs = "f";
    		e.printStackTrace();
    	}
        return rs;
    }


	/**
	 * ---新增标签操作
	 * @param newtag
	 * @param model
	 * @param photoID
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/newTag")
    public String newTag(String newtag,Model model,String photoID) throws BaseException {
    	String rs = "redirect:/running/wall.action";
    	try{
	    	if(StringUtils.isNotEmpty(newtag)&&StringUtils.isNotEmpty(photoID)){
	    		if(newtag.endsWith(",")){
	    			newtag = newtag.substring(0, newtag.length()-1);
	    		}
	    		String str[] = newtag.split(",");
	    		for (int i = 0; i < str.length; i++) {
	    			addTag(photoID, str[i]);
				}
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return rs;
    }


	/**
	 * ---删除图片操作
	 * @param photoID
	 * @param model
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/delPic")
    @ResponseBody
    public String delPic(String photoID,Model model) throws BaseException {
    	String rs = "success";
    	try{
	    	if(StringUtils.isNotEmpty(photoID)){
	
				String access = ConfigUtil.getProperty("qiniu.accessKey");
				String secret = ConfigUtil.getProperty("qiniu.secretKey");
	    		String bucket =  ConfigUtil.getProperty("qiniu.photo.bucket");
	    		photoService.delPic(photoID,access,secret,bucket);
	    	}
    	}catch(Exception e){
    		rs = "f";
    		e.printStackTrace();
    	}
        return rs;
    }

	/**
	 * ---删除标签操作
	 * @param tagID
	 * @param model
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/delTag")
    @ResponseBody
    public String delTag(String tagID,Model model) throws BaseException {
    	String rs = "success";
    	try{
	    	if(StringUtils.isNotEmpty(tagID)){
	    		photoTagService.delModel(tagID);
	    	}
    	}catch(Exception e){
    		rs = "f";
    		e.printStackTrace();
    	}
        return rs;
    }

	/**
	 * 添加标签的方法
	 * @param photoid
	 * @param tag
	 * @throws BaseException
	 */
	private void addTag(String photoid, String tag) throws BaseException {
		PhotoTag p1 = new PhotoTag();
		p1.setCreateTime(new Date().getTime());
		p1.setPersonID(CurrentUser.getCurrentPersonId());
		p1.setPersonName(CurrentUser.getPersonName());
		p1.setPhotoID(photoid);
		p1.setPointX(Math.random());
		p1.setPointY(Math.random());
		p1.setStatus(Photo.ISVALID_YES);
		p1.setTagContent64(Base64.encodeToString(tag.getBytes()));
		p1.setTagID(UUIDUtil.getUUID());
		photoTagService.addModel(p1);
	}

    /**
     * 照片墙
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/wall")
    public String wall(Model model,String status,HttpServletRequest request,String startTime_,String endTime_) throws BaseException {
    	String result = "/photo/wall-list";
    	String suff = ConfigUtil.getProperty("qiniu.qiniuDomainURL")+"/";
    	String wheresql  = " 1=1";
    	//这里拼接分页信息---
  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        if(StringUtils.isNotEmpty(startTime_)){
    		String startTime =startTime_+ " 00:00:00";
    		long st = TimeUtils.stringToMillis(startTime);
    		wheresql+=" and createTime >= "+st;
    	}
    	if(StringUtils.isNotEmpty(endTime_)){
    		String endTime = endTime_ + " 23:59:59";
    		long en = TimeUtils.stringToMillis(endTime);
    		wheresql+=" and createTime <= "+en;
    	}
        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        Page<Photo> pageObj = this.photoService.findWallByPage(paginationParameters,wheresql);
        //处理标签数----------
        List<Photo> list =  pageObj.getDataList();
        for(Photo p :list){
        	int tagsize = 0;
        	String pid = p.getPhotoID();
        	try {
				
        		tagsize =  photoTagService.findAllByPhotoID(pid).size();
        		
			} catch (Exception e) {
				// TODO: handle exception
				tagsize = 0;
			}finally{
				p.setTagsize(tagsize);
			}
        }
   	    model.addAttribute("list", list);
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
        model.addAttribute("suff", suff);
        model.addAttribute("startTime_", startTime_);
        model.addAttribute("endTime_", endTime_);
    	
    	
        return result;
    }
    
    
	
}
