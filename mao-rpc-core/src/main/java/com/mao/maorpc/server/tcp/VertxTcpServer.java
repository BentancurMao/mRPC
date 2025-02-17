package com.mao.maorpc.server.tcp;

import com.mao.maorpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

public class VertxTcpServer implements HttpServer {

    private byte[] handleRequest(byte[] requestData) {
        // 这里以下面的例子作为处理请求的实例
        return "Hello, client".getBytes();
    }

    @Override
    public void doStart(int port) {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer netServer = vertx.createNetServer();

        // 处理请求
        netServer.connectHandler(new TcpServerHandler());

        // 启动TCP服务并监听端口
        netServer.listen(port, result -> {
           if(result.succeeded()){
               System.out.println("TCP server started on port " + port);
           }else{
               System.err.println("Failed to start TCP server " + result.cause());
           }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
