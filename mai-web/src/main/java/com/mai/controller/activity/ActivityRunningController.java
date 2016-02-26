package com.mai.controller.activity;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.Category;
import com.mai.activity.entity.SystemActivityFollow;
import com.mai.activity.entity.Tag;
import com.mai.activity.service.ActivityService;
import com.mai.activity.service.CategoryService;
import com.mai.activity.service.TagService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.util.ConfigUtil;
import com.mai.util.CurrentUser;
import com.mai.vo.ActivityRunningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by denghao on 15/10/24.
 */
@Controller
@RequestMapping(value = "/activityRunning")
public class ActivityRunningController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/ActivityList")
    public String SocietyList(Model model, HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" activityStatus = 1 ");
        if (!StringUtils.isBlank(request.getParameter("sName"))) {
            mywhere.append(" and activityTitle like '%" + request.getParameter("sName") + "%'");
            model.addAttribute("sName", request.getParameter("sName"));
        }

        int pageNumber = 1;
        if (!StringUtils.isBlank(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<ActivityRunningVO> pageObj = this.activityService.findActivityRunningByPage(mywhere.toString(), paginationParameters);

        model.addAttribute("arlist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize", pageObj.getPageMaxSize());
        return "/running/activityList";
    }

    @RequestMapping(value = "/ActivityRecommendNo")
    public String SocietyRecommendNo(HttpServletRequest request) throws BaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
            map.put("recommendNo", request.getParameter("newvalue"));
        } else {
            map.put("recommendNo", 0);
        }
        map.put("activityID", request.getParameter("aid"));
        this.activityService.updateRecommendNoByID(map);
        return "redirect:/activityRunning/ActivityList.action?page="+request.getParameter("jump_page");
    }

    @RequestMapping(value = "/ActivitySysFollow")
    @ResponseBody
    public String SocietySysFollow(HttpServletRequest request) throws BaseException {
        String aid = request.getParameter("aid");
        SystemActivityFollow systemActivityFollow = activityService.findSystemActivityFollowByAID(aid);
        if (systemActivityFollow != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                systemActivityFollow.setActivityFollowNum(Integer.valueOf(request.getParameter("newvalue")));
            } else {
                systemActivityFollow.setActivityFollowNum(0);
            }
            systemActivityFollow.setCreateTime(Calendar.getInstance().getTimeInMillis());
            systemActivityFollow.setPersonID(CurrentUser.getCurrentPersonId());
            this.activityService.updateSystemActivityFollow(systemActivityFollow);
        } else {
            systemActivityFollow = new SystemActivityFollow();
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                systemActivityFollow.setActivityFollowNum(Integer.valueOf(request.getParameter("newvalue")));
            } else {
                systemActivityFollow.setActivityFollowNum(0);
            }
            systemActivityFollow.setSystemActivityFollowID(UUIDUtil.getUUID());
            systemActivityFollow.setCreateTime(Calendar.getInstance().getTimeInMillis());
            systemActivityFollow.setPersonID(CurrentUser.getCurrentPersonId());
            systemActivityFollow.setActivityID(aid);
            this.activityService.insertSystemActivityFollow(systemActivityFollow);
        }
