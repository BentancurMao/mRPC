package com.mao.maorpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolMessage <T> {

    /**
     * 消息头
     */
    private Header header;

    /**
     * 消息体
     */
    private T body;

    /**
     * 协议消息头
     */
    @Data
    public static class Header{

        private byte magic;

        private byte version;

        private byte serializer;

        private byte type;

        private byte status;

        private long requestId;

        private int bodyLength;
    }
}
