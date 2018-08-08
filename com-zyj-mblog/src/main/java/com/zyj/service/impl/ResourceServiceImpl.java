package com.zyj.service.impl;

import com.zyj.dao.ResourceMapper;
import com.zyj.dao.RoleResourceMapper;
import com.zyj.model.Resource;
import com.zyj.model.ResourceExample;
import com.zyj.model.RoleResourceExample;
import com.zyj.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    //tree
    @Override
    public List<Resource> selectList() {
        List<Resource> resources = resourceMapper.selectList();
        List<Resource> resultList = new ArrayList<>();

        for(Resource resource : resources){
            if(StringUtils.isEmpty(resource.getPid())){
                resultList.add(resource);
            }
        }

        for(Resource resource : resultList){
            resource.setChildren(getChildList(resource.getId(),resources));
        }

        return resultList;
    }

    //查询所有资源
    @Override
    public List<Resource> selectAll() {
        return resourceMapper.selectList();
    }

    @Override
    @Transactional
    public Resource save(Resource resource) {
        resourceMapper.insertSelective(resource);
        return resource;
    }

    @Override
    @Transactional
    public Resource update(Resource resource) {
        resourceMapper.updateByPrimaryKeySelective(resource);
        return resource;
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        ResourceExample example = new ResourceExample();
        example.createCriteria().andIdIn(ids);
        resourceMapper.deleteByExample(example);

        //删除角色资源的关联关系
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        roleResourceExample.createCriteria().andResourceIdIn(ids);
        roleResourceMapper.deleteByExample(roleResourceExample);
    }

    //菜单
    @Override
    public List<Map<String,Object>> findMenu() {
        return resourceMapper.selectMenu();
    }

    //递归树
    private List<Resource> getChildList(Integer id,List<Resource> resources) {
        List<Resource> childList = new ArrayList<>();
        for(Resource resource : resources){
            if(id.equals(resource.getPid())){
                childList.add(resource);
            }
        }
        for(Resource resource : childList){
            resource.setChildren(getChildList(resource.getId(),resources));
        }

        if(CollectionUtils.isEmpty(childList)){
            return null;
        }

        return  childList;
    }


}
