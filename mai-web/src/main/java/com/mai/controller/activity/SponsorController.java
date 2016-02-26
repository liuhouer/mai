package com.mai.controller.activity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.activity.entity.Activity;
import com.mai.activity.entity.Sponsor;
import com.mai.activity.service.ActivityService;
import com.mai.activity.service.SponsorService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.user.entity.Role;
import com.mai.user.entity.User;
import com.mai.util.CurrentUser;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by denghao on 15/10/9.
 */
@Controller
@RequestMapping(value = "/sponsor")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/SponsorList")
    public String SponsorList(Model model,HttpServletRequest request) throws BaseException {
        Map<String,String> params = new HashMap<String,String>();
        params.put("sponsorStatus", String.valueOf(Sponsor.STATUS_NORMAL));
        if(CurrentUser.hasRole(Role.ROLENAME_SPONSOR)){
            params.put("adminID",CurrentUser.getCurrentPersonId());
        }
        int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        Page<Sponsor> pageObj = this.sponsorService.findSponsorList(params, paginationParameters);

        model.addAttribute("sponsorList", pageObj.getDataList());
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
        return "/sponsor/sponsorList";
    }

    @RequestMapping(value = "/newSponsor")
    public String addActivity(Model model,HttpServletRequest request) throws BaseException {
        Sponsor sponsor = new Sponsor();
        String actTitle = "";
        if(!StringUtils.isBlank(request.getParameter("spid"))){
            Map<String,String> params = new HashMap<String,String>();
            params.put("sponsorID",request.getParameter("spid"));
            sponsor = this.sponsorService.findSponsorByID(params);
            if(sponsor!=null){
                Activity activity = this.activityService.findActivityByID(sponsor.getActivityID());
                if(activity!=null){
                    actTitle = activity.getActivityTitle();
                }
            }
        }else{
            sponsor.setSponsorStatus(Sponsor.STATUS_NORMAL);
        }
        model.addAttribute("sponsor", sponsor);
        model.addAttribute("actTitle",actTitle);
        return "/sponsor/sponsor";
    }

    @RequestMapping(value = "/deleteSponsor")
    public String deleteSponsor(HttpServletRequest request) throws BaseException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("sponsorID",request.getParameter("spid"));
        map.put("sponsorStatus", String.valueOf(Sponsor.STATUS_CLOSE));
        this.sponsorService.updateSponsorStatusByID(map);
        return "redirect:/sponsor/SponsorList.action";
    }


    @RequestMapping(value = "/saveOrUpdateSponsor")
    @ResponseBody
    public String saveOrUpdateSponsor(Sponsor sponsor,HttpServletRequest request,Model model) throws BaseException, ParseException, IOException {

        Calendar ca = Calendar.getInstance();
        sponsor.setUpdateTime(ca.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = CurrentUser.getCurrentUser();
        sponsor.setAdminID(user.getPersonID());
        sponsor.setAdminName(user.getPersonName());

        if(sponsor.getShowAppTime().length() == 16){
            sponsor.setShowAppTime(sponsor.getShowAppTime() + ":00");
        }
        ca.setTimeInMillis(sdf.parse(sponsor.getShowAppTime()).getTime());
        sponsor.setAppTime(ca.getTimeInMillis());

        if(StringUtils.isBlank(sponsor.getSponsorID())){
            sponsor.setSponsorID(UUIDUtil.getUUID());
            sponsor.setCreateTime(sponsor.getUpdateTime());
            sponsorService.addSponsor(sponsor);
        }else{
            sponsorService.updateSponsor(sponsor);
        }
        return "/sponsor/SponsorList.action";
    }

}
