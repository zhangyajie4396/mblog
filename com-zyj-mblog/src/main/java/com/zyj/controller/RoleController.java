package com.zyj.controller;

import com.zyj.beans.ResultBean;
import com.zyj.model.Role;
import com.zyj.model.RoleResource;
import com.zyj.service.IRoleService;
import com.zyj.util.ResultBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 14:13 2018/6/26
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/selectList")
    public ResultBean selectList(){
        return ResultBeanUtil.getResultBean(roleService.selectList());
    }


    @RequestMapping("/saveRole")
    public ResultBean saveRole(Role role){
        try {
            roleService.saveRole(role);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }

    @RequestMapping("/updateRole")
    public ResultBean updateRole(Role role){
        try {

            roleService.updateRole(role);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }


    @RequestMapping("/deleteByIds")
    public ResultBean deleteByIds(@RequestParam("ids[]") Integer[] ids){
        try {

            roleService.deleteByIds(Arrays.asList(ids));
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }

    //权限配置
    @RequestMapping("/permissionConfig")
    public ResultBean permissionConfig(@RequestBody List<RoleResource> list){
        try {

            roleService.permissionConfig(list);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }
}
