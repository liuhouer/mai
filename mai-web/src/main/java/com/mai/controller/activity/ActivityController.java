package com.mai.controller.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mai.activity.entity.*;
import com.mai.activity.service.PhotoService;
import com.mai.util.CommonUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.dto.ActivityDTO;
import com.mai.activity.service.ActivityService;
import com.mai.activity.service.LogService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.GeohashUtil;
import com.mai.framework.utils.UUIDUtil;
import com.mai.notification.entity.Notification;
import com.mai.notification.service.NotificationService;
import com.mai.society.entity.Society;
import com.mai.society.service.SocietyService;
import com.mai.user.entity.Person;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.service.UserService;
import com.mai.util.ConfigUtil;
import com.mai.util.CurrentUser;
import com.mai.util.TimeUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.processing.OperationManager;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private SocietyService societyService;

    @Autowired
    private LogService logService;

    @Autowired
    private UploadManager uploadService;
    
    @Autowired
	private NotificationService noticeService;

    @RequestMapping(value = "/findActivityByPage")
    @ResponseBody
    public Page<Activity> findActivityByPage() throws BaseException {
        PaginationParameters paginationParameters = new PaginationParameters();
        ActivityDTO activityDTO = new ActivityDTO();
        Page<Activity> activityByPage = activityService.findActivityByPage(activityDTO, paginationParameters);
        return activityByPage;
    }

    /**
     * 我参加的活动列表
     *
     * @param personID
     * @param rowNum
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "/findMyActivityByPage")
    @ResponseBody
    public Page<Activity> findMyActivityByPage(@RequestParam(required = false) String personID,
                                               @RequestParam(required = false)
                                               Integer rowNum) throws BaseException {
        PaginationParameters paginationParameters = new PaginationParameters();
        paginationParameters.setCurrentPageNumber(rowNum);
        return activityService.findMyActivityByPage(personID, paginationParameters);
    }

    @RequestMapping(value = "/ActivityList")
    public String ActivityList(Model model,HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" activityStatus > " + Activity.ACTIVITY_STATUS_DELETE);
        if(!StringUtils.isBlank(request.getParameter("stitle"))){
            mywhere.append(" and activityTitle like '%"+request.getParameter("stitle").trim()+"%'");
            model.addAttribute("stitle",request.getParameter("stitle"));
        }
        if(!StringUtils.isBlank(request.getParameter("ssName"))){
            mywhere.append(" and societyName like '%"+request.getParameter("ssName").trim()+"%'");
            model.addAttribute("ssName",request.getParameter("ssName"));
        }

        if(!StringUtils.isBlank(request.getParameter("stype"))){
                mywhere.append(" and activityStatus = "+request.getParameter("stype"));
                model.addAttribute("stype", request.getParameter("stype"));
        }
        if(CurrentUser.hasRole(Role.ROLENAME_PRESIDENT)){
            mywhere.append(" and societyID = '" + CurrentUser.getSocietyID() + "'");
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

        int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<Activity> pageObj = this.activityService.listActivityList(mywhere.toString(),paginationParameters);

        model.addAttribute("actlist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
        return "/activity/activityList";
    }

    @RequestMapping(value = "/ActivityStatus")
    public String ActivityStatus(Model model,HttpServletRequest request) throws BaseException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("id", request.getParameter("actid"));
        map.put("status", request.getParameter("newstatus"));
        Log log = null;
        
       
        if(!Activity.ACTIVITY_STATUS_READY.toString().equals(request.getParameter("newstatus"))){
            log =new Log();
            log.setCreateTime(Calendar.getInstance().getTimeInMillis());
            log.setActID(request.getParameter("actid"));
            log.setLogID(UUIDUtil.getUUID());
            log.setLogDesc(request.getParameter("mysug"));
            log.setLogAuthor(CurrentUser.getPhoneNum());
            log.setLogtype(Log.TYPE_ACTIVITY);
        }
        String cont = "";
        Integer notype = Notification.NOTIFICATION_TYPE_ACTIVITY_CHANGED;
        /*
         * 
         	活动新建的审核反馈消息	麦同学icon	麦同学	"审核通过：您新建的xxx活动审核通过了，期待圆满成功哦！
			审核未通过：您新建的xxx活动由于如下原因审核未通过，您可以修改后再次提交：xxxxx"
			//你的xxx活动由于如下原因被下架：xxxx，您可以修改后再次申请活动发布。
	    	//你的xxx社团由于如下原因被下架：xxxx，您可以修改后再次申请社团发布。

         * */
        
        String title = "活动审核";
        Activity model1  = activityService.findActivityByID(request.getParameter("actid"));
        
        
        //判断社长，取消发布自己 的活动
        boolean flag = false ; 
        if(model1!=null && model1.getSocietyID()!=null){
        	flag = model1.getSocietyID().equals(CurrentUser.getSocietyID());
        }
        
        String fromperson = "麦同学";
        String headerurl = ConfigUtil.getSystemDefaultIcon();
        //判断社长，取消发布自己 的活动
        if(flag){
        	 
        	cont = "该活动已在【"+TimeUtils.getNowTime()+"】由您进行了取消发布操作";
        	if(Activity.ACTIVITY_STATUS_NON_CHECKED.toString().equals(request.getParameter("newstatus"))){//通过
              	cont = "【"+model1.getActivityTitle()+"】已由您在【"+TimeUtils.getNowTime()+"】重新发布";
              	//title = "活动审核通过";
            }
        	//title="";
        	fromperson = CurrentUser.getPersonName();
        	//获取社长头像赋予发消息实体
        	String pid  =  CurrentUser.getCurrentPersonId();
        	Person  p = userService.getPersonByID(pid);
        	if(p!=null){
        		if(StringUtils.isNotEmpty(p.getHeaderURL())){
        			headerurl = p.getHeaderURL();
        		}
        	}
        	//end
        }else{
        	 if(Activity.ACTIVITY_STATUS_READY.toString().equals(request.getParameter("newstatus"))){//通过
             	cont = "审核通过：您新建的"+model1.getActivityTitle()+"活动审核通过了，期待圆满成功哦！";
             	//title = "活动审核通过";
             }else if(Activity.ACTIVITY_STATUS_NOTPASS.toString().equals(request.getParameter("newstatus"))){//不通过
             	cont = "审核未通过：您新建的"+model1.getActivityTitle()+"活动由于如下原因审核未通过，您可以修改后再次提交："+request.getParameter("mysug");
             	//title = "活动审核未通过";
             }else if(Activity.ACTIVITY_STATUS_OFFLINE.toString().equals(request.getParameter("newstatus"))){//下架
             	cont = "你的"+model1.getActivityTitle()+"活动由于如下原因被下架："+request.getParameter("mysug")+"，您可以修改后再次申请社团发布。";
             	//title = "活动下架";
             }
        }
        this.activityService.updateActivityStatus(map, log);
        
        //给社长发消息------------------------start
  		Notification n = new Notification();
  		n.setCreateTime(new Date().getTime());
  		if(StringUtils.isNotEmpty(CurrentUser.getCurrentPersonId())){
  			n.setFromPersonID(CurrentUser.getCurrentPersonId());
  		}
  		n.setFromPersonName(fromperson);//麦同学
		n.setHeaderURL(headerurl);//麦同学LOGO
  		n.setIsDeal(0);
  		n.setIsRead(0);
  		n.setNeedDeal(0);
  		n.setNotificationContent(cont);
  		n.setNotificationID(UUIDUtil.getUUID());
  		n.setNotificationType(notype);
  		n.setObjID(model1.getActivityID());
  		n.setToPersonID(model1.getAdminID());
  		n.setTitle(model1.getActivityTitle());
  		noticeService.add(n);
  		noticeService.push2Single(n);
  		
  		//给社长发消息-------------------------end
        
        return "redirect:/activity/ActivityList.action?page="+request.getParameter("page");
    }

    @RequestMapping(value = "/newActivity")
    public String addActivity(Model model,HttpServletRequest request) throws BaseException {
        Activity activity = new Activity();
        String gps_map_static_str = "北京",acttagRefsstr=",";
        if(!StringUtils.isBlank(request.getParameter("actid"))){
            activity = this.activityService.findActivityByID(request.getParameter("actid"));
            model.addAttribute("fileContent", activity.getActivityDetailContent());
            if(!StringUtils.isBlank(activity.getGpsLongitude()) && !StringUtils.isBlank(activity.getGpsLatitude())){
                gps_map_static_str = activity.getGpsLongitude() + "," + activity.getGpsLatitude();
            }
            List<ActivitytagRef> activitytagRefs = this.activityService.findActivitytagRefListByActID(activity.getActivityID());
            for(ActivitytagRef acttr : activitytagRefs){
                acttagRefsstr += acttr.getTagID()+ ",";
            }

        }else{
            activity.setNeedCheck(Activity.NEEDCHECK_YES);
            activity.setIsInner(Activity.ISINNER_NOT);
//            activity.setActivityStatus(Activity.ACTIVITY_STATUS_NON_CHECKED);
            activity.setRecommendNo(0);
        }
        model.addAttribute("gps_map_static_str", gps_map_static_str);
        model.addAttribute("activity", activity);
        model.addAttribute("acttagRefsstr", acttagRefsstr);
        return "/activity/activity";
    }

    @RequestMapping(value = "/saveOrUpdateActivity")
    public String saveOrUpdateActivity(@RequestParam(required = false) MultipartFile Filedata,Activity activity,HttpServletRequest request,Model model) throws BaseException, ParseException, IOException {
        Calendar ca = Calendar.getInstance();
        activity.setCreateTime(ca.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = CurrentUser.getCurrentUser();
        activity.setAdminID(user.getPersonID());
        activity.setAdminName(user.getPersonName());
        Society society = societyService.findSocietyByAdminIDAndValid(user.getPersonID());
        activity.setSocietyID(society.getSocietyID());
        activity.setSocietyName(society.getSocietyName());
        activity.setSchoolID(society.getSchoolID());
        activity.setShareDESC(activity.getSocietyName());
        if(Filedata!=null){
            String fileName = Filedata.getOriginalFilename();
            if(!StringUtils.isBlank(fileName)){
//                String postfix = fileName.substring(fileName.lastIndexOf("."));
//                String _filename = MD5Tools.md5(fileName)+"-"+Calendar.getInstance().getTimeInMillis();
                String newFilename = UUIDUtil.getUUID();
                Response response = uploadService.put(Filedata.getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket",null));
                if(response.isOK()){
                      activity.setCoverURL(ConfigUtil.getProperty("qiniu.qiniuDomainURL")+"/"+newFilename+ConfigUtil.getThumbnailParam(Filedata.getSize()));
                }
            }
        }

        if(activity.getShowStartTime().length() == 16){
            activity.setShowStartTime(activity.getShowStartTime() + ":00");
        }
        if(activity.getShowEndTime().length() == 16){
            activity.setShowEndTime(activity.getShowEndTime() + ":00");
        }
        ca.setTimeInMillis(sdf.parse(activity.getShowStartTime()).getTime());
        activity.setStartTime(ca.getTimeInMillis());
        ca.setTimeInMillis(sdf.parse(activity.getShowEndTime()).getTime());
        activity.setEndTime(ca.getTimeInMillis());


        if(activity.getMaxPersonNum() == null){
            activity.setMaxPersonNum(0);
        }

        ActivityDetail activityDetail = new ActivityDetail();
        activityDetail.setCreateTime(activity.getCreateTime());
        activityDetail.setActivityDetail(request.getParameter("fileContent"));




        List<ActivitytagRef> activitytagRefs = new ArrayList<ActivitytagRef>();


        if(StringUtils.isBlank(activity.getActivityID())){
            activity.setActivityID(UUIDUtil.getUUID());
            activityDetail.setActivityDetailID(UUIDUtil.getUUID());
            activity.setActivityDetailID(activityDetail.getActivityDetailID());
            if(request.getParameterValues("tagids")!=null){
                ActivitytagRef activitytagRef = null;
                for(String tagid : request.getParameterValues("tagids")){
                    activitytagRef = new ActivitytagRef();
                    activitytagRef.setTagActivityRefID(UUIDUtil.getUUID());
                    activitytagRef.setTagID(tagid);
                    activitytagRef.setActivityID(activity.getActivityID());
                    activitytagRefs.add(activitytagRef);
                }
            }
            activity.setShareURL(ConfigUtil.getProperty("activity.share.url")+activity.getActivityID());
            activity.setActivityDetail(ConfigUtil.getProperty("activity_detail_url")+activity.getActivityDetailID());
            activityService.addActivity(activity,activityDetail,activitytagRefs);
        }else{
            activityDetail.setActivityDetailID(activity.getActivityDetailID());
            activityDetail.setActivityDetailHerf(activity.getActivityDetail());
            if(request.getParameterValues("tagids")!=null){
                ActivitytagRef activitytagRef = null;
                for(String tagid : request.getParameterValues("tagids")){
                    activitytagRef = new ActivitytagRef();
                    activitytagRef.setTagActivityRefID(UUIDUtil.getUUID());
                    activitytagRef.setTagID(tagid);
                    activitytagRef.setActivityID(activity.getActivityID());
                    activitytagRefs.add(activitytagRef);
                }
            }
            activity.setShareURL(ConfigUtil.getProperty("activity.share.url")+activity.getActivityID());
            activity.setActivityDetail(ConfigUtil.getProperty("activity_detail_url") + activity.getActivityDetailID());
            if(!StringUtils.isBlank(activity.getGpsLatitude()) && !StringUtils.isBlank(activity.getGpsLongitude())){
                activity.setGeohash(GeohashUtil.encode(Double.parseDouble(activity.getGpsLatitude()), Double.parseDouble(activity.getGpsLongitude())));
            }
            activityService.updateActivity(activity,activityDetail,activitytagRefs);
        }
        return "redirect:/activity/ActivityList.action";
    }

    @RequestMapping(value = "/ActivityMemberList")
    public String ActivityMemberList(Model model,HttpServletRequest request) throws BaseException {
        Map<String,String> params = new HashMap<String,String>();
        if(StringUtils.isBlank(request.getParameter("actid"))){
            return "redirect:/activity/ActivityList.action";
        }
        params.put("activityID", request.getParameter("actid"));
        model.addAttribute("actid", request.getParameter("actid"));

        if(!StringUtils.isBlank(request.getParameter("aptype"))){
            params.put("memberStatus",request.getParameter("aptype"));
            model.addAttribute("aptype",request.getParameter("aptype"));
        }
        if(!StringUtils.isBlank(request.getParameter("apsid"))){
            params.put("schoolID", request.getParameter("apsid"));
            model.addAttribute("apsid", request.getParameter("apsid"));
        }
        if(!StringUtils.isBlank(request.getParameter("apgenderid"))){
            params.put("gender", request.getParameter("apgenderid"));
            model.addAttribute("apgenderid", request.getParameter("apgenderid"));
            model.addAttribute("apgendername", request.getParameter("apgendername"));
        }
        int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<ActivityMember> pageObj = this.activityService.findActivitymemberListByActID(params,paginationParameters);;

        if(CurrentUser.hasRole(Role.ROLENAME_PRESIDENT)){
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

        model.addAttribute("actmlist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
        return "/activity/activityPersonList";
    }

    @RequestMapping(value = "/ActivityMemberStatus")
    @ResponseBody
    public String ActivityMemberStatus(Model model,HttpServletRequest request) throws BaseException {
        int autodelPerson = 0;
        if(!StringUtils.isBlank(request.getParameter("autodelPerson"))){
            autodelPerson = Integer.valueOf(request.getParameter("autodelPerson"));
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("id",request.getParameter("actmemberid"));
        map.put("status", request.getParameter("newstatus"));
        map.put("actid", request.getParameter("actid"));
        
        
        //added ....nocie
        String personid = request.getParameter("personid");//申请者的id
        Activity model1  = activityService.findActivityByID(request.getParameter("actid"));
        String cont1 = "您报名的"+model1.getSocietyName()+"动审核通过了，快去看看其他一起报名的同学";
        String cont = "";
        Integer resultCode = 0;//处理结果code
      	String result = "";
      	Integer notype = Notification.NOTIFICATIONTYPE_TYPE_SOCIETY_PERSON_NOTICE;
      	String title = "活动审核";
        //added ....nocie
        
        
        Log log = null;
      
        if(!ActivityMember.MEMBERSTATUS_CHECKKED.toString().equals(request.getParameter("newstatus"))){
            log =new Log();
            log.setCreateTime(Calendar.getInstance().getTimeInMillis());
            log.setActID(request.getParameter("actmemberid"));
            log.setLogID(UUIDUtil.getUUID());
            log.setLogDesc(request.getParameter("mysug"));
            log.setLogtype(Log.TYPE_ACTIVITY_MEMBER);
            log.setLogAuthor(CurrentUser.getPhoneNum());
            String cont2 = "桑心，您报名的"+model1.getSocietyName()+"活动审核未通过，社长说"+request.getParameter("mysug")+"，再次报名打动ta？";
            cont  = cont2;
            resultCode = Notification.DEALRESULT_CODE_REFUSE;
			
			result = "审核未通过";
			title  = "活动审核未通过";
			notype = Notification.NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER_FAILED;
			
        }else{
        	cont = cont1;
        	resultCode = Notification.DEALRESULT_CODE_PASS;
			
			result = "审核通过";
			title  = "活动审核通过";
			notype = Notification.NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER_PASS;
        }
        if(ActivityMember.MEMBERSTATUS_CHECKKED.toString().equals(request.getParameter("oldstatus"))){
            map.put("oldstatus", request.getParameter("oldstatus"));
        }
        this.activityService.updateActivitymemberStatus(map, log,request.getParameter("actid"),autodelPerson,CurrentUser.getPhoneNum());
        
      //给社长发消息------------------------start
		
      		Notification n = new Notification();
      		n.setCreateTime(new Date().getTime());
      		if(StringUtils.isNotEmpty(CurrentUser.getCurrentPersonId())){
      			n.setFromPersonID(CurrentUser.getCurrentPersonId());
      		}
      		n.setFromPersonName(model1.getActivityTitle());//活动名称
      		n.setHeaderURL(model1.getCoverURL());
      		n.setIsDeal(0);
      		n.setIsRead(0);
      		n.setNeedDeal(0);
      		n.setNotificationContent(cont);
      		n.setNotificationID(UUIDUtil.getUUID());
      		n.setNotificationType(notype);
      		n.setObjID(model1.getActivityID());
      		n.setToPersonID(personid);
      		n.setTitle(model1.getActivityTitle());
      		noticeService.add(n);
      		noticeService.push2Single(n);
      		
      		//给社长发消息-------------------------end
      		

    		//更新用户之前申请的消息状态
    		List<Notification> list  = noticeService.getOldStatusList(CurrentUser.getCurrentPersonId(), Notification.NOTIFICATION_TYPE_ACTIVITY_APPLAY_ENTER, personid );
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
        return "{\"errorcode\":0,\"member_status\":\""+request.getParameter("newstatus")+"\",\"amemberid\":\""+request.getParameter("actmemberid")+"\",\"personid\":\""+personid+"\"}";

//        return "redirect:/activity/ActivityMemberList.action?actid="+request.getParameter("actid");
    }

    @RequestMapping(value = "/checkActPersonNum")
    @ResponseBody
    public Map<String,Object> checkActPersonNum(HttpServletRequest request) throws BaseException {
            Map<String,Object> _map = new HashMap<String, Object>();
            if(StringUtils.isBlank(request.getParameter("actid"))){
               _map.put("errorcode",0); //活动ID丢失
            }
            Activity act = activityService.findActivityByID(request.getParameter("actid"));
            if(Calendar.getInstance().getTimeInMillis() < act.getStartTime()){
                int _joinNum = act.getJoinPersonNum() == null ? 0 : act.getJoinPersonNum();
                int _maxNum = act.getMaxPersonNum()==null?0:act.getMaxPersonNum();
                if(_joinNum < _maxNum){
                    _map.put("code",1);
                    int _num = _maxNum - _joinNum;
                    if(_num == 1){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("activityID",request.getParameter("actid"));
                        params.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                        int _normal_Num = activityService.findActivitymemberCountByStatus(params)-1;
                        _map.put("num",_normal_Num>0?_normal_Num:0);//未审核人员数
                    }
                }else{
                    _map.put("code",-2);//人员已满
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("activityID",request.getParameter("actid"));
                    params.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                    _map.put("num",activityService.findActivitymemberCountByStatus(params));//未审核人员数
                }
            }else{
                _map.put("code",-1);//活动已开始
                Map<String,String> params = new HashMap<String,String>();
                params.put("activityID",request.getParameter("actid"));
                params.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                _map.put("num",activityService.findActivitymemberCountByStatus(params));
            }
            return _map;
    }

    @RequestMapping(value = "/ActivityLogList")
    @ResponseBody
    public List<Log> ActivityLogList(HttpServletRequest request) throws BaseException {
       return logService.getLogList(String.valueOf(Log.TYPE_ACTIVITY),request.getParameter("actid"));
    }

    @RequestMapping(value = "/ActivityMemberLogList")
    @ResponseBody
    public List<Log> ActivityMemberLogList(HttpServletRequest request) throws BaseException {
        return logService.getLogList(String.valueOf(Log.TYPE_ACTIVITY_MEMBER),request.getParameter("actmemberid"));
    }

    @RequestMapping(value = "/ActivityInfoList")
    @ResponseBody
    public List<Activity> ActivityInfoList(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        Map<String,String> params = new HashMap<String,String>();
        params.put("actTitle",java.net.URLDecoder.decode(request.getParameter("actTitle"),"utf-8"));
        params.put("actStatus",String.valueOf(Activity.ACTIVITY_STATUS_READY));
        List<Activity> activityList = activityService.findActivityListByActTitleAndStatus(params);
        if(activityList!=null){
            return activityList;
        }else{
            return Arrays.asList(new Activity());
        }
    }

    @RequestMapping(value = "/imgUpload")
    @ResponseBody
    public String imgUpload(@RequestParam(required = false) MultipartFile imgFile,HttpServletRequest request) throws BaseException, IOException {
        if(imgFile!=null){
            String fileName = imgFile.getOriginalFilename();
            if(!StringUtils.isBlank(fileName)){
//                String postfix = fileName.substring(fileName.lastIndexOf("."));
//                String _filename = MD5Tools.md5(fileName)+"-"+Calendar.getInstance().getTimeInMillis();
                String newFilename = UUIDUtil.getUUID();
                Response response = uploadService.put(imgFile.getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket",null));
                if(response.isOK()){
                    return ConfigUtil.getProperty("qiniu.qiniuDomainURL")+"/"+newFilename + ConfigUtil.getThumbnailParam(imgFile.getSize());
                }else{
                    return "";
                }
            }else{
                return "";
            }
        }else{
            return "";
        }
    }

    @RequestMapping(value = "/viewActivity")
    public String viewActivity(Model model,HttpServletRequest request) throws BaseException {
        Activity activity = new Activity();
        String gps_map_static_str = "北京",acttagRefsstr=",";
        if(!StringUtils.isBlank(request.getParameter("actid"))){
            activity = this.activityService.findActivityByID(request.getParameter("actid"));
            model.addAttribute("fileContent", activity.getActivityDetailContent());
            if(!StringUtils.isBlank(activity.getGpsLongitude()) && !StringUtils.isBlank(activity.getGpsLatitude())){
                gps_map_static_str = activity.getGpsLongitude() + "," + activity.getGpsLatitude();
            }
            List<ActivitytagRef> activitytagRefs = this.activityService.findActivitytagRefListByActID(activity.getActivityID());
            for(ActivitytagRef acttr : activitytagRefs){
                acttagRefsstr += acttr.getTagID()+ ",";
            }

        }else{
            activity.setNeedCheck(Activity.NEEDCHECK_YES);
            activity.setIsInner(Activity.ISINNER_NOT);
            activity.setRecommendNo(0);
        }
        model.addAttribute("gps_map_static_str", gps_map_static_str);
        model.addAttribute("activity", activity);
        model.addAttribute("acttagRefsstr", acttagRefsstr);
        return "/activity/activityDetail";
    }

    /**
     * 判断活动审核状态处理，0为不审核，1为审核，从审核改为不审核，需要判断当前报名是否存在未审核状态人员
     * @param request
     * @return
     * @throws BaseException
     */
    @RequestMapping(value = "/checkActAuditMethod")
    @ResponseBody
    public Map<String,Object> checkActAuditMethod(HttpServletRequest request) throws BaseException {
        Map<String,Object> _map = new HashMap<String, Object>();
        if(StringUtils.isBlank(request.getParameter("actid")) || StringUtils.isBlank(request.getParameter("newAuditMethod"))){
            _map.put("errorcode", 0); //活动ID或者新的审核状态丢失
        }else if("1".equals(request.getParameter("newAuditMethod"))){
            _map.put("errorcode", -1); //不审核改为审核不做处理
        }else {
            Activity act = activityService.findActivityByID(request.getParameter("actid"));
            if(Calendar.getInstance().getTimeInMillis() < act.getStartTime()){
                int _joinNum = act.getJoinPersonNum() == null ? 0 : act.getJoinPersonNum();
                int _maxNum = act.getMaxPersonNum()==null?0:act.getMaxPersonNum();
                if(_joinNum < _maxNum){
                    _map.put("code",1);
                    int lotNum = _maxNum - _joinNum;
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("activityID",request.getParameter("actid"));
                    params.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                    Integer _normal_Num = activityService.findActivitymemberCountByStatus(params);
                    _map.put("num",_normal_Num>0?_normal_Num:0);//未审核人员数
                    _map.put("lotNum",lotNum);//加入活动剩余名额
                }else{
                    _map.put("code",-2);//人员已满
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("activityID",request.getParameter("actid"));
                    params.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                    _map.put("num",activityService.findActivitymemberCountByStatus(params));//未审核人员数
                }
            }else{
                _map.put("code",-1);//活动已开始
                Map<String,String> params = new HashMap<String,String>();
                params.put("activityID",request.getParameter("actid"));
                params.put("memberStatus",String.valueOf(ActivityMember.MEMBERSTATUS_NORMAL));
                _map.put("num",activityService.findActivitymemberCountByStatus(params));
            }
        }
        return _map;
    }

    @RequestMapping("/shareActivity")
    public String shareActivity(Model model, String activityID) throws BaseException {
        Activity activity = activityService.getActivityFromRedisByID(activityID);
        if(activity == null && activity.getActivityStatus() != Activity.ACTIVITY_STATUS_READY){
            org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            if(null!=subject){
                subject.logout();
                CurrentUser.setCurrentUser(null);
            }
            return "redirect:/nav/toLogIn.action";
        }
        activity.setActivityDetailContent(activityService.getActivityDetail(activity.getActivityDetailID()));
        model.addAttribute("activity", activity);
        return "/activity/shareActivity";
    }


    public static final void main(String[] args){

        String phone = "13521220099";
        phone = phone.substring(0, 3) + "****" + phone.substring(7);
        System.out.println(phone);

//        String paths[] = {"spring/service-app.xml"};
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
//        PhotoService photoService = (PhotoService)ctx.getBean("photoService");
//        try {
//            List<Photo> photoList = photoService.findPotoByWH();
//            String returnbody = null;
//            Photo _photo = null;
//            for(Photo photo : photoList){
//                try {
//                    returnbody = CommonUtil.get(ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP")+"/"+photo.getPhotoURL()+"?" + java.net.URLEncoder.encode("imageMogr2/auto-orient|imageInfo","utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                JSONObject jsonObject = JSON.parseObject(returnbody);
//                if(jsonObject!=null && jsonObject.get("error") == null){
//                    _photo = new Photo();
//                    _photo.setPhotoID(photo.getPhotoID());
//                    _photo.setWidth(Double.parseDouble(jsonObject.get("width").toString()));
//                    _photo.setHeight(Double.parseDouble(jsonObject.get("height").toString()));
//                    photoService.updateWH(_photo);
//                }
//            }
//            List<Photo> photoList = photoService.findPhotoByActivityID("3792234617552456568");
//            String returnbody = null;
//            Photo _photo = null;
//            for(Photo photo : photoList){
//                try {
//                    returnbody = CommonUtil.get(ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP")+"/"+photo.getPhotoURL()+"?" + java.net.URLEncoder.encode("imageMogr2/auto-orient|imageInfo","utf-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                JSONObject jsonObject = JSON.parseObject(returnbody);
//                if(jsonObject != null && jsonObject.get("error") == null){
//                    String format = jsonObject.getString("format");
//
//                }
//            }
//            "http://api.qiniu.com/pfop/"
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(CommonUtil.setNameValuePair("avconcat", ""));
//            String returnbody = CommonUtil.post("http://api.qiniu.com/pfop/",params);
//            System.out.println(returnbody);
//            Auth auth = CommonUtil.getQiniuAuth(ConfigUtil.getProperty("qiniu.accessKey"), ConfigUtil.getProperty("qiniu.secretKey"));
//            System.out.println(auth.);

//            OperationManager operater = new OperationManager(auth);
//            String bucket = "cardaudio";
//            String key = "1.mp3";
//            String fops = "avconcat/2/format/amr/aHR0cDovLzd4cGIwNS5jb20yLnowLmdsYi5xaW5pdWNkbi5jb20vMi5tcDM=|saveas/Y2FyZGF1ZGlvOjMubXAz";
//            String notifyURL = "";
//            boolean force = true;
//            String pipeline = "";
//            StringMap params = new StringMap().putNotEmpty("notifyURL", notifyURL).putWhen("force", 1, force).putNotEmpty("pipeline", pipeline);
//
//            String id = operater.pfop(bucket, key, fops, null);
//            System.out.println(id);
//        } catch (QiniuException e) {
//            Response r = e.response;
//            // 请求失败时简单状态信息
//            System.out.println(r.toString());
//            try {
//                // 响应的文本信息
//                System.out.println(r.bodyString());
//            } catch (QiniuException e1) {
//                //ignore
//            }
//        }
    }
}
