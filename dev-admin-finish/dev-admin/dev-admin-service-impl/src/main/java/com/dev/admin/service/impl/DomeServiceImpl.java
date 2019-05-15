package com.dev.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.User;
import com.dev.admin.mapper.UserMapper;
import com.dev.admin.service.DomeService;
import com.dev.admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DomeServiceImpl implements DomeService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List getUserList() {
        List<User> userList = userMapper.getUserList(new UserVo());
        return userList;
    }

    @Override
    public void saveUser(String username) {
        User user = new User();
        user.setName(username);
        userMapper.insert(user);
    }
}
