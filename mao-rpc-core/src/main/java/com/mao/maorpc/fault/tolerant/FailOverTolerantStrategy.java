package com.mao.maorpc.fault.tolerant;

import com.mao.maorpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("出现异常，转移到其他服务提供节点", e);
        // TODO: 具体服务转移逻辑待实现
        return null;
    }
}
