package com.zyj.service.impl;

import com.zyj.dao.UserMapper;
import com.zyj.model.User;
import com.zyj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    @Override
    public List<User> selectList() {
        return userMapper.selectList();
    }
}
