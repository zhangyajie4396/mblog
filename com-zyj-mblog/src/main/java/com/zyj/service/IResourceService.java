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

}
