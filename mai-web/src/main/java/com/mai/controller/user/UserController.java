package com.mai.controller.user;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Log;
import com.mai.activity.entity.School;
import com.mai.activity.service.LogService;
import com.mai.activity.service.SchoolService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.society.entity.Society;
import com.mai.society.service.SocietyService;
import com.mai.user.entity.Person;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.user.entity.UserroleRef;
import com.mai.user.service.UserService;
import com.mai.util.ConfigUtil;
import com.mai.util.CurrentUser;
import com.mai.util.ExcelReader;
import com.mai.util.TimeUtils;
import com.mai.vo.ActRankVO;
import com.mai.vo.ExpData;
import com.mai.vo.MixRankVO;
import com.mai.vo.SocRankVO;
import com.mai.vo.UserTemp;
import com.mai.vo.UserVO;

/**
 * 社团信息
 * Created by fengdzh on 2015/9/16.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SocietyService societyService;
    @Autowired
    private LogService logService;

	/**
	 * 人员管理页
	 * @param model
	 * @param request
	 * @param msg
	 * @param keyword
	 * @param J_role
	 * @param J_status
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/list")
    public String list(Model model,HttpServletRequest request,String msg, String keyword,String J_role,String J_status,String startTime_,String endTime_) throws BaseException {
    	String result = "/user/user-list";
    	String wheresql  = " 1=1";
    	if(StringUtils.isNotEmpty(keyword)){
    		wheresql+=" and (b.name like '%"+keyword+"%' or b.phoneNum like '%"+keyword+"%' )";
    	}
    	
    	
    	if(StringUtils.isNotEmpty(startTime_)){
    		String startTime =startTime_+ " 00:00:00";
    		long st = TimeUtils.stringToMillis(startTime);
    		wheresql+=" and a.createTime >= "+st;
    	}
    	if(StringUtils.isNotEmpty(endTime_)){
    		String endTime = endTime_ + " 23:59:59";
    		long en = TimeUtils.stringToMillis(endTime);
    		wheresql+=" and a.createTime <= "+en;
    	}
    	if(StringUtils.isNotEmpty(J_role)){
    		if(J_role.equals("0")){//普通用户
    			//wheresql+=" and (d.roleID ='' or d.roleID = 'null' or d.roleID = 'NULL')";
    			wheresql+=" and not exists (select 1 from user_role_ref c where a.userID = c.userID)";
    		}else if(J_role.equals("1")){//社长
    			wheresql+=" and exists (select 1 from user_role_ref c join role d where c.roleID = d.roleID and c.userID = a.userID and d.roleNameEn = '"+Role.ROLENAME_PRESIDENT+"' )";
    		}
    	}
    	
    	//这里拼接分页信息
    	  int pageNumber = 1;
          if(!StringUtils.isBlank(request.getParameter("page"))){
              pageNumber = Integer.parseInt(request.getParameter("page"));
          }

          PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
    	
    	
    	if(StringUtils.isNotEmpty(J_status)){
    		wheresql+=" and a.isValid = "+J_status;
    	}
    	System.out.println(wheresql);
    	//List<UserVO> list  = userService.findAll(wheresql);
    	
    	Page<UserVO> pageObj = this.userService.findAllByPage(wheresql, paginationParameters);
    	List<UserVO> list = pageObj.getDataList();
    	if(StringUtils.isNotEmpty(J_role)){//角色条件不为空
    		if(J_role.equals("0")){//普通用户
    			for (UserVO vo: list) {
    	    			vo.setRolename("普通用户");
    			}
    		}else if(J_role.equals("1")){
    			for (UserVO vo: list) {
	    			vo.setRolename("社长");
    			}
    		}
    	}else{//角色条件为空
	    	for (UserVO vo: list) {
	    		UserroleRef ur = userService.findRefByUidAndRoleID(vo.getUserid(), Role.ROLEID_SHEZHANG);
	    		if(ur!=null&&StringUtils.isNotEmpty(ur.getUserRoleRefID())){
	    			vo.setRolename("社长");
	    		}else{
	    			vo.setRolename("普通成员");
	    		}
			}
    	}
    	 model.addAttribute("list", pageObj.getDataList());
         model.addAttribute("page", pageObj.getCurrentPageNumber());
         model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
         model.addAttribute("totalsize", pageObj.getTotalSize());
         model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
    	
    	
    	//model.addAttribute("list", list);
    	
    	model.addAttribute("keyword", keyword);
    	model.addAttribute("J_role", J_role);
    	model.addAttribute("J_status", J_status);
    	model.addAttribute("J_status", J_status);
    	model.addAttribute("startTime_", startTime_);
    	model.addAttribute("endTime_", endTime_);
    	List<School> schoolList = schoolService.getSchoolList(null);
    	model.addAttribute("schoolList", schoolList);
    	
    	msg = (String) request.getSession().getAttribute("msg");
    	
    	//设置传递上传结果的参数
    	if("success".equals(msg)){
    		model.addAttribute("msg", "导入成功");
    	}else if("extinvald".equals(msg)){
    		model.addAttribute("msg", "文件格式不对，请重新选择");
    	}else if("fail".equals(msg)){
    		model.addAttribute("msg", "导入异常，请重试");
    	}
    	
    	request.getSession().removeAttribute("msg");
        return result;
    }
    
    

	/**
	 * 人员管理筛选导出
	 * @param model
	 * @param request
	 * @param msg
	 * @param keyword
	 * @param J_role
	 * @param J_status
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/export")
    public void export(Model model,HttpServletRequest request,HttpServletResponse response,String msg, String keyword,String J_role,String J_status,String startTime_,String endTime_) throws BaseException {
    try{
    	String wheresql  = " 1=1  and y.memberStatus=1 ";
    	if(StringUtils.isNotEmpty(keyword)){
    		wheresql+=" and (b.name like '%"+keyword+"%' or b.phoneNum like '%"+keyword+"%' )";
    	}
    	
    	
    	if(StringUtils.isNotEmpty(startTime_)){
    		String startTime =startTime_+ " 00:00:00";
    		long st = TimeUtils.stringToMillis(startTime);
    		wheresql+=" and a.createTime >= "+st;
    	}
    	if(StringUtils.isNotEmpty(endTime_)){
    		String endTime = endTime_ + " 23:59:59";
    		long en = TimeUtils.stringToMillis(endTime);
    		wheresql+=" and a.createTime <= "+en;
    	}
    	if(StringUtils.isNotEmpty(J_role)){
    		if(J_role.equals("0")){//普通用户
    			//wheresql+=" and (d.roleID ='' or d.roleID = 'null' or d.roleID = 'NULL')";
    			wheresql+=" and not exists (select 1 from user_role_ref c where a.userID = c.userID)";
    		}else if(J_role.equals("1")){//社长
    			wheresql+=" and exists (select 1 from society d where d.adminID = a.personID and d.status > 0) ";
    		}
    	}
    	
    	
    	if(StringUtils.isNotEmpty(J_status)){
    		wheresql+=" and a.isValid = "+J_status;
    	}
    	System.out.println(wheresql);
    	
    	//List<UserVO> list  = userService.findAll(wheresql);
    	
    	List<ExpData> expList = this.userService.findExpData(wheresql);
    	
    		for (ExpData vo: expList) {
	    		UserroleRef ur = userService.findRefByUidAndRoleID(vo.getUserid(), Role.ROLEID_SHEZHANG);
	    		if(ur!=null&&StringUtils.isNotEmpty(ur.getUserRoleRefID())){
	    			String userid = ur.getUserID();
	    			User u_= userService.getUserByID(userid);
	    			if(u_!=null){
	    				String adminID = u_.getPersonID();
	    				Society s = societyService.findSocietyByAdminID(adminID);
	    				if(s!=null){
	    					if(s.getSocietyID().equals(vo.getSocietyID())){
	    						vo.setRoleName("社长");
	    					}else{
	    						vo.setRoleName("普通成员");
	    					}
	    				}
	    			}
	    			
	    			
	    		}else{
	    			vo.setRoleName("普通成员");
	    		}
	    		if(User.ISVALID_YES == vo.getIntST()){
	    			vo.setStatus("启用");
	    		}else{
	    			vo.setStatus("禁用");
	    		}
			}
    	//model.addAttribute("list", list);
    	
    	 HSSFWorkbook wb = ExcelReader.export(expList);    
         response.setContentType("application/vnd.ms-excel");    
         response.setHeader("Content-disposition", "attachment;filename=person.xls");    
         OutputStream ouputStream = response.getOutputStream();    
         wb.write(ouputStream);    
         ouputStream.flush();    
         ouputStream.close();    
      }catch(Exception e){
    	  e.printStackTrace();
      }
    }
    


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
    public String add(Model model,String J_name,String J_phone,String J_add_role,String J_societyName,String J_schoolID,String J_schoolName)  {
    	String result = "fail";
    	try{
	    	if(OnlyOneByPhone(J_phone)){
				Integer isPresident = Person.ISPRESIDENT_NO;
				if(StringUtils.isNotEmpty(J_add_role)){
					isPresident = Person.ISPRESIDENT_YES;
				}
	    		String uid = userService.addUser(J_name, J_phone,J_schoolID,J_schoolName,isPresident);
	        
	    		result = "success";
	    		//添加关系
	    		if(isPresident == Person.ISPRESIDENT_YES){
		        	if(J_add_role.equals("1")){
		        		UserroleRef ur = new UserroleRef();
		        		ur.setUserID(uid);
		        		ur.setRoleID(Role.ROLEID_SHEZHANG);
		        		ur.setUserRoleRefID( UUIDUtil.getUUID());
		        		userService.insertUserRole(ur);
		        		String societyid = UUIDUtil.getUUID();
		        		//添加社团
		        		if(StringUtils.isNotEmpty(J_schoolName)&&StringUtils.isNotEmpty(J_schoolID)&&StringUtils.isNotEmpty(J_societyName)){
		        			Society s = new Society();
		        			s.setSchoolID(J_schoolID);
		        			s.setSchoolName(J_schoolName);
		        			s.setSocietyName(J_societyName);
		        			s.setCreateTime(new Date().getTime());
		        			s.setPhoneNum(J_phone);
		        			s.setLevel(1);
							s.setShareURL(ConfigUtil.getProperty("society.share.url")+societyid);
//							s.setShareDESC(ConfigUtil.getProperty("society.share.desc"));
		        			User u = userService.getUserByID(uid);
		        			if(u!=null){
		        				s.setAdminID(u.getPersonID());
		        				Person p = userService.getPersonByID(u.getPersonID());
		        				if(p!=null){
		        					s.setAdminName(p.getName());
		        				}
		        			}
		        			s.setStatus(Society.STATUS_NORMAL);
		        			s.setSocietyID(societyid);
		        			societyService.addSociety(s);
		        		}
		        		//添加log日志
	    	    		Log log = new Log();
	    	    		log.setLogID(UUIDUtil.getUUID());
	    	    		log.setCreateTime(new Date().getTime());
	    	    		log.setLogAuthor(CurrentUser.getPhoneNum());
	    	    		log.setLogDesc(J_societyName+Log.MSG_ADMIN_ADD);
	    	    		log.setLogtype(Log.TYPE_SOCIETY_IMPORT);
	    	    		log.setActID(societyid);
	    	    		logService.add(log);
	    			
		        	}
	    		}
	    	
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return result;
    }

	/**
	 *
	 * @param model
	 * @param id
	 * @param status
	 * @return
	 */
    @RequestMapping("/pushST")
    @ResponseBody
    public String pushST(Model model,String id,String status)  {
    	String result = "success";
    	User user = new User();
    	user.setIsValid(Integer.parseInt(status));
    	user.setUserID(id);
    	try {
			
    		userService.updateUserSt(user);
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "fail";
		}
        return result;
    }

	/**
	 *
	 * @param model
	 * @param request
	 * @param id
	 * @param status
	 * @param file
	 * @return 改变状态
	 */
   @RequestMapping("/importEX")
   public String importEX(Model model,HttpServletRequest request,String id,String status,@RequestParam(value = "file", required = false) MultipartFile[] file)  {
   	String result = "/user/list.action";
   	String path   = request.getSession().getServletContext().getRealPath("/")+"/upload/";
	String fileName="";
	String newName="";
	String destName="";//目的文件
	String msg = "success";
  
			
   	 	for (int i = 0; i < file.length; i++) {
   	 		//读取excel并且上传--------------
   	    	fileName = file[i].getOriginalFilename();  
   	        System.out.println(path); 
   	        if(!StringUtils.isEmpty(fileName)){//新上传了才执行保存
   	        String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
   	        if("xls".equals(ext)||"xlsx".equals(ext)){
	   	        newName = String.valueOf(System.currentTimeMillis())+"."+ext;
	   	        File targetFile = new File(path, newName);  
	   	        if(!targetFile.exists()){  
	   	            targetFile.mkdirs();  
	   	        }  
	   	  
	   	        //保存  
	   	        try {  
	   	            file[i].transferTo(targetFile);  
	   	        } catch (Exception e) {  
	   	            e.printStackTrace();  
	   	        }  
	   	        	destName = path+"/"+newName;
	   	     //读取excel并且上传-------------- 
	   	        	
	   	        	//读取该文件解析插入
	   	        	List<UserTemp> list = ExcelReader.getListFromExcel(destName);
	   	        	for (UserTemp u: list) {
						String role = StringUtils.isNotEmpty( u.getRoleName())?u.getRoleName().trim():u.getRoleName();
						String name = StringUtils.isNotEmpty( u.getName())?u.getName().trim():u.getName();
						String phone= StringUtils.isNotEmpty( u.getPhoneNum())?u.getPhoneNum().trim():u.getPhoneNum();
						String schoolName = StringUtils.isNotEmpty( u.getSchoolName())?u.getSchoolName().trim():u.getSchoolName();
						String societyName = StringUtils.isNotEmpty( u.getSocietyName())?u.getSocietyName().trim():u.getSocietyName();
						String schoolID = "";
						try{
							List<School> schlist = schoolService.getSchoolList(schoolName);
							if(schlist!=null && schlist.size()>0){
								schoolID = schlist.get(0).getSchoolID();
							}
						}catch(Exception e){
							e.printStackTrace();
							result = "/user/list.action";
							msg    = "fail";
						}
						boolean flag = StringUtils.isNotEmpty(phone) &&  StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(role)  ;
						if(flag){//这一行有信息，不全部为空
							
							if(StringUtils.isNotEmpty(schoolName)&&StringUtils.isNotEmpty(societyName)){
								if(OnlyOneByPhone(phone)&&isMobile(phone)){//判断手机号码唯一性
									//添加社长
									if("社长".equals(role)){
										if(schoolService.isExist(schoolName)){
											userService.saveImpData(name, phone, schoolName, societyName, schoolID,CurrentUser.getPhoneNum(),ConfigUtil.getProperty("society.share.url"),ConfigUtil.getProperty("society.share.desc"));
										}
									}
								}

							}
						}
					}
	   	        	//读取该文件解析插入
	   	        }else{//文件格式不对
	   	        	msg    = "extinvald";
	   	        }
   	        }
   		}
   	   request.getSession().setAttribute("msg",msg );
			
       return "redirect:"+result;
   }

   /**
    * 根据手机号判断唯一性：false：【存在。不唯一】true：【不存在：唯一】
    * @param phone
    * @return
    */
   public boolean OnlyOneByPhone(String phone){
	   boolean a = false;
	   try {
		a = userService.PhoneFlag(phone, null);
	   } catch (Exception e) {
		// TODO: handle exception
	   }
	   
	   return a;
   }

	@RequestMapping(value = "/checkUserPhoneNum")
	@ResponseBody
	public Map<String,Object> checkUserByPhoneNum(String phoneNum) throws BaseException {
		return userService.checkPresidentInfo(phoneNum);
	}
	
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    
    
    /**
	 *  导出社团排行统计数据
	 * @param model
	 * @param request
	 * @return
	 * @throws BaseException
	 */
    @RequestMapping("/expRank")
    public void expRank(Model model,HttpServletRequest request,HttpServletResponse response) throws BaseException {
    try{
    	List<MixRankVO> list1 = userService.findMixRank();
    	List<SocRankVO> list2 = userService.findSocRank();
    	List<ActRankVO> list3 = userService.findActRank();
    	 
    	 HSSFWorkbook wb = ExcelReader.expRank(list1,list2,list3);    
         response.setContentType("application/vnd.ms-excel");    
         response.setHeader("Content-disposition", "attachment;filename=rank.xls");    
         OutputStream ouputStream = response.getOutputStream();    
         wb.write(ouputStream);    
         ouputStream.flush();    
         ouputStream.close();    
      }catch(Exception e){
    	  e.printStackTrace();
      }
    }
    
}
