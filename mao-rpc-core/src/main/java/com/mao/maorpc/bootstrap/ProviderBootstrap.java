package com.mao.maorpc.bootstrap;

import com.mao.maorpc.RpcApplication;
import com.mao.maorpc.config.RegistryConfig;
import com.mao.maorpc.config.RpcConfig;
import com.mao.maorpc.model.ServiceMetaInfo;
import com.mao.maorpc.model.ServiceRegisterInfo;
import com.mao.maorpc.registry.LocalRegistry;
import com.mao.maorpc.registry.Registry;
import com.mao.maorpc.registry.RegistryFactory;
import com.mao.maorpc.server.tcp.VertxTcpServer;

import java.util.List;

public class ProviderBootstrap {

    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList){
        // RPC框架初始化（配置和注册中心）
        RpcApplication.init();
        // 全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        for(ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList){
            // 注册服务到本地
            String serviceName = serviceRegisterInfo.getServiceName();
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e){
                throw new RuntimeException(serviceName + "服务注册失败", e);
            }
        }

        // 启动Web服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
