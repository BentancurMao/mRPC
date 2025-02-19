package com.mao.example.consumer;


import com.mao.example.common.model.User;
import com.mao.example.common.service.UserService;
import com.mao.maorpc.bootstrap.ConsumerBootstrap;
import com.mao.maorpc.proxy.ServiceProxyFactory;

public class ConsumerExample {
    public static void main(String[] args) {
        // 服务消费者初始化
        ConsumerBootstrap.init();

        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("Bentancur Mao");
        // 调用远程服务
        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println("消费者获取到返回:" + newUser.getName());
        }else{
            System.out.println("User is null !!");
        }
    }
}
