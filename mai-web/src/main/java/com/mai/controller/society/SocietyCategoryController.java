package com.mai.controller.society;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyCategory;
import com.mai.society.service.SocietyCategoryService;

/**
 * Created by denghao on 15/10/5.
 */
@Controller
@RequestMapping(value = "/scategory")
public class SocietyCategoryController {
    @Autowired
    private SocietyCategoryService societyCategoryService;

    @RequestMapping(value = "/getSCategoryList")
    @ResponseBody
    public List<SocietyCategory> getSCategoryList() throws BaseException {
        return societyCategoryService.getSocietyCategoryList();
    }
}
