package com.mao.examplespringbootconsumer;

import com.mao.example.common.model.User;
import com.mao.example.common.service.UserService;
import com.mao.maorpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test(){
        User user = new User();
        user.setName("Bentancur Mao");
        User newUser = userService.getUser(user);
        System.out.println(newUser.getName());
    }
}
