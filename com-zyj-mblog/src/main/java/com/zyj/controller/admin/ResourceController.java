package com.zyj.controller.admin;

import com.zyj.beans.ResultBean;
import com.zyj.model.Resource;
import com.zyj.service.IResourceService;
import com.zyj.util.ResultBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    @RequestMapping("/saveResource")
    public ResultBean save(Resource resource){
        try{
            Resource result = resourceService.save(resource);
            return ResultBeanUtil.getResultBean(result);
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }

    @RequestMapping("/deleteByIds")
    public ResultBean deleteByIds(@RequestParam("ids[]") Integer[] ids){
        try{
            resourceService.deleteByIds(Arrays.asList(ids));
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }


    @RequestMapping("/updateResource")
    public ResultBean updateResource(Resource resource){
        try{
            Resource result = resourceService.update(resource);
            return ResultBeanUtil.getResultBean(result);
        }catch (Exception e){
            return ResultBeanUtil.getFail(e.getMessage());
        }
    }
}
