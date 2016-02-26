package com.mai.controller.nav;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mai.user.entity.User;
import com.mai.user.service.UserService;
import com.mai.util.CurrentUser;
import com.mai.util.MD5Tools;

/**
 * 导航
 * Created by fengdzh on 2015/9/15.
 */
@Controller
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        System.out.print("aiaiaia");
        return "index";
    }

    /**
     * 登陆页
     *
     * @return
     */
    @RequestMapping("/toLogIn")
    public String toLogIn() {
        Subject subject = SecurityUtils.getSubject();
        if(null!=subject){
            subject.logout();
            CurrentUser.setCurrentUser(null);
        }
        return "admin/logIn";
    }

    /**
     * 登陆页
     *
     * @return
     */
    @RequestMapping("/navbar")
    public String dashboard(@RequestParam(required = false) String choosed) {
        return "admin/navbar";
    }

    @RequestMapping(value = "/viewRoleP")
    @ResponseBody
    public String viewRolePermission(){
        StringBuffer sb = new StringBuffer("");

        List<Map<String,String>> plist = CurrentUser.getCurrentUser().getPermissionList();
        if(plist!=null&&plist.size()>0)
            for(Map<String,String> permission : plist)
            {
                sb.append("<li><a href=\""+permission.get("actionURL")+"\">"+permission.get("actionName")+"</a></li>");

            }
        return sb.toString();
    }
    @RequestMapping(value = "/viewRolePLte")
    @ResponseBody
    public String viewRolePermissionLte(){
        StringBuffer sb = new StringBuffer("");

        List<Map<String,String>> plist = CurrentUser.getCurrentUser().getPermissionList();
        if(plist!=null&&plist.size()>0)
            for(Map<String,String> permission : plist)
            {
                sb.append("<li><a href=\""+permission.get("actionURL")+"\">"+"<i class=\"fa fa-angle-double-right\"></i>" +permission.get("actionName")+"</a></li>");

            }
        return sb.toString();
    }

    @RequestMapping(value = "/checkPassW")
    @ResponseBody
    public Boolean checkPassW(String oldpass){
        String _oldpass = MD5Tools.md5(oldpass);
        if(_oldpass.equals(CurrentUser.getPassWord())){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/updatePassW")
    @ResponseBody
    public Boolean updateNewPassWord(String oldpass,String newpass){
        String _oldpass = MD5Tools.md5(oldpass);
        if(_oldpass.equals(CurrentUser.getPassWord())){
            String _newpass = MD5Tools.md5(newpass);
            User user = CurrentUser.getCurrentUser();
            user.setPassword(_newpass);
            userService.updateUserPWD(user);
            return true;
        }else{
            return false;
        }
    }

}
