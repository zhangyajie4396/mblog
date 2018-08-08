package com.zyj.controller.desk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 14:59 2018/6/29
 */
@Controller
public class IndexController {

    @RequestMapping(value= {"/", "/index"})
    public String index(){
        return "/default/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "/default/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "/default/register";
    }
}
