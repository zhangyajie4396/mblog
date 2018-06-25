package com.zyj.service;

import com.zyj.model.Resource;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 16:04 2018/6/25
 */
public interface IResourceService {

    //查询所有资源
    List<Resource> selectList();
}
