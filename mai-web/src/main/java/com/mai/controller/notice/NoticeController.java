package com.mai.controller.notice;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Activity;
import com.mai.activity.service.ActivityService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.notification.entity.Notification;
import com.mai.notification.entity.NotificationMain;
import com.mai.notification.service.NotificationService;
import com.mai.society.entity.Society;
import com.mai.society.service.SocietyService;
import com.mai.user.entity.Person;
import com.mai.user.entity.Role;
import com.mai.user.service.UserService;
import com.mai.util.CurrentUser;

/**
 * 通知管理
 * Created by bruce on 2015-10-23.
 */

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NotificationService noticeService;
    @Autowired
    private  ActivityService activityService;
    @Autowired
    private  SocietyService societyService;
    @Autowired
    private  UserService userService;

	/**
	 *
	 * @param model
	 * @param request
	 * @param objID
	 * @param objType
	 * @return
	 */
    @RequestMapping("/toAdd")
    public String toAdd(Model model,HttpServletRequest request,String objID,String objType,String toPersonID)  {
    	String result = "/notice/notice-add";
    	
    	model.addAttribute("objID", objID);
    	model.addAttribute("objType", objType);
    	
    	if(StringUtils.isNotEmpty(toPersonID)){
    		Person toPerson = userService.getPersonByID(toPersonID);
    		model.addAttribute("toPerson", toPerson);
    	}
        return result;
    }

	/**
	 * 保存草稿
	 * @param model
	 * @param objID
	 * @param objType
	 * @param J_cont
	 * @return
	 */
    @RequestMapping("/draft")
    @ResponseBody
    public String add(Model model,String objID,String objType,String J_cont)  {
    	String result = "fail";
    	
    	try {
    		
    		//保存草稿-main表
    		NotificationMain m   =  new NotificationMain();
    		m.setNotificationMainID(UUIDUtil.getUUID());
    		m.setCreateTime(new Date().getTime());
    		m.setNotificationContent(J_cont);
    		m.setObjID(objID);
    		if(StringUtils.isNotEmpty(objType)){
    			m.setObjType(Integer.parseInt(objType));
    		}
    		m.setPersonID(CurrentUser.getCurrentPersonId());
    		m.setStatus(NotificationMain.STATUS_DRAFT);
    		m.setSocietyID(CurrentUser.getSocietyID());
    		noticeService.addMain(m);
    		
        	result = "success";
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return result;
    }

	/**
	 * 发消息直接发布
	 * @param model
	 * @param objID
	 * @param objType
	 * @param J_cont
	 * @return
	 */
  @RequestMapping("/publish")
  @ResponseBody
  public String publish(Model model,String objID,String objType,String J_cont,String toPersonID)  {
  	String result = "fail";
  	
  	try {
      	
  		//发送对象是团体的时候才保存main
  		if(StringUtils.isEmpty(toPersonID)){
  			
  			//发布-main表
  			NotificationMain m   =  new NotificationMain();
  			m.setNotificationMainID(UUIDUtil.getUUID());
  			m.setCreateTime(new Date().getTime());
  			m.setNotificationContent(J_cont);
  			m.setObjID(objID);
  			if(StringUtils.isNotEmpty(objType)){
  				m.setObjType(Integer.parseInt(objType));
  			}
  			m.setPersonID(CurrentUser.getCurrentPersonId());
  			m.setStatus(NotificationMain.STATUS_PUBLISHED);
  			m.setSocietyID(CurrentUser.getSocietyID());
  			
  			noticeService.addMain(m);
  		}
		
		//保存每个通知消息
		
		noticeService.sendNotice(objID, objType, CurrentUser.getCurrentPersonId(), J_cont,toPersonID);
		
		
		
		result = "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	
      return result;
  }

	/**
	 * 从草稿发布
	 * @param model
	 * @param id
	 * @return
	 */
  @RequestMapping("/pub")
  @ResponseBody
  public String pub(Model model,String id)  {
  	String result = "fail";
  	
  	try {
      	
  		//获取main model
  		NotificationMain m = noticeService.findMainByID(id);

		
		if(m!=null){
			//更新状态
			m.setStatus(NotificationMain.STATUS_PUBLISHED);
			noticeService.updateST(m);
			
			//保存每个通知消息
			noticeService.sendNotice(m.getObjID(),String.valueOf(m.getObjType()),CurrentUser.getCurrentPersonId(),m.getNotificationContent(),null);
		}
		
		
			result = "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	
      return result;
  }
  
  /**
   * 从草稿状态-未发布改为删除
   * @param model
   * @param id
   * @return
   * @throws BaseException
   */
  @RequestMapping("/remove")
  @ResponseBody
  public String remove(Model model,String id)  {
  	String result = "fail";
  	
  		try {
      	
  		//获取main model
  		int m = noticeService.removeMainByID(id);

			if(m>0){
				
				result = "success";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	
      return result;
  }

	/**
	 * 通知管理页
	 * @param model
	 * @param J_status
	 * @param request
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/list")
    public String list(Model model,String J_status,HttpServletRequest request) throws BaseException {
    	String result = "/notice/notice-list";
    	String wheresql = " 1=1 and status != -1 ";
    	  if(CurrentUser.hasRole(Role.ROLENAME_PRESIDENT)){
    		  if(StringUtils.isNotEmpty(CurrentUser.getSocietyID())){
    			  wheresql += " and societyID = '" + CurrentUser.getSocietyID() + "'";

				  Society society = societyService.getSocietyByID(CurrentUser.getSocietyID());
				  if(society == null){
					  org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
					  if(null!=subject){
						  subject.logout();
						  CurrentUser.setCurrentUser(null);
					  }
					  return "redirect:/nav/toLogIn.action";
				  }else{
					  model.addAttribute("societyStatus",society.getStatus());
				  }
    		  }
          }
    	//这里拼接分页信息---
  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        if(StringUtils.isNotEmpty(J_status)){
        	wheresql+=" and status = "+J_status;
        }
        
        Page<NotificationMain> pageObj = this.noticeService.findAllByPage(paginationParameters, wheresql);
        
        List<NotificationMain> list =  pageObj.getDataList();
        for (NotificationMain n:list) {
			String id = n.getObjID();
			Integer type = n.getObjType();
			if(Notification.NOTIFICATIONTYPE_TYPE_ACTIVITY_NEWS.equals(type)){//活动
				Activity a = activityService.findActivityByID(id);
				n.setObjName("活动消息");
				if(a!=null){
					n.setObjName("活动："+a.getActivityTitle());
				}
			}else if(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_NEWS.equals(type)){//社团
				Society a = societyService.getSocietyByID(id);
				n.setObjName("社团消息");
				if(a!=null){
					n.setObjName("社团："+a.getSocietyName());
				}
			}
		}
        
   	    model.addAttribute("list", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
   	
        model.addAttribute("J_status", J_status);
        return result;
    }
    

    public static void main(String[] args) {
		for (int i = 0; i < 16; i++) {
			System.out.println(UUIDUtil.getUUID());
		}
	}
}
