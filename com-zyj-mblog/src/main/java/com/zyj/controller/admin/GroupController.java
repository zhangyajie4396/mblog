package com.zyj.controller.admin;

import com.zyj.beans.ResultBean;
import com.zyj.model.Group;
import com.zyj.service.IGroupService;
import com.zyj.util.ResultBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 10:20 2018/7/3
 */
@RestController
@RequestMapping("/admin/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @RequestMapping("/selectList")
    public ResultBean list(){
        return ResultBeanUtil.getResultBean(groupService.selectList());
    }

    @RequestMapping("save")
    public ResultBean save(Group group){
        try{
            groupService.save(group);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail("操作失败");
        }
    }

    @RequestMapping("/update")
    public ResultBean update(Group group){
        try{
            groupService.update(group);
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail("操作失败");
        }
    }

    @RequestMapping("/deleteByIds")
    public ResultBean deleteByIds(@RequestParam("ids[]")Integer[] ids){
        try{
            groupService.deleteByIds(Arrays.asList(ids));
            return ResultBeanUtil.getSuccess();
        }catch (Exception e){
            return ResultBeanUtil.getFail("操作失败");
        }
    }
}
