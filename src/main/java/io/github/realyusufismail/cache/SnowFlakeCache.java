package io.github.realyusufismail.cache;

import io.github.realyusufismail.ydwreg.snowflake.SnowFlake;

import java.util.Map;

public class SnowFlakeCache<K extends SnowFlake> extends CacheStorerer<K, K> {
    public SnowFlakeCache(Map<K, K> map) {
        super(map);
    }

    public K getSnowFlake(K value) {
        for (Map.Entry<K, K> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return null;
    }
}
