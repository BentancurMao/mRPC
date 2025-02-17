package com.mao.maorpc.server.tcp;

import cn.hutool.core.util.IdUtil;
import com.mao.maorpc.RpcApplication;
import com.mao.maorpc.model.RpcRequest;
import com.mao.maorpc.model.RpcResponse;
import com.mao.maorpc.model.ServiceMetaInfo;
import com.mao.maorpc.protocol.*;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VertxTcpClient {

    public static RpcResponse doRequest(RpcRequest rpcRequest, ServiceMetaInfo serviceMetaInfo) throws InterruptedException, ExecutionException {
        // 发送 TCP 请求
        Vertx vertx = Vertx.vertx();
        NetClient netClient = vertx.createNetClient();
        CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<>();
        netClient.connect(serviceMetaInfo.getServicePort(), serviceMetaInfo.getServiceHost(),
                result -> {
                    if(!result.succeeded()){
                        System.err.println("Failed to connect TCP server");
                        return;
                    }
                    NetSocket socket = result.result();
                    // 发送数据，构造消息
                    ProtocolMessage<RpcRequest> requestProtocolMessage = new ProtocolMessage<>();
                    ProtocolMessage.Header header = new ProtocolMessage.Header();
                    header.setMagic(ProtocolConstant.MESSAGE_MAGIC);
                    header.setVersion(ProtocolConstant.MESSAGE_VERSION);
                    header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
                    header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
                    // 生成全局请求ID
                    header.setRequestId(IdUtil.getSnowflakeNextId());
                    requestProtocolMessage.setHeader(header);
                    requestProtocolMessage.setBody(rpcRequest);

                    // 编码请求
                    try {
                        Buffer buffer = ProtocolMessageEncoder.encode(requestProtocolMessage);
                        socket.write(buffer);
                    }catch (IOException e) {
                        throw new RuntimeException("协议消息编码错误");
                    }

                    // 接受响应
                    TcpBufferHandlerWrapper handlerWrapper = new TcpBufferHandlerWrapper(
                            buffer -> {
                                try {
                                    ProtocolMessage<RpcResponse> responseProtocolMessage =
                                            (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
                                    responseFuture.complete(responseProtocolMessage.getBody());
                                }catch (IOException e) {
                                    throw new RuntimeException("协议消息解码错误");
                                }
                            }
                    );
                    socket.handler(handlerWrapper);
                });

        RpcResponse rpcResponse = responseFuture.get();
        netClient.close();
        return rpcResponse;
    }
}
