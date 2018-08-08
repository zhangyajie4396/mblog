package com.zyj.service;

import com.zyj.model.Resource;

import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 16:04 2018/6/25
 */
public interface IResourceService {

    //查询菜单（一级）
    List<Map<String,Object>> findMenu();

    //查询资源树
    List<Resource> selectList();

    //查询所有资源
    List<Resource> selectAll();

    Resource save(Resource resource);

    Resource update(Resource resource);

    //删除对象为子级时参数是子级id,删除对象为父级时是父级id跟子级id的集合
    void deleteByIds(List<Integer> ids);

}
