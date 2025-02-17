package com.mao.example.provider;

import com.mao.example.common.model.User;
import com.mao.example.common.service.UserService;

public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名: " + user.getName());
        return user;
    }
}
