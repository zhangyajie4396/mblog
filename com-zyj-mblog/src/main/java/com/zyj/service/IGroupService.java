package com.zyj.service;

import com.zyj.model.Group;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 10:16 2018/7/3
 */
public interface IGroupService {

    List<Group> selectList();

    void save(Group group);

    void update(Group group);

    void deleteByIds(List<Integer> ids);
}
