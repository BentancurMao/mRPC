package com.mao.maorpc.protocol;

public interface ProtocolConstant {

    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte MESSAGE_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte MESSAGE_VERSION = 0x1;
}
