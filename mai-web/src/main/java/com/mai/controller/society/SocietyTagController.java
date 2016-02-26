package com.mai.controller.society;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.framework.exception.BaseException;
import com.mai.society.entity.SocietyTag;
import com.mai.society.service.SocietyTagService;

/**
 * Created by denghao on 15/10/5.
 */
@Controller
@RequestMapping("/stag")
public class SocietyTagController {

    @Autowired
    private SocietyTagService societyTagService;

    @RequestMapping(value = "getSTagList")
    @ResponseBody
    public List<SocietyTag> getSTagList() throws BaseException {
        return societyTagService.findSTagList();
    }
}
