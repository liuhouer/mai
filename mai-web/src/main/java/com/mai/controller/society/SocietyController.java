package com.mai.controller.society;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mai.activity.dao.PersonRedisDao;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.ActivityMember;
import com.mai.activity.entity.Category;
import com.mai.activity.entity.Location;
import com.mai.activity.service.ActivityService;
import com.mai.activity.service.CategoryService;
import com.mai.activity.service.LocationService;
import com.mai.framework.utils.GeohashUtil;
import com.mai.user.entity.Person;
import com.mai.user.service.PersonService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Log;
import com.mai.activity.service.LogService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.notification.entity.Notification;
import com.mai.notification.service.NotificationService;
import com.mai.society.entity.Society;
import com.mai.society.entity.SocietyMember;
import com.mai.society.entity.SocietytagRef;
import com.mai.society.service.SocietyService;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.entity.UserroleRef;
import com.mai.user.service.UserService;
import com.mai.util.ConfigUtil;
import com.mai.util.CurrentUser;
import com.mai.util.MD5Tools;
import com.mai.util.TimeUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 社团信息
 * Created by fengdzh on 2015/9/16.
 */

@Controller
@RequestMapping("/society")
public class SocietyController {
    @Autowired
    private SocietyService societyService;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;
	@Autowired
	private UploadManager uploadService;
	@Autowired
	private NotificationService noticeService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private PersonService personService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LocationService locationService;




    /**
     * 社团详情页
     *
     * @param model
     * @param societyID
     * @return
     * @throws BaseException
     */
    @RequestMapping("/societyDeatil")
    public String societyDeatil(Model model, String societyID) throws BaseException {
        System.out.println("societyID:" + UUIDUtil.getUUID());
		String gps_map_static_str = "北京",stagRefsstr=",";
        Society society = null;
		if(CurrentUser.hasRole(Role.ROLENAME_PRESIDENT)){
			society = societyService.findSocietyByAdminIDAndValid(CurrentUser.getCurrentPersonId());
		}else{
			society = societyService.getSocietyByID(societyID);
		}
		if(society == null){
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			if(null!=subject){
				subject.logout();
				CurrentUser.setCurrentUser(null);
			}
			return "redirect:/nav/toLogIn.action";
		}
		if(society != null && !StringUtils.isBlank(society.getGpsLongitude()) && !StringUtils.isBlank(society.getGpsLatitude())){
			gps_map_static_str = society.getGpsLongitude() + "," + society.getGpsLatitude();
		}
		List<SocietytagRef> societytagRefs = this.societyService.findSocietytagRefListBySID(society.getSocietyID());
		for(SocietytagRef str : societytagRefs){
			stagRefsstr += str.getSocietyTagID()+ ",";
		}
		model.addAttribute("stagRefsstr",stagRefsstr);
		model.addAttribute("gps_map_static_str",gps_map_static_str);
        model.addAttribute("society", society);
        
        //类别
        List<Category> clist = categoryService.getCategoryList();
        
        //区域
         List<Location> lolist = locationService.getLocationList();
        model.addAttribute("clist", clist);
        model.addAttribute("lolist", lolist);
        return "/society/societyDetail";
    }


	/**
	 * 社团信息保存，后台用
	 * @param Filedatas
	 * @param society
	 * @param request
	 * @return
	 * @throws BaseException
	 */

