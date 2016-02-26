package com.mai.controller.society;

import javax.servlet.http.HttpServletRequest;

import com.mai.framework.utils.UUIDUtil;
import com.mai.society.entity.*;
import com.mai.society.service.SocietyCategoryService;
import com.mai.society.service.SocietyTagService;
import com.mai.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.society.service.SocietyService;
import com.mai.util.CurrentUser;
import com.mai.vo.SocietyRunningVO;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by denghao on 15/10/21.
 */
@Controller
@RequestMapping("/societyRunning")
public class SocietyRunningController {

    @Autowired
    private SocietyService societyService;

    @Autowired
    private SocietyCategoryService societyCategoryService;

    @Autowired
    private SocietyTagService societyTagService;
    
    
    /**
     * 修改社团名称bybruce2015-11-6 11:55:07
     * @param request
     * @param J_sid
     * @param J_label_name
     * @param page
     * @return msg
     */
    @RequestMapping(value = "/mdName")
    public String mdName(HttpServletRequest request,String J_sid,String J_label_name,String page) {
    	String msg = "/societyRunning/SocietyList.action";
    	if(StringUtils.isNotEmpty(page)){
    		msg += "?page="+page;
    	}
    	try {
    		int a = societyService.mdName(J_sid,J_label_name);

    	} catch (Exception e) {
			// TODO: handle exception
		    e.printStackTrace();
		}
		return "redirect:"+msg;
    	
    }

    @RequestMapping(value = "/SocietyList")
    public String SocietyList(Model model, HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" status = 1 ");
        if (!StringUtils.isBlank(request.getParameter("sName"))) {
            mywhere.append(" and societyName like '%" + request.getParameter("sName") + "%'");
            model.addAttribute("sName", request.getParameter("sName"));
        }

        int pageNumber = 1;
        if (!StringUtils.isBlank(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<SocietyRunningVO> pageObj = this.societyService.findSocietyRunningByPage(mywhere.toString(), paginationParameters);

        model.addAttribute("srlist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize", pageObj.getPageMaxSize());
        return "/running/societyList";
    }

    @RequestMapping(value = "/SocietyRecommendNo")
    public String SocietyRecommendNo(HttpServletRequest request) throws BaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
            map.put("recommendNo", request.getParameter("newvalue"));
        } else {
            map.put("recommendNo", 0);
        }
        map.put("societyID", request.getParameter("sid"));
        this.societyService.updateRecommendNoByID(map);
        return "redirect:/societyRunning/SocietyList.action?page="+request.getParameter("jump_page");
    }

    @RequestMapping(value = "/SocietySysFollow")
    public String SocietySysFollow(HttpServletRequest request) throws BaseException {
        SystemSocietyFollow systemSocietyFollow = societyService.findSystemSocietyFollowBySID(request.getParameter("sid"));
        if (systemSocietyFollow != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                systemSocietyFollow.setSocietyFollowNum(Integer.valueOf(request.getParameter("newvalue")));
            } else {
                systemSocietyFollow.setSocietyFollowNum(0);
            }
            systemSocietyFollow.setCreateTime(Calendar.getInstance().getTimeInMillis());
            systemSocietyFollow.setPersonID(CurrentUser.getCurrentPersonId());
            this.societyService.updateSystemSocietyFollow(systemSocietyFollow);
        } else {
            systemSocietyFollow = new SystemSocietyFollow();
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                systemSocietyFollow.setSocietyFollowNum(Integer.valueOf(request.getParameter("newvalue")));
            } else {
                systemSocietyFollow.setSocietyFollowNum(0);
            }
            systemSocietyFollow.setSystemSocietyFollowID(UUIDUtil.getUUID());
            systemSocietyFollow.setCreateTime(Calendar.getInstance().getTimeInMillis());
            systemSocietyFollow.setPersonID(CurrentUser.getCurrentPersonId());
            systemSocietyFollow.setSocietyID(request.getParameter("sid"));
            this.societyService.insertSystemSocietyFollow(systemSocietyFollow);
        }
        return "redirect:/societyRunning/SocietyList.action?page="+request.getParameter("jump_page");
    }

    @RequestMapping(value = "/SocietySysPraise")
    @ResponseBody
    public String SocietySysPraise(HttpServletRequest request) throws BaseException {
        String sid = request.getParameter("sid");
        SystemSocietyPraise systemSocietyPraise = societyService.findSystemSocietyPraiseBySID(sid);
        if (systemSocietyPraise != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                systemSocietyPraise.setSocietyPraiseNum(Integer.valueOf(request.getParameter("newvalue")));
            } else {
                systemSocietyPraise.setSocietyPraiseNum(0);
            }
            systemSocietyPraise.setCreateTime(Calendar.getInstance().getTimeInMillis());
            systemSocietyPraise.setPersonID(CurrentUser.getCurrentPersonId());
            this.societyService.updateSystemSocietyPraise(systemSocietyPraise);
        } else {
            systemSocietyPraise = new SystemSocietyPraise();
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                systemSocietyPraise.setSocietyPraiseNum(Integer.valueOf(request.getParameter("newvalue")));
            } else {
                systemSocietyPraise.setSocietyPraiseNum(0);
            }
            systemSocietyPraise.setSystemSocietyPraiseID(UUIDUtil.getUUID());
            systemSocietyPraise.setCreateTime(Calendar.getInstance().getTimeInMillis());
            systemSocietyPraise.setPersonID(CurrentUser.getCurrentPersonId());
            systemSocietyPraise.setSocietyID(sid);
            this.societyService.insertSystemSocietyPraise(systemSocietyPraise);
        }
