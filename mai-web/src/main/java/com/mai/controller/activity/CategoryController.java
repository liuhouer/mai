package com.mai.controller.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.activity.entity.Category;
import com.mai.activity.service.CategoryService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getCategoryList")
    @ResponseBody
    public List<Category> getCategoryList() throws BaseException {
        return categoryService.getCategoryList();
    }
}
