package com.mao.maorpc.fault.tolerant;

import com.mao.maorpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("出现异常，降级服务", e);
        // TODO: 具体降级策略待实现
        return null;
    }
}
