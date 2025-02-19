package com.mao.maorpc.bootstrap;

import com.mao.maorpc.RpcApplication;

public class ConsumerBootstrap {

    /**
     * 初始化
     */
    public static void init(){
        // RPC框架初始化（配置与注册中心）
        RpcApplication.init();
    }
}
