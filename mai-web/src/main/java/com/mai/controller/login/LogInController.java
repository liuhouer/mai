package com.mai.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mai.user.entity.Role;
import com.mai.util.CurrentUser;
import com.mai.util.MD5Tools;

/**
 * Created by fengdzh on 2015/9/16.
 */
@Controller
@RequestMapping("/")
public class LogInController {
    private static final Logger logger = Logger.getLogger(LogInController.class);
    private String parentId = "";
    /**
     * <h1>登陆逻辑</h1><br>
     * 1. 验证身份；
     * 2. 获取角色信息；
     * 3. 获取权限信息；放到session内；
     * 4. 跳转到默认页面；
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping("/logIn")
    private String logIn(HttpServletRequest request,Model model) throws Exception {
        String username=request.getParameter("userName");
        String password=request.getParameter("password");
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null){
                RealmSecurityManager securityManager =
                        (RealmSecurityManager) SecurityUtils.getSecurityManager();
                BasicSecurityRealm userRealm = (BasicSecurityRealm) securityManager.getRealms().iterator().next();
                userRealm.clearCachedAuthorizationInfo(username);
            subject.logout();
            CurrentUser.setCurrentUser(null);
        }
        //设置session过期时间
        subject.getSession(true).setTimeout(3600000);
        if(!subject.isAuthenticated()){
            //为跳转页面，不显示错误信息
            if(username==null){
                subject.logout();
                CurrentUser.setCurrentUser(null);
                model.addAttribute("loginerror","用户名不能为空！");
                return "admin/logIn";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Tools.md5(password));
            try {
                subject.login(token);
            } catch (UnknownAccountException uae ) {
                logger.error(uae.getMessage());
                parentId="1";
                //username wasn't in the system, show them an error message?
            } catch (IncorrectCredentialsException ice ) {
                logger.error(ice.getMessage());
                parentId="2";
                //password didn't match, try again?
            } catch (LockedAccountException lae ) {
                logger.error(lae.getMessage());
                parentId="3";
                //account for that username is locked - can't login.  Show them a message?
            }catch(AuthenticationException ae ) {
                logger.error(ae.getMessage());
                parentId="4";
                //unexpected condition - error?
            }catch(Exception aee){
                logger.error(aee.getMessage());
                parentId="5";
            }
        }
        if(subject.isAuthenticated()){
            if(CurrentUser.hasRole(Role.ROLENAME_PRESIDENT)){
                return "redirect:/society/societyDeatil.action";
            }else if(CurrentUser.hasRole(Role.ROLENAME_SOCIETY)){
                return "redirect:/society/list.action";
            }else if(CurrentUser.hasRole(Role.ROLENAME_ACTIVITY)){
                return "redirect:/activity/ActivityList.action";
            }else if(CurrentUser.hasRole(Role.ROLENAME_SPONSOR)){
                return "redirect:/sponsor/SponsorList.action";
            }else if(CurrentUser.hasRole(Role.ROLENAME_PERSON)){
                return "redirect:/user/list.action";
            }else if(CurrentUser.hasRole(Role.SOCIETY_RUNNING)){
                return "redirect:/societyRunning/SocietyList.action";
            }else if(CurrentUser.hasRole(Role.ACTIVITY_RUNNING)){
                return "redirect:/activityRunning/ActivityList.action";
            }else if(CurrentUser.hasRole(Role.RUNNING)){
                return "redirect:/ad/list.action";
            }else{
                subject.logout();
                CurrentUser.setCurrentUser(null);
                model.addAttribute("loginerror", "账号权限未设置！");
                return "admin/logIn";
            }
        }else{
            subject.logout();
            CurrentUser.setCurrentUser(null);
            model.addAttribute("loginerror", "用户名或密码错误！");
            return "admin/logIn";
        }

    }

    @RequestMapping("/logOut")
    public void logOut(HttpServletResponse response) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        if(null!=subject){
            subject.logout();
            CurrentUser.setCurrentUser(null);
        }
        response.sendRedirect("/nav/toLogIn.action");
    }


}
