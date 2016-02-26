package com.mai.controller.card;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mai.card.entity.CardStarPhoto;
import com.mai.card.service.CardStarPhotoService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.page.Page;
import com.mai.framework.page.PaginationParameters;
import com.mai.framework.utils.UUIDUtil;
import com.mai.util.ConfigUtil;
import com.mai.util.CurrentUser;
import com.mai.util.ImgCompress;
import com.mai.util.MD5Tools;
import com.mai.util.TimeUtils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

/**
 * 社团信息
 * Created by bruce on 2015/10/19.
 */

@Controller
@RequestMapping("/card")
public class CardController {
	private static Logger logger= Logger.getLogger(CardController.class);
    @Autowired
    private CardStarPhotoService cardService;
    @Autowired
    private UploadManager uploadService;
    
    public static final List<String> COLOR_LIST = new LinkedList<String>();
    static{
    	COLOR_LIST.add("#B29013FE");//紫色
    	COLOR_LIST.add("#B2E73D52");//红色
    	COLOR_LIST.add("#B24A90E2");//蓝色
    	COLOR_LIST.add("#B250E3C2");//绿色
    	COLOR_LIST.add("#B2FFA101");//黄色
    };
    


    /**
     * 上传
        * @param files
        * @param ad
        * @param request
        * @param model
        * @return
        */
   @RequestMapping("/upload")
   public String add(@RequestParam(required = false) MultipartFile audio,@RequestParam(required = false) MultipartFile photo,String cardStarName,String dx,HttpServletRequest request,Model model)  {
   	String result = "/ad/toAdd.action";
   	
   	try {
   		CardStarPhoto cs = new CardStarPhoto();
   		//文件处理
   		if(photo!=null){
               String fileName = photo.getOriginalFilename();
               if(!StringUtils.isBlank(fileName)){
                   String postfix = fileName.substring(fileName.lastIndexOf("."));
                   String _filename = MD5Tools.md5(fileName)+"-"+Calendar.getInstance().getTimeInMillis();
                   String newFilename = _filename + postfix;
                   Response response = uploadService.put(photo.getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket.card",null));
                   if(response.isOK()){
                        cs.setPhotoURL(newFilename);
                   }
               }
          }
   		 if(audio!=null){
            String fileName = audio.getOriginalFilename();
            if(!StringUtils.isBlank(fileName)){
                String postfix = fileName.substring(fileName.lastIndexOf("."));
                String _filename = MD5Tools.md5(fileName)+"-"+Calendar.getInstance().getTimeInMillis();
                String newFilename = _filename + postfix;
                Response response = uploadService.put(audio.getBytes(), newFilename, ConfigUtil.getUpToken("qiniu.bucket.card",null));
                if(response.isOK()){
                     cs.setAudioURL(newFilename);
                }
            }
        }
   		//宽高获取
   		Map<String,String> map = ImgCompress.getWidth(photo);
		String width = map.get("width");
		String height = map.get("height");
		//......
   		cs.setCardStarName(cardStarName);
   		cs.setDx(Integer.parseInt(dx));
   		cs.setDy(1);
   		cs.setIsDelete(0);
   		cs.setIsForbidden(0);
   		cs.setPersonID(CurrentUser.getCurrentPersonId());
   		cs.setWidth(Integer.parseInt(width));
   		cs.setHeight(Integer.parseInt(height));
   		cs.setOrderNum(99);
   		cs.setRatio(1.0);
   		cs.setCreateTime(TimeUtils.getCurrentTiem());
   		cs.setCardStarPhotoID(UUIDUtil.getUUID());
   		cs.setColor(COLOR_LIST.get(getRandomOne(COLOR_LIST)));
   		cardService.add(cs);
       	result = "/card/list.action";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	
       return "redirect:"+result;
   }




    /**
     * 明星管理
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/list")
    public String list(Model model,String status,HttpServletRequest request,String startTime_,String endTime_,String keyword) throws BaseException {
    	String domain = ConfigUtil.getProperty("qiniu.qiniuDomainURL.card");
    	
    	String result = "/card/card-list";
    	String wheresql  = " 1=1 and isDelete=0 ";
    	//这里拼接分页信息---
  	  int pageNumber = 1;
        if(!StringUtils.isBlank(request.getParameter("page"))){
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        if(StringUtils.isNotEmpty(startTime_)){
    		String startTime =startTime_+ " 00:00:00";
    		long st = TimeUtils.stringToMillis(startTime);
    		wheresql+=" and createTime >= "+st;
    	}
    	if(StringUtils.isNotEmpty(endTime_)){
    		String endTime = endTime_ + " 23:59:59";
    		long en = TimeUtils.stringToMillis(endTime);
    		wheresql+=" and createTime <= "+en;
    	}
    	
    	if(StringUtils.isNotEmpty(keyword)){
    		wheresql+=" and cardStarName like '%"+keyword+"%'";
    	}
        PaginationParameters paginationParameters = new PaginationParameters(pageNumber);
        
        Page<CardStarPhoto> pageObj = this.cardService.findAllByPage(paginationParameters, wheresql);
        //处理标签数----------
        List<CardStarPhoto> list =  pageObj.getDataList();
   	    model.addAttribute("list", list);
        model.addAttribute("page", pageObj.getCurrentPageNumber());
        model.addAttribute("totalPageSize",pageObj.getTotalPageSize());
        model.addAttribute("totalsize", pageObj.getTotalSize());
        model.addAttribute("pageMaxSize",pageObj.getPageMaxSize());
        model.addAttribute("startTime_", startTime_);
        model.addAttribute("endTime_", endTime_);
        model.addAttribute("keyword", keyword);
        model.addAttribute("domain", domain);
        
    	
    	
        return result;
    }
    
    
    /**
     * 禁用
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/ban")
    @ResponseBody
    public String ban(HttpServletRequest request,String cardStarPhotoID,String isForbidden) throws BaseException {
    	String result = "";
    	try{
	    	if(StringUtils.isNotEmpty(cardStarPhotoID)){
	    		CardStarPhoto model =  cardService.findByID(cardStarPhotoID);
	    		if(model!=null){
		    		if(isForbidden.equals("1")){
		    			model.setIsForbidden(0);
		    		}else if(isForbidden.equals("0")){
		    			model.setIsForbidden(1);
		    		}
		    		cardService.update(model);
		    		result = "success";
	    		}
	    	}
    	}catch(Exception e){
    		result = "ex";
    		e.printStackTrace();
    	}
        return result;
    }
    
    
    /**
     * 删除
     *
     * @param model
     * @param status
     * @return
     * @throws BaseException
     */
    @RequestMapping("/remove")
    @ResponseBody
    public String remove(HttpServletRequest request,String cardStarPhotoID) throws BaseException {
    	String result = "";
    	try{
	    	if(StringUtils.isNotEmpty(cardStarPhotoID)){
	    		CardStarPhoto model =  cardService.findByID(cardStarPhotoID);
	    		if(model!=null){
		    		model.setIsDelete(1);
		    		cardService.update(model);
		    		result = "success";
	    		}
	    	}
    	}catch(Exception e){
    		result = "ex";
    		e.printStackTrace();
    	}
    	
        return result;
    }
    
    
    /**
	 * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
	 * @return
	 */
	public static Integer getRandomOne(List<?> list){
		
		
		Random ramdom =  new Random();
		int number = -1;
		int max = list.size();
		
		//size 为  10 ，取得类似0-9的区间数
		number = Math.abs(ramdom.nextInt() % max  );
		
		return number;
    
	}
    
}
