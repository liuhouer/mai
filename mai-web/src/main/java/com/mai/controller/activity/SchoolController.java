package com.mai.controller.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.activity.entity.School;
import com.mai.activity.service.SchoolService;
import com.mai.framework.exception.BaseException;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value = "getSchoolList")
    @ResponseBody
    @ApiOperation(value = "获取学校列表", httpMethod = "POST", response = List.class, notes = "getSchoolList")
    @ApiParam(value = "schoolName", name = "schoolName", required = false)
    public List<School> getSchoolList(String schoolName) throws BaseException {
        return schoolService.getSchoolList(schoolName);
    }
}
