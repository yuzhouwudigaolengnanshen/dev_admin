package com.dev.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * Created by 风象南(yuboon) on 2019/01/19
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("/login");
        return "/index";
    }

}
