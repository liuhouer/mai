package com.mai.controller.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.activity.entity.Tag;
import com.mai.activity.service.TagService;
import com.mai.framework.exception.BaseException;

/**
 * Created by Administrator on 2015/9/7.
 */
@Controller
@RequestMapping(value = "tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "getTagList")
    @ResponseBody
    public List<Tag> getTagList() throws BaseException {
        return tagService.getTagList();
    }
}