//        return "redirect:/activityRunning/ActivityList.action";
        return "{\"errorcode\":0,\"aid\":\""+aid+"\",\"value\":"+request.getParameter("newvalue")+"}";
    }



    @RequestMapping(value = "/ActivityCategoryList")
    public String ActivityCategoryList(Model model) throws BaseException {
        List<Category> categoryList = categoryService.findCategoryInfoList();
        String[] imageUrls = ConfigUtil.getTypeImages();
        model.addAttribute("imageUrls", imageUrls);
        model.addAttribute("clist", categoryList);
        return "/running/activityCategoryList";
    }

    @RequestMapping(value = "/updateActivityCategory")
    public String updateActivityCategory(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        Category category = categoryService.findCategoryByID(request.getParameter("cid"));
        if (category != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                category.setCategoryName(request.getParameter("newvalue"));
                categoryService.updateCategory(category);
            }
        }
        return "redirect:/activityRunning/ActivityCategoryList.action";
    }

    @RequestMapping(value = "/delActivityCategory")
    @ResponseBody
    public Boolean delActivityCategory(HttpServletRequest request) throws BaseException{
        Category category = categoryService.findCategoryByID(request.getParameter("cid"));
        if (category == null || category.getCategoryCount()==0) {
            if(categoryService.deleteCategoryByCID(request.getParameter("cid"))>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @RequestMapping("/addActivityCategory")
    public String addActivityCategory(Model model, HttpServletRequest request) throws BaseException {
        String[] categoryNames = request.getParameterValues("newvalues");
        if(categoryNames!=null && categoryNames.length>0){
            List<Category> clist = new ArrayList<Category>();
            Category category = null;
            for(String categoryName : categoryNames){
                if (!StringUtils.isBlank(categoryName)){
                    category = new Category();
                    category.setCategoryID(UUIDUtil.getUUID());
                    category.setCategoryName(categoryName);
                    category.setCategoryOrderNum(99);
                    clist.add(category);
                }
            }
            categoryService.insertBatch(clist);
        }
        return "redirect:/activityRunning/ActivityCategoryList.action";
    }

    @RequestMapping(value = "/ActivityCategoryDetail")
    public String ActivityCategoryDetail(Model model,HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" categoryID = "+request.getParameter("cid"));
        if (!StringUtils.isBlank(request.getParameter("sName"))) {
            mywhere.append(" and activityTitle like '%" + request.getParameter("sName") + "%'");
            model.addAttribute("sName", request.getParameter("sName"));
        }
        if (!StringUtils.isBlank(request.getParameter("aStatus"))) {
            mywhere.append(" and activityStatus = " + request.getParameter("aStatus"));
            model.addAttribute("aStatus", request.getParameter("aStatus"));
        }


        int pageNumber = 1;
        if (!StringUtils.isBlank(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<Activity> pageObj = this.categoryService.findCategoryDetailByPage(mywhere.toString(), paginationParameters);

        model.addAttribute("clist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize", pageObj.getPageMaxSize());
        model.addAttribute("cid", request.getParameter("cid"));
        return "/running/activityCategoryDetail";
    }


    @RequestMapping(value = "/ActivityTagList")
    public String ActivityTagList(Model model) throws BaseException {
        List<Tag> tList = tagService.findTagInfoList();
        model.addAttribute("tlist", tList);
        return "/running/activityTagList";
    }

    @RequestMapping(value = "/updateActivityTag")
    public String updateActivityTag(HttpServletRequest request) throws BaseException {
        Tag tag = tagService.findTagByID(request.getParameter("tid"));
        if (tag != null) {
            if (!StringUtils.isBlank(request.getParameter("newvalue"))) {
                tag.setTagName(request.getParameter("newvalue"));
                tagService.updateTag(tag);
            }
        }
        return "redirect:/activityRunning/ActivityTagList.action";
    }

    @RequestMapping(value = "/delActivityTag")
    @ResponseBody
    public Boolean delActivityTag(HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        Tag tag = tagService.findTagByID(request.getParameter("tid"));
        if (tag == null || tag.getTagCount()==0) {
            if(tagService.deleteTagByTID(request.getParameter("tid"))>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @RequestMapping("/addActivityTag")
    public String addActivityTag(Model model, HttpServletRequest request) throws BaseException {
        String[] tagNames = request.getParameterValues("newvalues");
        if(tagNames!=null && tagNames.length>0){
            List<Tag> tlist = new ArrayList<Tag>();
            Tag tag = null;
            for(String tagName : tagNames){
                if (!StringUtils.isBlank(tagName)){
                    tag = new Tag();
                    tag.setTagID(UUIDUtil.getUUID());
                    tag.setTagName(tagName);
                    tag.setOrderNum(99);
                    tlist.add(tag);
                }
            }
            tagService.insertBatch(tlist);
        }
        return "redirect:/activityRunning/ActivityTagList.action";
    }

    @RequestMapping(value = "/ActivityTagDetail")
    public String ActivityTagDetail(Model model,HttpServletRequest request) throws BaseException {
        StringBuffer mywhere = new StringBuffer(" tagID = "+request.getParameter("tid"));
        if (!StringUtils.isBlank(request.getParameter("sName"))) {
            mywhere.append(" and activityTitle like '%" + request.getParameter("sName") + "%'");
            model.addAttribute("sName", request.getParameter("sName"));
        }
        if (!StringUtils.isBlank(request.getParameter("aStatus"))) {
            mywhere.append(" and activityStatus = " + request.getParameter("aStatus"));
            model.addAttribute("aStatus", request.getParameter("aStatus"));
        }

        int pageNumber = 1;
        if (!StringUtils.isBlank(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<Activity> pageObj = this.tagService.findTagDetailByPage(mywhere.toString(), paginationParameters);

        model.addAttribute("tlist", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize", pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize", pageObj.getPageMaxSize());
        model.addAttribute("tid", request.getParameter("tid"));
        return "/running/activityTagDetail";
    }

    @RequestMapping(value = "/ActivityCategoryImg")
//    @ResponseBody
    public String ActivityCategoryImg(HttpServletRequest request) throws BaseException {
        String cid = request.getParameter("cid");
        String cimgurl = request.getParameter("cimgurl");
        Category category = categoryService.findCategoryByID(cid);
        if (category != null) {
            if (!StringUtils.isBlank(request.getParameter("cimgurl"))) {
                category.setImageURL(request.getParameter("cimgurl"));
                categoryService.updateCategory(category);
            }
        }
        return "redirect:/activityRunning/ActivityCategoryList.action";
//        return "{\"errorcode\":0,\"cid\":\""+cid+"\",\"value\":"+request.getParameter("cimgurl")+"}";
    }
}