	@RequestMapping("/updateSocietyDeatil")
	public String updateSocietyDeatil(@RequestParam(value="Filedatas",required = false) MultipartFile[] Filedatas,Society society,HttpServletRequest request) throws BaseException, IOException {
		Calendar ca = Calendar.getInstance();
		society.setCreateTime(ca.getTimeInMillis());
		Person person = userService.getPersonByID(society.getAdminID());
		society.setShareURL(ConfigUtil.getProperty("society.share.url")+society.getSocietyID());
		society.setShareDESC(society.getSlogan());
		if(person!=null){
			society.setAdminName(person.getName());
		}
		if(Filedatas!=null && Filedatas.length == 2){
			String fileName = "",postfix = "",_filename="",newFilename="";
			if(!Filedatas[0].isEmpty()){
				fileName = Filedatas[0].getOriginalFilename();
				if(!StringUtils.isBlank(fileName)){
//					postfix = fileName.substring(fileName.lastIndexOf("."));
//					_filename = society.getSocietyID();
					newFilename = UUIDUtil.getUUID();
					Response response = uploadService.put(Filedatas[0].getBytes(), newFilename,ConfigUtil.getUpToken("qiniu.bucket",null));
					if(response.isOK()){
						society.setCoverURL(ConfigUtil.getProperty("qiniu.qiniuDomainURL")+"/"+newFilename+ConfigUtil.getThumbnailParam(Filedatas[0].getSize()));
					}
				}
			}

			if(!Filedatas[1].isEmpty()){
				fileName = Filedatas[1].getOriginalFilename();
				if(!StringUtils.isBlank(fileName)){
//					postfix = fileName.substring(fileName.lastIndexOf("."));
//					_filename = society.getSocietyID();
					newFilename = UUIDUtil.getUUID();
					Response response = uploadService.put(Filedatas[1].getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket",null));
					if(response.isOK()){
						society.setSocietyLOGO(ConfigUtil.getProperty("qiniu.qiniuDomainURL") + "/" + newFilename+ConfigUtil.getthumLogo());
					}
				}
			}

		}
		List<SocietytagRef> societytagRefs = new ArrayList<SocietytagRef>();
			if(request.getParameterValues("tagids")!=null){
				SocietytagRef societytagRef = null;
				for(String tagid : request.getParameterValues("tagids")){
					societytagRef = new SocietytagRef();
					societytagRef.setSocietytagrefID(UUIDUtil.getUUID());
					societytagRef.setSocietyTagID(tagid);
					societytagRef.setSocietyID(society.getSocietyID());
					societytagRefs.add(societytagRef);
				}
			}
		if(!StringUtils.isBlank(society.getGpsLatitude()) && !StringUtils.isBlank(society.getGpsLongitude())){
			society.setGeohash(GeohashUtil.encode(Double.parseDouble(society.getGpsLatitude()), Double.parseDouble(society.getGpsLongitude())));
		}
		if(society.getLevel() == null || society.getLevel() == 0) {
			society.setLevel(1);
		}
		societyService.updateSocietyInfo(society, societytagRefs);
		return "redirect:/society/societyDeatil.action";
	}
    
    /**
     * 社团详情--浏览
     *
     * @param model
     * @param id
     * @return
     * @throws BaseException
     */
    @RequestMapping("/view")
    public String view(Model model, String id) throws BaseException {
        Society society = societyService.getSocietyByID(id);
        model.addAttribute("society", society);
        return "/society/socView";
    }
    
    
    /**
     * 社团管理页
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/list")
    public String list(Model model,String status,HttpServletRequest request,String keyword) throws BaseException {
    	String result = "/society/list";
    	if(StringUtils.isEmpty(status)){
    		status =  "0";
    	}
    	//审核通过+已发布+已下架显示一个页面信息
    	if("1".equals(status)||"3".equals(status)||"2".equals(status)){
    		result = "/society/list2";
    	}else if("-1".equals(status)){
    		result = "/society/list3";
    	}
    	model.addAttribute("status", status);
//        List<Society> list = societyService.getSocietyListByZT(status);
        
	      //这里拼接分页信息				
	  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
    	
        
        String wheresql = " 1=1 ";
        if(StringUtils.isNotEmpty(status)){
        	wheresql +=" and s.status = '"+status+"' ";
        }
        
        if(StringUtils.isNotEmpty(keyword)){
        	wheresql +=" and (s.societyName like '%"+keyword+"%'  or s.adminName like '%"+keyword+"%' or phoneNum like '%"+keyword+"%' )";
        	
        	model.addAttribute("keyword",keyword);
        }
    	
    	 Page<Society> pageObj = this.societyService.getSocietyPageByZT(wheresql, paginationParameters);
    	 model.addAttribute("list", pageObj.getDataList());
         model.addAttribute("page", pageObj.getCurrentPageNumber());
         model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
         model.addAttribute("totalsize", pageObj.getTotalSize());
         model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
    	
//        model.addAttribute("list", list);
        return result;
    }
    
    /**
     * 社团管理页---通过
     *
     * @param tid
     * @return
     * @throws BaseException
     */
    @RequestMapping("/pass")
    @ResponseBody
    public String pass(String tid) throws BaseException {
    	String rs = "fail";
    	try{
	    	if(StringUtils.isNotEmpty(tid)){
	    		//更新model
	    		Society model  = societyService.getSocietyByID(tid);
	    		if(model!=null){
	    			if(model.getLevel() == null ||model.getLevel() == 0  ){
	    				model.setLevel(1);
	    			}
					model.setShareURL(ConfigUtil.getProperty("society.share.url")+model.getSocietyID());
					model.setShareDESC(model.getSlogan());
	    			if(!societyService.OnlyOneEffectST(model.getAdminID())){//社长《-》社团【唯一】
	    			if(StringUtils.isNotEmpty(model.getSchoolID())&&StringUtils.isNotEmpty(model.getSchoolName())){ 	
	    			//社团信息不完善，不允许新建
	    				
	    			//更新状态
	    			model.setStatus(Society.STATUS_NORMAL);
	    			societyService.updateSocietyStatusByID(model,CurrentUser.getPhoneNum());
	    			
	    			//给社长身份加一条记录

	    				User u = userService.getUserByPid(model.getAdminID());
	    				if(u!=null){
	    					if(StringUtils.isNotEmpty(u.getPhoneNum())){
	    					//添加关系
			        		UserroleRef ur = new UserroleRef();
			        		ur.setUserID(u.getUserID());
			        		ur.setRoleID(Role.ROLEID_SHEZHANG);
			        		ur.setUserRoleRefID(UUIDUtil.getUUID());
			        		userService.insertUserRole(ur);

			        		//给用户添加默认密码让他可以登陆后台

			        			u.setPassword(MD5Tools.md5(u.getPhoneNum()));//密码是手机号
			        			userService.updateUserPWD(u);


								Person person = personService.getPersonByID(model.getAdminID());
								if(person!=null){
									person.setIsPresident(Person.ISPRESIDENT_YES);
									personService.updatePerson(person);
								}

			        		//给社长发消息
			        			String cont = "恭喜您新建的"+model.getSocietyName()+"社团审核通过了，用户名"+u.getPhoneNum()+"，密码"+u.getPhoneNum()+"，您可以登录mp.maitongxue.com管理你的社团信息、发布最新的活动，快去发展你的队伍吧！";
			        			//恭喜您新建的XXX社团审核通过了，用户名xxx，密码xxx，您可以登录www.maitongxue.com管理你的社团信息、发布最新的活动，快去发展你的队伍吧！
			        			Notification n = new Notification();
								n.setCreateTime(new Date().getTime());
								if(StringUtils.isNotEmpty(CurrentUser.getCurrentPersonId())){
									n.setFromPersonID(CurrentUser.getCurrentPersonId());
								}
								n.setFromPersonName("麦同学");//麦同学
								n.setHeaderURL(ConfigUtil.getSystemDefaultIcon());//麦同学LOGO
								n.setIsDeal(0);
								n.setIsRead(0);
								n.setNeedDeal(0);
								n.setNotificationContent(cont);
								n.setNotificationID(UUIDUtil.getUUID());
								n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_CHECKED);
								n.setObjID(tid);
								n.setToPersonID(u.getPersonID());
								n.setTitle("麦同学");
								noticeService.add(n);
								noticeService.push2Single(n);

			        		}
	    				}
	    			
	    			
	    				//添加操作历史
	    	    		Log log = new Log();
	    	    		log.setLogID(UUIDUtil.getUUID());
	    	    		log.setCreateTime(new Date().getTime());
	    	    		log.setLogAuthor(CurrentUser.getPhoneNum());
	    	    		log.setLogDesc(model.getSocietyName()+"审核通过");
	    	    		log.setLogtype(Log.TYPE_SOCIETY);
	    	    		log.setActID(model.getSocietyID());
	    	    		logService.add(log);
	    			
	    	    		rs = "success";
	    			}else{//社团信息不完善，不允许新建
	    				rs = "less";
	    			}
	    			
	    			}else{
	    				rs="repeat";
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
     * 社团管理页---不通过//下架
     *
     * @param tid
     * @param con
     * @return
     * @throws BaseException
     */
    @RequestMapping("/dispass")
    @ResponseBody
    public String dispass(String tid,String con,String status,HttpServletRequest request) throws BaseException {
    	String rs = "success";
    	try{
	    	if(StringUtils.isNotEmpty(tid)){
	    		//更新model
	    		Society model  = societyService.getSocietyByID(tid);
	    		if(model!=null){
	    			if(model.getLevel() == null ||model.getLevel() == 0 ){
	    				model.setLevel(1);
	    			}
					model.setShareURL(ConfigUtil.getProperty("society.share.url")+model.getSocietyID());
					model.setShareDESC(model.getSlogan());
	    			model.setStatus(Society.STATUS_CLOSE);//审核不通过
	    			if(String.valueOf(Society.STATUS_OFFLINE).equals(status)){//下架
	    				model.setStatus(Society.STATUS_OFFLINE);
	    			}
	    			societyService.updateSocietyStatusByID(model, CurrentUser.getPhoneNum());

					Person person = personService.getPersonByID(model.getAdminID());
					if(person!=null){
						person.setIsPresident(Person.ISPRESIDENT_NO);
						personService.updatePerson(person);
					}

	    			String cont ="";
	    			String title = "";
	    			User u =  userService.getUserByPid(model.getAdminID());
	    			//判断此社长的身份，删除权限userroleref
	    			if(String.valueOf(Society.STATUS_CLOSE).equals(status)){
	    				String camefrom =  request.getParameter("camefrom");
	    				if("2".equals(camefrom)){//只有审核通过-变成审核不通过删除权限
		    				if(u!=null){
		    					UserroleRef ur = userService.findRefByUidAndRoleID(u.getUserID(),Role.ROLEID_SHEZHANG);
		    					if(ur!=null){
	    						userService.deleteByID(ur.getUserRoleRefID());
		    					}
	    					
		    				}
	    				}
	    				//不通过给社长发消息
	    				//“桑心，您筹划的XXX社团审核未通过，原因是XXXXXX，小强不死，改进后再去申报吧！”con
	        			 cont = "您筹划的"+model.getSocietyName()+"社团审核未通过，原因是"+con;
	        			 title = "社团审核不通过";
	    			}else{
	    				//下架给社长发消息
	    				//你的xxx活动由于如下原因被下架：xxxx，您可以修改后再次申请活动发布。
	    				//你的xxx社团由于如下原因被下架：xxxx，您可以修改后再次申请社团发布。
	        			 cont = "你的"+model.getSocietyName()+"社团由于如下原因被下架："+con+"，您可以修改后再次申请社团发布。";
	        			 title = "社团下架";
	    			}
	    			Notification n = new Notification();
	    			n.setCreateTime(new Date().getTime());
	    			if(StringUtils.isNotEmpty(CurrentUser.getCurrentPersonId())){
	    				n.setFromPersonID(CurrentUser.getCurrentPersonId());
	    			}
	    			n.setFromPersonName("麦同学");//麦同学
					n.setHeaderURL(ConfigUtil.getSystemDefaultIcon());//麦同学LOGO
	    			n.setIsDeal(0);
	    			n.setIsRead(0);
	    			n.setNeedDeal(0);
	    			n.setNotificationContent(cont);
	    			n.setNotificationID(UUIDUtil.getUUID());
	    			n.setNotificationType(Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_CHECKED);
	    			n.setObjID(tid);
	    			n.setToPersonID(u.getPersonID());
	    			n.setTitle("麦同学");
	    			if(noticeService.add(n)>0){
	    				noticeService.push2Single(n);
	    			}
	    			
	    			
	    			
	    		}
	    		//添加操作历史
	    		Log log = new Log();
	    		log.setLogID(UUIDUtil.getUUID());
	    		log.setCreateTime(new Date().getTime());
	    		log.setLogAuthor(CurrentUser.getPhoneNum());
	    		log.setLogDesc(con);
	    		log.setLogtype(Log.TYPE_SOCIETY_DISPASS);
	    		if(String.valueOf(Society.STATUS_OFFLINE).equals(status)){//下架
    				log.setLogtype(Log.TYPE_SOCIETY_OFFLINE);
    			}
	    		log.setActID(model.getSocietyID());
	    		logService.add(log);
	    	}
    	}catch(Exception e){
    		rs = "f";
    		e.printStackTrace();
    	}
        return rs;
    }
    
    
    /**
     * 社团管理页---获取历史操作
     *
     * @param model
     * @param tid
     * @return
     * @throws BaseException
     */
    @RequestMapping("/loglist")
    public String loglist(String tid,Model model,String path) throws BaseException {
    	String rs = "/society/logDiv";
    	try{
	    	if(StringUtils.isNotEmpty(tid)){
	    		if(StringUtils.isNotEmpty(path)){
	    			if("2".equals(path)){//查询审核不通过和下架历史
	    				String type = Log.TYPE_SOCIETY_DISPASS+","+Log.TYPE_SOCIETY_OFFLINE;
	    				List<Log> list = logService.getLogList(type, tid);
	    	    		for (Log log :list) {
	    					
	    	    			String date = TimeUtils.longToDateStrng(log.getCreateTime());
	    	    			log.setLogAuthor(date);
	    				}
	    	    		model.addAttribute("list", list);
	    			}else if("1".equals(path)){
	    				String type = String.valueOf(Log.TYPE_SOCIETY_OFFLINE);
	    				List<Log> list = logService.getLogList(type, tid);
	    	    		for (Log log :list) {
	    					
	    	    			String date = TimeUtils.longToDateStrng(log.getCreateTime());
	    	    			log.setLogAuthor(date);
	    				}
	    	    		model.addAttribute("list", list);
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
	 * 获取社团列表，页面录入临时方法
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/getSocietyList")
	@ResponseBody
	public List<Society> getSocietyList() throws BaseException {
		return societyService.getSocietyListByZT(String.valueOf(Society.STATUS_NORMAL));
	}

	@RequestMapping(value = "/SocietyMemberList")
	public String SocietyMemberList(Model model,HttpServletRequest request) throws BaseException {
		Map<String,String> params = new HashMap<String,String>();
		String societyID = "";
		if(CurrentUser.hasRole(Role.ROLENAME_PRESIDENT)){
			societyID = CurrentUser.getSocietyID();
		}else{
			return "redirect:/society/societyDeatil.action";
		}
		params.put("societyID", societyID);
		model.addAttribute("sid", societyID);

		if(!StringUtils.isBlank(request.getParameter("sptype"))){
			params.put("memberStatus",request.getParameter("sptype"));
			model.addAttribute("sptype",request.getParameter("sptype"));
		}
		if(!StringUtils.isBlank(request.getParameter("spsid"))){
			params.put("schoolID", request.getParameter("spsid"));
			model.addAttribute("spsid",request.getParameter("spsid"));
		}
		if(!StringUtils.isBlank(request.getParameter("spgenderid"))){
			params.put("gender", request.getParameter("spgenderid"));
			model.addAttribute("spgenderid", request.getParameter("spgenderid"));
			model.addAttribute("spgendername",request.getParameter("spgendername"));
		}
		int pageNumber = 1;
		if(!StringUtils.isBlank(request.getParameter("page"))){
			pageNumber = Integer.parseInt(request.getParameter("page"));
		}

		PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
		Page<SocietyMember> pageObj = this.societyService.findSocietymemberListBySID(params, paginationParameters);

		model.addAttribute("smlist", pageObj.getDataList());
		model.addAttribute("page", pageObj.getCurrentPageNumber());
		model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
		model.addAttribute("totalsize", pageObj.getTotalSize());
		model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
		return "/society/societyPersonList";
	}

	@RequestMapping(value = "/SocietyMemberStatus")
	@ResponseBody
	public String SocietyMemberStatus(Model model,HttpServletRequest request) throws BaseException {
		Map<String,String> map = new HashMap<String, String>();
		map.put("id",request.getParameter("smemberid"));
		map.put("status", request.getParameter("newstatus"));
		map.put("sid", request.getParameter("sid"));
		String personid = request.getParameter("personid");//申请者的id
		
		
		Society model1  = societyService.getSocietyByID(request.getParameter("sid"));
		String cont1 = "恭喜您如愿加入"+model1.getSocietyName()+"社团啦，社长号召你赶紧去给社团点赞打榜吧！";
		String cont = "";
		Integer resultCode = 0;//处理结果code
		String result = "";
		Integer notype = Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_PERSON_NOTICE;
		Log log = null;
		if(!SocietyMember.MEMBERSTATUS_CHECKKED.toString().equals(request.getParameter("newstatus"))){//不通过
			log =new Log();
			log.setCreateTime(Calendar.getInstance().getTimeInMillis());
			log.setActID(request.getParameter("smemberid"));
			log.setLogID(UUIDUtil.getUUID());
			log.setLogDesc(request.getParameter("mysug"));
			log.setLogtype(Log.TYPE_SOCIETY_MEMBER);
			log.setLogAuthor(CurrentUser.getPhoneNum());
			String cont2 = "对不起，社长说ta不同意你加入"+model1.getSocietyName()+"社团，麦同学可没说你坏话呀。拒绝原因:"+log.getLogDesc();
			cont = cont2;
			resultCode = Notification.DEALRESULT_CODE_REFUSE;
			
			result = "审核未通过";
			notype = Notification.NOTIFICATIONTYPE_TYPE_JOIN_SOCIETY_APPLAY_FAILED;
		}else{//通过
			cont = cont1;
			resultCode = Notification.DEALRESULT_CODE_PASS;
			result = "审核通过";
			notype = Notification.NOTIFICATIONTYPE_TYPE_JOIN_SOCIETY_APPLAY_SUCCESS;
		}
		
		if(SocietyMember.MEMBERSTATUS_CHECKKED.toString().equals(request.getParameter("oldstatus"))){
			map.put("oldstatus", request.getParameter("oldstatus"));
		}
		this.societyService.updateSocietymemberStatus(map, log);
		//给社长发消息------------------------start
		
		Notification n = new Notification();
		n.setCreateTime(new Date().getTime());
		if(StringUtils.isNotEmpty(CurrentUser.getCurrentPersonId())){
			n.setFromPersonID(CurrentUser.getCurrentPersonId());
		}
		n.setFromPersonName(model1.getSocietyName());//活动名称
		n.setHeaderURL(model1.getSocietyLOGO());
		n.setIsDeal(0);
		n.setIsRead(0);
		n.setNeedDeal(0);
		n.setNotificationContent(cont);
		n.setNotificationID(UUIDUtil.getUUID());
		n.setNotificationType(notype);
		n.setObjID(model1.getSocietyID());
		n.setToPersonID(personid);
		n.setTitle(model1.getSocietyName());
		noticeService.add(n);
		noticeService.push2Single(n);
		
		
		
		//给社长发消息-------------------------end
		
		//更新用户之前申请的消息状态
		List<Notification> list  = noticeService.getOldStatusList(CurrentUser.getCurrentPersonId(), Notification.NOTIFICATIONTYPE_TYPE_JOIN_SOCIETY_APPLAY, personid );
		if(list!=null&&list.size()==1){
			Notification nm = list.get(0);
			nm.setIsDeal(Notification.ISDEAL_YES);
			nm.setDealResult(result);
			nm.setDealResultCode(resultCode);
			nm.setDealTime(new Date().getTime());
			nm.setDealPersonID(CurrentUser.getCurrentPersonId());
			nm.setDealPersonName(CurrentUser.getPersonName());
			noticeService.updateModel(nm);
			
		}
		//更新用户之前申请的消息状态
		//String toPersonID,String notificationType,String fromPersonID ,String datetime)
		return "{\"errorcode\":0,\"member_status\":\""+request.getParameter("newstatus")+"\",\"smemberid\":\""+request.getParameter("smemberid")+"\",\"personid\":\""+personid+"\"}";
//		return "redirect:/society/SocietyMemberList.action";
	}

	@RequestMapping(value = "/checkSPersonNum")
	@ResponseBody
	public Integer checkSPersonNum(HttpServletRequest request) throws BaseException {
		if(StringUtils.isBlank(request.getParameter("sid"))){
			return 0;
		}
		Society st = societyService.getSocietyByID(request.getParameter("sid"));
		if((st.getJoinPersonNum()==null?0:st.getJoinPersonNum()) < (st.getMemberNum()==null?0:st.getMemberNum()) && st.getStatus() != Society.STATUS_CLOSE){
			return 1;
		}else{
			return 0;
		}
	}

	@RequestMapping(value = "/SocietyMemberLogList")
	@ResponseBody
	public List<Log> SocietyMemberLogList(HttpServletRequest request) throws BaseException {
		return logService.getLogList(String.valueOf(Log.TYPE_SOCIETY_MEMBER),request.getParameter("smemberid"));
	}

	@RequestMapping(value = "/updatePresident")
	@ResponseBody
	public Boolean updatePresident(String newPresidentPNum,String sid) throws BaseException {
		if(!StringUtils.isBlank(newPresidentPNum)){
			User user = userService.getUserByPhoneNum(newPresidentPNum,1);
			if(user != null){
				Society society = societyService.getSocietyByID(sid);
				if(society!=null){
					society.setAdminID(user.getPersonID());
					society.setAdminName(user.getPersonName());
					society.setSchoolID(user.getSchoolID());
					society.setSchoolName(user.getSchoolName());
					societyService.updatePresident(society,CurrentUser.getCurrentUserId(),user.getUserID());
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@RequestMapping(value = "/SocietyLogListByPresident")
	@ResponseBody
	public List<Log> SocietyLogListByPresident(HttpServletRequest request) throws BaseException {
		return logService.getLogList(String.valueOf(Log.TYPE_SOCIETY_OFFLINE),request.getParameter("societyID"));
	}

	@RequestMapping("/societyInfo")
	public String societyInfo(Model model, String societyID) throws BaseException {
		String gps_map_static_str = "北京",stagRefsstr=",";
		Society society = societyService.getSocietyByID(societyID);
		if(society == null){
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			if(null!=subject){
				subject.logout();
				CurrentUser.setCurrentUser(null);
			}
			return "redirect:/nav/toLogIn.action";
		}

		if(society != null && !StringUtils.isBlank(society.getGpsLongitude()) && !StringUtils.isBlank(society.getGpsLatitude())){
			gps_map_static_str = society.getGpsLongitude() + "," + society.getGpsLatitude();
		}
		List<SocietytagRef> societytagRefs = this.societyService.findSocietytagRefListBySID(society.getSocietyID());
		for(SocietytagRef str : societytagRefs){
			stagRefsstr += str.getSocietyTagID()+ ",";
		}
		model.addAttribute("stagRefsstr",stagRefsstr);
		model.addAttribute("gps_map_static_str",gps_map_static_str);
		model.addAttribute("society", society);
		return "/society/societyInfo";
	}

	@RequestMapping("/societyPersonInfoList")
	public String societyPersonInfoList(Model model, HttpServletRequest request) throws BaseException {
		Map<String,Object> params = new HashMap<String,Object>();
		String societyID = request.getParameter("societyID");
		if(StringUtils.isBlank(societyID)){
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			if(null!=subject){
				subject.logout();
				CurrentUser.setCurrentUser(null);
			}
			return "redirect:/nav/toLogIn.action";
		}
		params.put("societyID", societyID);
		model.addAttribute("sid", societyID);

		params.put("memberStatus",SocietyMember.MEMBERSTATUS_CHECKKED);
		model.addAttribute("sptype",SocietyMember.MEMBERSTATUS_CHECKKED);

		int pageNumber = 1;
		if(!StringUtils.isBlank(request.getParameter("page"))){
			pageNumber = Integer.parseInt(request.getParameter("page"));
		}

		PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
		Page<SocietyMember> pageObj = this.societyService.findSocietymemberListBySID(params, paginationParameters);

		model.addAttribute("smlist", pageObj.getDataList());
		model.addAttribute("page", pageObj.getCurrentPageNumber());
		model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
		model.addAttribute("totalsize", pageObj.getTotalSize());
		model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
		return "/society/societyPersonInfoList";
	}

	@RequestMapping(value = "/societyActivityInfoList")
	public String societyActivityInfoList(Model model,HttpServletRequest request) throws BaseException {
		if(StringUtils.isBlank(request.getParameter("societyID"))){
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			if(null!=subject){
				subject.logout();
				CurrentUser.setCurrentUser(null);
			}
			return "redirect:/nav/toLogIn.action";
		}

		model.addAttribute("societyID", request.getParameter("societyID"));

		StringBuffer mywhere = new StringBuffer(" activityStatus = " + Activity.ACTIVITY_STATUS_READY);

		mywhere.append(" and societyID = '" + request.getParameter("societyID") + "'");

		int pageNumber = 1;
		if(!StringUtils.isBlank(request.getParameter("page"))){
			pageNumber = Integer.parseInt(request.getParameter("page"));
		}

		PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
		Page<Activity> pageObj = this.activityService.listActivityList(mywhere.toString(), paginationParameters);

		model.addAttribute("actlist", pageObj.getDataList());
		model.addAttribute("page", pageObj.getCurrentPageNumber());
		model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
		model.addAttribute("totalsize", pageObj.getTotalSize());
		model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
		return "/society/societyActivityInfoList";
	}


	@RequestMapping("/shareSociety")
	public String shareSociety(Model model, String societyID) throws BaseException {
		Society society = societyService.getSocietyByID(societyID);
		if(society == null && society.getStatus() != Society.STATUS_RELEASED){
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			if(null!=subject){
				subject.logout();
				CurrentUser.setCurrentUser(null);
			}
			return "redirect:/nav/toLogIn.action";
		}
		String _societyNote = society.getSocietyNote();
		_societyNote = _societyNote.replaceAll("\r\n", "<br />");
		_societyNote = _societyNote.replaceAll("\r", "<br />");
		_societyNote = _societyNote.replaceAll("\n", "<br />");
		society.setSocietyNote(_societyNote);
		model.addAttribute("society", society);
		return "/society/shareSociety";
	}
}
