package com.zyj.service;

import com.zyj.model.User;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 11:26 2018/6/25
 */
public interface IUserService {
    
    //查询所有用户
    List<User> selectList();

    void saveUser(User user);

    void updateUser(User user);

    //启用停用
    void doDisable(User user);

    void deleteByIds(List<Integer> ids);

}
