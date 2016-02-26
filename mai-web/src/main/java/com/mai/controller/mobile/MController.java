package com.mai.controller.mobile;

import com.mai.app.dto.AppMessageInfoDTO;
import com.mai.card.entity.PersonCard;
import com.mai.card.service.PersonCardService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.utils.JsonHelper;
import com.mai.micromessage.service.MicroMessageService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * MController
 *
 * @author Yao Jinwei
 * @date 2015/12/23
 */
@Controller
@RequestMapping("/m")
public class MController {
    private static Logger logger= Logger.getLogger(MController.class);
    @Autowired
    private PersonCardService personCardService;
    @Autowired
    private MicroMessageService microMessageService;
    /**
     * 明星H5页面
     * @param id
     * @return
     * @throws com.mai.framework.exception.BaseException
     */
    @RequestMapping("/card")
    public String card(Model model,String id) throws BaseException {
        if(com.mai.framework.utils.StringUtils.isBlank(id)){
            return "/appweb/error";
        }
        this.personCardService.transIncreaseViewNum(id);
        PersonCard personCard = this.personCardService.findByID(id);
//        if(null == personCard){
//            model.addAttribute("cardStarPhoto",personCard);
//            AppMessageInfoDTO dto = new AppMessageInfoDTO();
//            dto.setPicID("1");
//            dto.setTitle("么么哒啦");
//            dto.setDescription("在年底贺岁档探班中自爆藏了十年的秘密");
//            dto.setPicID("222");
//        }
        model.addAttribute("appParamJson", JsonHelper.object2json(personCardService.getAppMessageInfoDTO(personCard)));
        model.addAttribute("personCard",personCard);
        return "/card/mobile";
    }

    /**
     * 页面访问计数
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/visitPage")
    @ResponseBody
    public void visitPage(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        String pageID = request.getParameter("pageID");
        String pageType = request.getParameter("pageType");
        boolean isPreview = Boolean.parseBoolean(request.getParameter("isPreview"));
        this.personCardService.transIncreaseViewNum(pageID);
    }

    /**
     * 页面转发 没有返回真实的转发数 需要在每次数据库更新后，取出数据库值 之后的计算中加上缓存值
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deliverPage")
    @ResponseBody
    public void deliverPage(HttpServletRequest request, HttpServletResponse response) throws BaseException {
        String pageID = request.getParameter("pageID");
        String pageType = request.getParameter("pageType");
        String targetType = request.getParameter("targetType");
        boolean isPreview = Boolean.getBoolean(request.getParameter("isPreview"));
        this.personCardService.transIncreaseShareNum(pageID,targetType);
    }


    /**
     *
     * @param model
     * @return
     * @throws BaseException
     */
    @RequestMapping("/getMicroMessengerInfo")
    @ResponseBody
    public Map<String,Object> getMicroMessengerInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws BaseException{
        Map<String,Object> result = new HashMap<String, Object>();
        String url = request.getParameter("url");
        long mtime = System.currentTimeMillis()/1000;
        String timestamp = Long.toString(mtime);//System.currentTimeMillis() + "";
        String nonceStr = RandomStringUtils.random(8, "123456789"); // 8位随机数
        String jsapi_ticket="";//="sM4AOVdWfPE4DxkXGEs8VH_k_7TfipBZNEW-qAV9Yjn111-xwI8p-Dh0i6RiwWi6Dgc8yYwqu1e578Zfk6k_Mg";//"sM4AOVdWfPE4DxkXGEs8VH_k_7TfipBZNEW-qAV9YjmvfjqVw0-53Ko9zQ-4YFoHhOws_AmGL3strDUNb33vyA";
        String signature="";
        try {
            jsapi_ticket = microMessageService.getMicroMessageAccessToken();
            signature = microMessageService.getMicroMessageSignature(timestamp, nonceStr, jsapi_ticket, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("jsapi_ticket", jsapi_ticket);
        result.put("appid", microMessageService.getMicroMessageAppid());
        result.put("nonceStr", nonceStr);
        result.put("timestamp", timestamp);
        result.put("signature", signature);
        logger.info("jsapi_ticket:"+jsapi_ticket);
        logger.info("nonceStr:"+nonceStr);
        logger.info("timestamp:"+timestamp);
        logger.info("url:"+url);
        return result;
    }
}
