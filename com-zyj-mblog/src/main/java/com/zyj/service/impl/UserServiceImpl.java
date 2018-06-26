package com.zyj.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyj.dao.UserMapper;
import com.zyj.dao.UserRoleMapper;
import com.zyj.model.*;
import com.zyj.service.IUserService;
import groovy.transform.TailRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 11:26 2018/6/25
 */
@Service

public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    public List<User> selectList() {
        return userMapper.selectList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {

        //用户名判重
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(example);
        if(users.size() > 0){
            throw new RuntimeException("用户名重复！");
        }
        user.setCreateTime(new Date());
        user.setDisabled(1);//默认启用
        if(StringUtils.isEmpty(user.getPassword())){
            user.setPassword("123456");
        }
        userMapper.insertSelective(user);

        List<Role> roles  = user.getRoles();

        if(!CollectionUtils.isEmpty(roles)){

            List<UserRole> list = new ArrayList<>();

            for(Role role : roles) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                list.add(userRole);
            }

            userRoleMapper.batchInsert(list);
        }

    }

    @Override
    @Transactional
    public void updateUser(User user) {

        //用户名判重
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> us = userMapper.selectByExample(example);
        if(us.size()>0 && !us.get(0).getId().equals(user.getId())){
            throw new RuntimeException("用户名重复！");
        }

        userMapper.updateByPrimaryKeySelective(user);

        //删除用户所属角色 ，重新添加
        List<Role> roles = user.getRoles();
        if(!CollectionUtils.isEmpty(roles)){
            List<UserRole> list = new ArrayList<>();
            for(Role role : roles){
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());
                list.add(userRole);
            }
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(user.getId());
            userRoleMapper.deleteByExample(userRoleExample);
            userRoleMapper.batchInsert(list);

        }


    }

    @Override
    @Transactional
    public void doDisable(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdIn(ids);
        userRoleMapper.deleteByExample(userRoleExample);

        UserExample example = new UserExample();
        example.createCriteria().andIdIn(ids);
        userMapper.deleteByExample(example);
    }
}
