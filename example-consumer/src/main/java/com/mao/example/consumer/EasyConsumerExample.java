package com.mao.example.consumer;

import com.mao.example.common.model.User;
import com.mao.example.common.service.UserService;
import com.mao.maorpc.proxy.ServiceProxyFactory;

public class EasyConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("mao");
        // 调用
        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else{
            System.out.println("user is null!");
        }
    }
}
