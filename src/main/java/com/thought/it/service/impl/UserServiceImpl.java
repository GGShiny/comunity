package com.thought.it.service.impl;

import com.thought.it.mapper.UserMapper;
import com.thought.it.model.User;
import com.thought.it.model.UserExample;
import com.thought.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/8/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() == 0){
            //insert
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //update
            User dbUser = users.get(0);
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(dbUser, userExample1);
        }
    }
}
