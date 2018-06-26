package com.zyj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyj.beans.ResultBean;
import com.zyj.model.Role;
import com.zyj.model.User;
import com.zyj.service.IUserService;
import com.zyj.util.ResultBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 11:30 2018/6/25
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/selectList")
    public ResultBean selectList(){
        return ResultBeanUtil.getResultBean(userService.selectList());
    }

    @RequestMapping("/save")
    public ResultBean saveUser(String jsonData){
        try{
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            User user = JSON.parseObject(jsonObject.getString("user"), User.class);
            List<Role> roles = JSON.parseArray(jsonObject.getString("roles"), Role.class);
            user.setRoles(roles);
            userService.saveUser(user);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }

    @RequestMapping("/update")
    public ResultBean updateUser(String jsonData){
        try{
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            User user = JSON.parseObject(jsonObject.getString("user"), User.class);
            List<Role> roles = JSON.parseArray(jsonObject.getString("roles"), Role.class);
            user.setRoles(roles);
            userService.updateUser(user);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }


    //启用停用
     @RequestMapping("/doDisable")
    public ResultBean doDisable(User user){
        try{
            userService.doDisable(user);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }

    //删除
    @RequestMapping("/deleteByIds")
    public ResultBean deleteByIds(@RequestParam("ids[]") Integer[] ids){
        try{
            userService.deleteByIds(Arrays.asList(ids));
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }
}
