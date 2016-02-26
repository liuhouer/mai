package com.mai.controller.user;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.service.FeedbackService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.user.entity.Feedback;
import com.mai.util.TimeUtils;

/**
 * 投诉建议
 * Created by bruce on 2015/10/22.
 */

@Controller
@RequestMapping("/feed")
public class FeedbackController {
	
    @Autowired
    private FeedbackService feedService;
	
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
    	String result = "/user/feed-list";
    	
    	
    	//这里拼接分页信息---
  	  	int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        Page<Feedback> pageObj = this.feedService.findAllByPage(paginationParameters);
        List<Feedback> ll =  pageObj.getDataList();
        for (Feedback f:ll) {
			f.setShowTime(TimeUtils.longToString(f.getCreateTime()));
		}
        
   	    model.addAttribute("list", ll);
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
   	
    	
        return result;
    }
    

}
