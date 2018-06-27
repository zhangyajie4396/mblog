package com.zyj.controller;

import com.zyj.beans.ResultBean;
import com.zyj.model.Resource;
import com.zyj.service.IResourceService;
import com.zyj.util.ResultBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 17:20 2018/6/26
 */
@RestController
@RequestMapping("/admin/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    //查询所有菜单组成树
    @RequestMapping("/findAll")
    public ResultBean findTree(){
        List<Resource> resources = resourceService.selectList();
        return ResultBeanUtil.getResultBean(resources);
    }

    //查询所有资源
    @RequestMapping("/selectList")
    public ResultBean selectList(){
        List<Resource> resources = resourceService.selectAll();
        return ResultBeanUtil.getResultBean(resources);
    }
}
