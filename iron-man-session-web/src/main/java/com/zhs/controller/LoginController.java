package com.zhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/2 13:06
 * @package: com.zhs.blog.controller
 * @description:
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = {"/","/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main");
        return modelAndView;
    }

    @RequestMapping("/biz1")
    public ModelAndView bzi1(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/biz1");
        return modelAndView;
    }
    @RequestMapping("/biz2")
    public ModelAndView bzi2(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/biz2");
        return modelAndView;
    }
    @RequestMapping("/syslog")
    public ModelAndView log(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/log");
        return modelAndView;
    }
    @RequestMapping("/sysuser")
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user");
        return modelAndView;
    }
}
