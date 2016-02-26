package com.mai.controller.activity;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mai.card.entity.PersonCard;
import com.mai.card.service.PersonCardService;
import com.mai.framework.exception.BaseException;
import com.mai.framework.utils.UUIDUtil;
import com.mai.lottery.Lottery;
import com.mai.lottery.service.LotteryService;
import com.mai.user.entity.Person;
import com.mai.user.entity.User;
import com.mai.user.service.PersonService;
import com.mai.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by denghao on 15/12/23.
 */
@Controller
@RequestMapping(value = "/game")
public class GameController {

    private static Logger logger= Logger.getLogger(GameController.class);
    @Autowired
    private PersonCardService personCardService;
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;


    @RequestMapping("/egg")
    public String egg(HttpServletRequest request,Model model) throws Exception {
//        String aid = request.getParameter("aid");
        String token = request.getParameter("token");
        logger.debug("egg token : " + token);
        User user = userService.token2User(token);
        if(user==null){
            model.addAttribute("username","游客");
            model.addAttribute("point",0);
        }else{
            PersonCard personCard = personCardService.getScoreByPersonID(user.getPersonID());
            Person person = personService.getRedisPersonByID(user.getPersonID());
            if(personCard==null){
                model.addAttribute("username",person.getName());
                model.addAttribute("point",0);
            }else{
                model.addAttribute("username",person.getName());
                Lottery lottery = lotteryService.findLotteryByPhoneAndObjID(user.getPhoneNum(),Lottery.objID_GOLDEGG);
                int lotteryTotal = 0;
                if(lottery!=null){
                    lotteryTotal = lottery.getLotteryNum();
                }
                int _point = (personCard.getShareTotal()-lotteryTotal*10);
                if(_point < 0){
                    _point = 0;
                }
                model.addAttribute("point", _point);
            }
            model.addAttribute("pnum",user.getPhoneNum());
            model.addAttribute("personID",user.getPersonID());
        }

        return "goldegg/index";
    }

    @RequestMapping(value = "/getLatestWiningPrizes")
    @ResponseBody
    public JSONObject getLatestWiningPrizes(Model model,HttpServletRequest request) throws BaseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("status",1);
        JSONObject _jsonObject = null,__jsonObject;
        String phone = null,username=null;
        List<Lottery> lotteryList = lotteryService.getLotteryList();
        for(Lottery lottery:lotteryList){
            _jsonObject = new JSONObject();
            _jsonObject.put("id",lottery.getLotteryID());
            switch (lottery.getLevel()){
                case 1:
                    _jsonObject.put("name","一等奖");
                    break;
                case 2:
                    _jsonObject.put("name","二等奖");
                    break;
                case 3:
                    _jsonObject.put("name","三等奖");
                    break;
                case 4:
                    _jsonObject.put("name","四等奖");
                    break;
                default:
//                    _jsonObject.put("name","纪念奖");
                    break;
            }
            __jsonObject = new JSONObject();
            phone = lottery.getPhoneNum().substring(0,3)+"****"+lottery.getPhoneNum().substring(7);
            __jsonObject.put("phone",phone);
            int user_length = lottery.getWinner().length();
            if(user_length == 2){
                username = "*" + lottery.getWinner().substring(1,user_length);
            }else if(user_length >=3){
                int _star = user_length - 2;
                String star = "";
                for(int i=0;i<_star;i++){
                    star += "*";
                }
                username = lottery.getWinner().substring(0,1)+star+lottery.getWinner().substring(user_length-1,user_length);
            }else{
                username = lottery.getWinner();
            }
            __jsonObject.put("username", username);
            _jsonObject.put("user", __jsonObject);
            jsonArray.add(_jsonObject);
        }
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @RequestMapping(value = "/drawLottery")
    @ResponseBody
    public JSONObject drawLottery(Model model,HttpServletRequest request) throws BaseException, UnsupportedEncodingException {
        String pnum = request.getParameter("pnum");
        String personID = request.getParameter("personID");
        String username = java.net.URLDecoder.decode(request.getParameter("username"), "utf-8");
//        String aid = request.getParameter("aid");
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject _jsonObject = null;

        if(StringUtils.isBlank(pnum)){
            jsonObject.put("status",0);
        }else{



                PersonCard personCard = personCardService.getScoreByPhoneNum(pnum);
                if(personCard==null || personCard.getShareTotal()==null){
                    jsonObject.put("status",-1);
//            jsonObject.put("msg","机会已用完~~~转发10人可以再抽一次哟");
                }else if(personCard.getShareTotal() <= 0){
                    jsonObject.put("status",0);
                }else{
                    jsonObject.put("status",1);
                    _jsonObject = new JSONObject();

                    Lottery lottery = new Lottery();
                    lottery.setLotteryID(UUIDUtil.getUUID());
                    lottery.setLevel(0);
                    lottery.setObjID(Lottery.objID_GOLDEGG);
                    lottery.setPhoneNum(pnum);
                    lottery.setWinner(username);
                    lottery.setLotteryNum(1);
                    lottery.setPersonID(personID);
                    lottery.setOrderNum(99);
                    lottery.setCreateTime(Calendar.getInstance().getTimeInMillis());
                    lottery.setWinnerTime(lottery.getCreateTime());
                    lottery.setLotteryStatus(Lottery.lotteryStatus_0);

                    if(lotteryService.insertOrUpdateLotteryNum(lottery)>0){
                        Lottery newlottery = lotteryService.findLotteryByPhoneAndObjID(pnum,Lottery.objID_GOLDEGG);
                        int lotteryTotal = 0;
                        if(newlottery!=null){
                            lotteryTotal = newlottery.getLotteryNum();
                            if(newlottery.getLotteryStatus()==Lottery.lotteryStatus_1){
                                _jsonObject.put("result",1);
                                switch(newlottery.getLevel()){
                                    case 1:
                                        _jsonObject.put("gift", "一等奖");
                                        break;
                                    case 2:
                                        _jsonObject.put("gift", "二等奖");
                                        break;
                                    case 3:
                                        _jsonObject.put("gift", "三等奖");
                                        break;
                                    default:
                                        _jsonObject.put("gift", "四等奖");
                                        break;
                                }
                                this.lotteryService.updateLotteryStatus(pnum,Lottery.objID_GOLDEGG,Lottery.lotteryStatus_2);
                            }else{
                                _jsonObject.put("result",0);
                            }
                        }else{
                            _jsonObject.put("result",0);
                        }
                        int _point = (personCard.getShareTotal()-lotteryTotal*10);
                        if(_point < 0){
                            _point = 0;
                        }
                        _jsonObject.put("point", _point);
                        jsonObject.put("data",_jsonObject);
                    }else{
                        jsonObject.put("status",-1);
                    }
                }

        }
        return jsonObject;
    }
}
