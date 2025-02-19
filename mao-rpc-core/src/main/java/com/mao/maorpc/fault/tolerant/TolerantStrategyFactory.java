package com.mao.maorpc.fault.tolerant;

import com.mao.maorpc.spi.SpiLoader;

public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错机制
     */
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailBackTolerantStrategy();

    /**
     * 获取容错机制实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
