package com.zyj.service.impl;

import com.zyj.dao.ResourceMapper;
import com.zyj.model.Resource;
import com.zyj.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 16:04 2018/6/25
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


    //tree
    @Override
    public List<Resource> selectList() {
        return resourceMapper.selectList();
    }

    //菜单
    @Override
    public List<Map<String,Object>> findMenu() {
        return resourceMapper.selectMenu();
    }


}
