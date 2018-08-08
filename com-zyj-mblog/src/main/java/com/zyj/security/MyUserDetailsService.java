package com.zyj.security;

import com.zyj.dao.UserMapper;
import com.zyj.model.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 15:53 2018/6/29
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("用户的用户名:{}",s);
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(s);
        List<com.zyj.model.User> users = userMapper.selectByExample(example);
        com.zyj.model.User user = users.get(0);

        return new User(user.getUsername(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }
}
