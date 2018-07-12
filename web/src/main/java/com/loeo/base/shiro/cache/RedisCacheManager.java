package com.loeo.base.shiro.cache;

import com.loeo.base.cache.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author ：Tony.L
 * @version ：2018 Version：1.0
 * @create ：2018/07/11 10:45
 */
public class RedisCacheManager implements CacheManager {
    private long cacheExpire;
    private RedisUtils redisUtils;

    public RedisCacheManager( long cacheExpire, RedisUtils redisUtils) {
        this.cacheExpire = cacheExpire;
        this.redisUtils = redisUtils;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<>(name, cacheExpire, redisUtils);
    }
}
