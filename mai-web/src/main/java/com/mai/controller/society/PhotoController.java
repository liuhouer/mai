package com.mai.controller.society;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.PhotoReport;
import com.mai.activity.service.PhotoService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.vo.PhotoVO;

/**
 * 社团信息
 * Created by bruce on 2015/10/19.
 */

@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    
	
    /**
     * 投诉照片列表
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/list")
    public String list(Model model,String status,HttpServletRequest request) throws BaseException {
    	String result = "/society/photo-list";
    	
    	//这里拼接分页信息---
  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        Page<PhotoVO> pageObj = this.photoService.findAllByPage(paginationParameters);
        
   	    model.addAttribute("list", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
   	
    	
    	
    	
        return result;
    }
    
    
    /**
     * @param model
     * @param photoID
     * @param status
     * @return 改变状态
     */
    @RequestMapping("/pushST")
    @ResponseBody
    public String pushST(Model model,String photoID,String status)  {
    	String result = "success";
    	try {
			if(StringUtils.isNotEmpty(photoID)&&StringUtils.isNotEmpty(status)){
				photoService.updateSt(photoID, status);
                if(!String.valueOf(PhotoReport.PHOTO_REPORT_STATUS_IGNORE).equals(status)){
                    photoService.updateRe(photoID, status);
                }
			}
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "fail";
		}
        return result;
    }
    

}
