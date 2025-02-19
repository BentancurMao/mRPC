package com.mao.examplespringbootprovider;

import com.mao.example.common.model.User;
import com.mao.example.common.service.UserService;
import com.mao.maorpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名: " + user.getName());
        return user;
    }
}
