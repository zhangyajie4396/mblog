package com.zyj.dao;

import com.zyj.model.User;
import com.zyj.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //------------start--------------
    /*
        查询所有用户
     * @Author: ZhangYajie
     * @Param: []
     * @Description:
     * @Date: 15:56 2018/6/25 
     * @Return: java.util.List<com.zyj.model.User>
     */
    List<User> selectList();

}