//        return "redirect:/societyRunning/SocietyList.action?page="+request.getParameter("jump_page");
        return "{\"errorcode\":0,\"sid\":\""+sid+"\",\"value\":"+request.getParameter("newvalue")+"}";
    }






    @RequestMapping(value = "/SocietyCategoryList")
    public String SocietyCategoryList(Model model) throws BaseException {
        List<SocietyCategory> societyCategoryList = societyCategoryService.findSocietyCategoryInfoList();
        String[] imageUrls = ConfigUtil.getTypeImages();
        model.addAttribute("imageUrls", imageUrls);
        model.addAttribute("sclist", societyCategoryList);
        return "/running/societyCategoryList";
    }

    @RequestMapping(value = "/updateSocietyCategory")
    public String updateSocietyCategory(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        SocietyCategory societyCategory = societyCategoryService.findSocietyCategoryByID(request.getParameter("cid"));
        if (societyCategory != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                societyCategory.setCategoryName(request.getParameter("newvalue"));
                societyCategoryService.updateSocietyCategory(societyCategory);
            }
        }
        return "redirect:/societyRunning/SocietyCategoryList.action";
    }

    @RequestMapping(value = "/delSocietyCategory")
    @ResponseBody
    public Boolean delSocietyCategory(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        SocietyCategory societyCategory = societyCategoryService.findSocietyCategoryByID(request.getParameter("cid"));
        if (societyCategory == null || societyCategory.getCategoryCount()==0) {
            if(societyCategoryService.deleteSocietyCategoryByCID(request.getParameter("cid"))>0){
                return true;
            }else{
                return false;
            }
        }else{
           return false;
        }
    }

    @RequestMapping("/addSocietyCategory")
    public String addSocietyCategory(Model model, HttpServletRequest request) throws BaseException {
        String[] categoryNames = request.getParameterValues("newvalues");
        if(categoryNames!=null && categoryNames.length>0){
            List<SocietyCategory> sclist = new ArrayList<SocietyCategory>();
            SocietyCategory societyCategory = null;
            for(String categoryName : categoryNames){
                if (!StringUtils.isBlank(categoryName)){
                    societyCategory = new SocietyCategory();
                    societyCategory.setCategoryID(UUIDUtil.getUUID());
                    societyCategory.setCategoryName(categoryName);
                    societyCategory.setCategoryOrderNum(99);
                    sclist.add(societyCategory);
                }
            }
            societyCategoryService.insertBatch(sclist);
        }
        return "redirect:/societyRunning/SocietyCategoryList.action";
    }

    @RequestMapping(value = "/SocietyCategoryDetail")
    public String SocietyCategoryDetail(Model model,HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" societyCategoryID="+request.getParameter("scid"));
        if (!StringUtils.isBlank(request.getParameter("sName"))) {
            mywhere.append(" and societyName like '%" + request.getParameter("sName") + "%'");
            model.addAttribute("sName", request.getParameter("sName"));
        }
        if (!StringUtils.isBlank(request.getParameter("sStatus"))) {
            mywhere.append(" and status = " + request.getParameter("sStatus"));
            model.addAttribute("sStatus", request.getParameter("sStatus"));
        }


        int pageNumber = 1;
        if (!StringUtils.isBlank(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<Society> pageObj = this.societyCategoryService.findSocietyCategoryDetailByPage(mywhere.toString(), paginationParameters);

        model.addAttribute("sclist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize", pageObj.getPageMaxSize());
        model.addAttribute("scid", request.getParameter("scid"));
        return "/running/societyCategoryDetail";
    }


    @RequestMapping(value = "/SocietyTagList")
    public String SocietyTagList(Model model) throws BaseException {
        List<SocietyTag> societyTagList = societyTagService.findSocietyTagInfoList();
        model.addAttribute("stlist", societyTagList);
        return "/running/societyTagList";
    }

    @RequestMapping(value = "/updateSocietyTag")
    public String updateSocietyTag(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        SocietyTag societyTag = societyTagService.findSocietyTagByID(request.getParameter("tid"));
        if (societyTag != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                societyTag.setTagName(request.getParameter("newvalue"));
                societyTagService.updateSocietyTag(societyTag);
            }
        }
        return "redirect:/societyRunning/SocietyTagList.action";
    }

    @RequestMapping(value = "/delSocietyTag")
    @ResponseBody
    public Boolean delSocietyTag(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        SocietyTag societyTag = societyTagService.findSocietyTagByID(request.getParameter("tid"));
        if (societyTag == null || societyTag.getScoietyTagCount()==0) {
            if(societyTagService.deleteSocietyTagByTID(request.getParameter("tid"))>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @RequestMapping("/addSocietyTag")
    public String addSocietyTag(Model model, HttpServletRequest request) throws BaseException {
        String[] tagNames = request.getParameterValues("newvalues");
        if(tagNames!=null && tagNames.length>0){
            List<SocietyTag> stlist = new ArrayList<SocietyTag>();
            SocietyTag societyTag = null;
            for(String tagName : tagNames){
                if (!StringUtils.isBlank(tagName)){
                    societyTag = new SocietyTag();
                    societyTag.setTagID(UUIDUtil.getUUID());
                    societyTag.setTagName(tagName);
                    societyTag.setOrderNum(99);
                    stlist.add(societyTag);
                }
            }
            societyTagService.insertBatch(stlist);
        }
        return "redirect:/societyRunning/SocietyTagList.action";
    }

    @RequestMapping(value = "/SocietyTagDetail")
    public String SocietyTagDetail(Model model,HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" societyTagID = "+request.getParameter("stid"));
        if (!StringUtils.isBlank(request.getParameter("sName"))) {
            mywhere.append(" and societyName like '%" + request.getParameter("sName") + "%'");
            model.addAttribute("sName", request.getParameter("sName"));
        }
        if (!StringUtils.isBlank(request.getParameter("sStatus"))) {
            mywhere.append(" and status = " + request.getParameter("sStatus"));
            model.addAttribute("sStatus", request.getParameter("sStatus"));
        }

        int pageNumber = 1;
        if (!StringUtils.isBlank(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<Society> pageObj = this.societyTagService.findSocietyTagDetailByPage(mywhere.toString(), paginationParameters);

        model.addAttribute("stlist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize", pageObj.getPageMaxSize());
        model.addAttribute("stid", request.getParameter("stid"));
        return "/running/societyTagDetail";
    }

    @RequestMapping(value = "/SocietyCategoryImg")
//    @ResponseBody
    public String SocietyCategoryImg(HttpServletRequest request) throws BaseException {
        String cid = request.getParameter("cid");
        String cimgurl = request.getParameter("cimgurl");
        SocietyCategory societyCategory = societyCategoryService.findSocietyCategoryByID(cid);
        if (societyCategory != null) {
            if (!StringUtils.isBlank(request.getParameter("cimgurl"))) {
                societyCategory.setImageURL(request.getParameter("cimgurl"));
                societyCategoryService.updateSocietyCategory(societyCategory);
            }
        }
        return "redirect:/societyRunning/SocietyCategoryList.action";


//        return "{\"errorcode\":0,\"cid\":\""+cid+"\",\"value\":"+request.getParameter("cimgurl")+"}";
    }
}
