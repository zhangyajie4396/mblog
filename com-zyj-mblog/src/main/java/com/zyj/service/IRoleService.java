package com.zyj.service;

import com.zyj.model.Role;
import com.zyj.model.RoleResource;

import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 14:12 2018/6/26
 */
public interface IRoleService {

    List<Role> selectList();

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteByIds(List<Integer> ids);

    void permissionConfig(List<RoleResource> list);
}
