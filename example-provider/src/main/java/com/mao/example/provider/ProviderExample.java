package com.mao.example.provider;

import com.mao.example.common.service.UserService;
import com.mao.maorpc.RpcApplication;
import com.mao.maorpc.config.RegistryConfig;
import com.mao.maorpc.config.RpcConfig;
import com.mao.maorpc.model.ServiceMetaInfo;
import com.mao.maorpc.registry.LocalRegistry;
import com.mao.maorpc.registry.Registry;
import com.mao.maorpc.registry.RegistryFactory;
import com.mao.maorpc.server.tcp.VertxTcpServer;

public class ProviderExample {
    public static void main(String[] args) {
        RpcApplication.init();

        // 注册服务到本地
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        // 启动Web服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
