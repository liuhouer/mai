package com.mai.controller.society;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.app.entity.Advertisement;
import com.mai.activity.service.AdService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.util.ConfigUtil;
import com.mai.util.MD5Tools;
import com.mai.util.TimeUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 社团信息
 * Created by bruce on 2015/10/19.
 */

@Controller
@RequestMapping("/ad")
public class ADController {
    @Autowired
    private AdService adService;
    @Autowired
    private UploadManager uploadService;

    /**
     *
     * @param files
     * @param ad
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestParam(required = false) MultipartFile files,Advertisement  ad,HttpServletRequest request,Model model)  {
    	String result = "/ad/toAdd.action";
    	
    	try {
    		
    		//文件处理
    		if(files!=null){
                String fileName = files.getOriginalFilename();
                if(!StringUtils.isBlank(fileName)){
                    String postfix = fileName.substring(fileName.lastIndexOf("."));
                    String _filename = MD5Tools.md5(fileName)+"-"+Calendar.getInstance().getTimeInMillis();
                    String newFilename = _filename + postfix;
                    Response response = uploadService.put(files.getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket",null));
                    if(response.isOK()){
                         ad.setImageURL(ConfigUtil.getProperty("qiniu.qiniuDomainURL")+"/"+newFilename+ConfigUtil.getProperty("qiniu.ThumbnailParam.200k"));
                    }
                }
            }
    		//时间处理
    		ad.setStartTime(TimeUtils.stringToMillis(ad.getShowStartTime()));
    		ad.setEndTime(TimeUtils.stringToMillis(ad.getShowEndTime()));
    		ad.setAdvertisementID(UUIDUtil.getUUID());
    		ad.setIsEnable(1);
    		adService.add(ad);

        	result = "/ad/list.action";
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return "redirect:"+result;
    }

    /**
     *
     * @param files
     * @param ad
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) MultipartFile files,Advertisement  ad,String id,HttpServletRequest request,Model model)  {
    	String result = "/ad/toEdit.action?id="+id;
    	
    	try {
    		
    		if(StringUtils.isNotEmpty(id)){
	    		//文件处理
	    		if(files!=null){
	                String fileName = files.getOriginalFilename();
	                if(!StringUtils.isBlank(fileName)){
	                    String postfix = fileName.substring(fileName.lastIndexOf("."));
	                    String _filename = MD5Tools.md5(fileName)+"-"+Calendar.getInstance().getTimeInMillis();
	                    String newFilename = _filename + postfix;
	                    Response response = uploadService.put(files.getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket",null));
	                    if(response.isOK()){
	                         ad.setImageURL(ConfigUtil.getProperty("qiniu.qiniuDomainURL")+"/"+newFilename+ConfigUtil.getProperty("qiniu.ThumbnailParam.200k"));
	                    }
	                }
	            }
	    		//时间处理
	    		ad.setStartTime(TimeUtils.stringToMillis(ad.getShowStartTime()));
	    		ad.setEndTime(TimeUtils.stringToMillis(ad.getShowEndTime()));
	    		ad.setAdvertisementID(id);
	    		
	    		adService.update(ad);
	
	        	result = "/ad/list.action";
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return "redirect:"+result;
    }
    
    /**
     * 跳转新增页面
     * @param model
     * @return
     * @throws BaseException
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model)  {
    	String result = "/ad/ad-add";
    	
    	
        return result;
    }
    
    /**
     * 跳转编辑页面
     * @param model
     * @return
     * @throws BaseException
     */
    @RequestMapping("/toEdit")
    public String toEdit(Model model,String id)  {
    	String result = "/ad/ad-edit";
    	
    	Advertisement model_ =  adService.findByID(id);
    	model_.setShowStartTime(TimeUtils.longToString(model_.getStartTime()));
    	model_.setShowEndTime(TimeUtils.longToString(model_.getEndTime()));
    	model.addAttribute("model", model_);
        return result;
    }
    
    /**
     * 删除
     * @param model
     * @return
     * @throws BaseException
     */
    @RequestMapping("/removes")
    @ResponseBody
    public String remove(Model model,String id)  {
    	String result = "fail";
    	try {
			
    		int a = adService.removeByID(id);
    		if(a==1){
    			result = "success";
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
        return result;
    }
    
    /**
     * 广告管理页
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/list")
    public String list(Model model,String status,HttpServletRequest request) throws BaseException {
    	String result = "/ad/ad-list";
    	
    	int max = adService.getMaxLevel();
    	model.addAttribute("max", max);
    	
    	//这里拼接分页信息---
  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        Page<Advertisement> pageObj = this.adService.findAllByPage(paginationParameters);
        
   	    model.addAttribute("list", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
   	
    	
    	
    	
        return result;
    }
    

}
