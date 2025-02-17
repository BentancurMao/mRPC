package com.mao.example.common.service;

import com.mao.example.common.model.User;

public interface UserService {
    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * Mock测试 - 获取数字
     */
    default int getNumber(){
        return 1;
    }
}
