package com.zyj.service.impl;

import com.zyj.dao.GroupMapper;
import com.zyj.model.Group;
import com.zyj.model.GroupExample;
import com.zyj.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 10:17 2018/7/3
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> selectList() {
        return groupMapper.selectList();
    }

    @Override
    @Transactional
    public void save(Group group) {
        groupMapper.insertSelective(group);
    }

    @Override
    @Transactional
    public void update(Group group) {
        groupMapper.updateByPrimaryKeySelective(group);
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        GroupExample example = new GroupExample();
        example.createCriteria().andIdIn(ids);
        groupMapper.deleteByExample(example);
    }
}
