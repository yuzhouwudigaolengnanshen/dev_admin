package com.dev.admin.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.entity.User;
import com.dev.admin.service.UserService;
import com.dev.admin.web.annotation.MenuName;
import com.dev.common.util.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆
 * Created by yuboon on 2019/01/19
 */
@Controller
@MenuName("登陆")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Reference
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public String login(){
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("/login");
        return "/login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String loginName, String password, HttpServletRequest request){
        String result = "redirect:/index";
        String fail = "/login";
        String errorMsg = "";

        // shiro认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        try {
            subject.login(token);
            User user = userService.getUserByLogiName(loginName);
            if(user != null){
                user.setPassword("");
                //userCache.put(user);
                redisTemplate.opsForValue().set(Constant.cacheName,user);
            }
        } catch (UnknownAccountException e) {
            result = fail;
            errorMsg = "账户不存在";
        } catch (DisabledAccountException e) {
            result = fail;
            errorMsg = "账户存在问题";
        } catch (AuthenticationException e) {
            result = fail;
            errorMsg = "密码错误";
        } catch (Exception e) {
            result = fail;
            errorMsg = "登陆异常";
            log.error(e.getMessage(),e);
        }

        request.setAttribute("error",errorMsg);
        return result;
    }

    @RequestMapping("/doLogout")
    public String doLogout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @RequestMapping("/getSession")
    public ResponseEntity getSession(HttpServletRequest request){
        //return ResponseEntity.ok(userCache.get());
        return ResponseEntity.ok(redisTemplate.opsForValue().get(Constant.cacheName));
        //return null;
    }

//    public static void main(String[] args) {
//        /*String PASS = PasswordHash.createHash("admin");
//        String PASS2 = PasswordHash.createHash("admin");
//        System.err.println(PASS);
//        System.err.println(PASS2);
//        System.err.println(PasswordHash.validatePassword("admin",PASS2));*/
//    }

}
