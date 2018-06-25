package com.zyj.controller;

import com.zyj.model.User;
import com.zyj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 11:30 2018/6/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/selectList")
    public List<User> selectList(){
        System.out.println(11222);
        return userService.selectList();
    }
}
