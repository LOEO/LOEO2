package com.loeo.base.shiro.cache;

import com.loeo.base.cache.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Tony.L
 * @version ：2018 Version：1.0
 * @create ：2018/07/11 10:45
 */
public class RedisCache<K, V> implements Cache<K, V> {
    public static final String KEY_SEPARATOR = ":";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String keyPrefix;
    private long expire;
    private RedisUtils redisUtils;


    public RedisCache(String prefix, long expire, RedisUtils redisUtils) {
        this.keyPrefix = prefix;
        this.expire = expire;
        this.redisUtils = redisUtils;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    @Override
    public V get(K key) throws CacheException {
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        return (V) this.redisUtils.get(this.getStringKey(key));
    }

    @Override
    public V put(K key, V value) throws CacheException {
        logger.debug("根据key从存储 key [" + key + "]");
        redisUtils.set(this.getStringKey(key), value, expire, TimeUnit.MINUTES);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        logger.debug("从redis中删除 key [" + key + "]");
        try {
            String fk = this.getStringKey(key);
            V previous = (V) this.redisUtils.get(fk);
            this.redisUtils.del(fk);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        Set<String> keys = (Set<String>) this.keys();
        keys.stream().forEach((key) -> {
            this.redisUtils.del(key);
        });
    }

    @Override
    public int size() {
        return this.values().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        return (Set<K>) redisUtils.hKeys(this.getStringWildcardKey());
    }

    @Override
    public Collection<V> values() {
        return redisUtils.hGetAll(this.getStringWildcardKey());
    }

    private String getStringKey(K key) {
        return this.keyPrefix + KEY_SEPARATOR + key;
    }

    private String getStringWildcardKey() {
        return this.keyPrefix + KEY_SEPARATOR;
    }
}