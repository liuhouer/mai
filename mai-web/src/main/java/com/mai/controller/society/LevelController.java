package com.mai.controller.society;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.LevelRule;
import com.mai.activity.service.LevelRuleService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;

/**
 * 社团信息
 * Created by bruce on 2015/10/19.
 */

@Controller
@RequestMapping("/level")
public class LevelController {
    @Autowired
    private LevelRuleService levelService;
	
	  /**
     * @param model
     * @param J_name
     * @param J_phone
     * @param J_add_role
     * @return
     * @throws BaseException
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(Model model,String level,Integer praiseNum,Integer followNum,String ruleid)  {
    	String result = "fail";
    	
    	try {
    		if(StringUtils.isEmpty(ruleid)){//新增
	    		LevelRule lr = new LevelRule();
	        	lr.setLevel(level);
	        	lr.setPraiseNum(praiseNum);
	        	lr.setFollowNum(followNum);
	        	lr.setCreateTime(new Date().getTime());
	        	lr.setRuleID(UUIDUtil.getUUID());
	        	
	        	levelService.add(lr);
    		}else{							//编辑
    			LevelRule lr = levelService.findByID(ruleid);
    			if(lr!=null){
    				levelService.updateByID(praiseNum,followNum,ruleid);
    			}
    		}
        	result = "success";
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return result;
    }
    
    /**
     * 级别管理页
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/list")
    public String list(Model model,String status,HttpServletRequest request) throws BaseException {
    	String result = "/running/op-list";
    	
    	int max = levelService.getMaxLevel();
    	model.addAttribute("max", max);
    	
    	//这里拼接分页信息---
  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        Page<LevelRule> pageObj = this.levelService.findAllByPage(paginationParameters);
        
   	    model.addAttribute("list", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
   	
    	
    	
    	
        return result;
    }
    

}
