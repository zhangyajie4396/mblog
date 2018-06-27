package com.zyj.service.impl;

import com.zyj.dao.RoleMapper;
import com.zyj.dao.RoleResourceMapper;
import com.zyj.model.Role;
import com.zyj.model.RoleExample;
import com.zyj.model.RoleResource;
import com.zyj.model.RoleResourceExample;
import com.zyj.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 14:12 2018/6/26
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public List<Role> selectList() {
        return roleMapper.selectList();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andRoleIdIn(ids);
        roleResourceMapper.deleteByExample(example);

        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(ids);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    @Transactional
    public void permissionConfig(List<RoleResource> list) {

        //先根据角色id删除再重新添加
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(list.get(0).getRoleId());
        roleResourceMapper.deleteByExample(example);

        if(list.get(0).getResourceId()!= null){
            roleResourceMapper.batchInsert(list);
        }
    }
}
