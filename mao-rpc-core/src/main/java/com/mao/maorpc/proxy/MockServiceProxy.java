package com.mao.maorpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服务代理
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成默认返回值
     *
     * @param type
     * @return
     */
    private Object getDefaultObject(Class<?> type){
        if(type.isPrimitive()){
            if(type == boolean.class){
                return false;
            }else if(type == int.class){
                return 0;
            }else if (type == short.class){
                return (short) 0;
            }else if(type == long.class){
                return 0L;
            }
        }
        return null;
    }
}
