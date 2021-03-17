package com.baizhi.springboot_jsp_shiro.cache;

import com.baizhi.springboot_jsp_shiro.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

public class RedisCache<k,v> implements Cache<k,v> {
    @Override
    public v get(k k) throws CacheException {
        System.out.println("get key:"+k);
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return (v) redisTemplate.opsForValue().get(k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("put key:"+k);
        System.out.println("put value:"+v);
        RedisTemplate redisTemplate=(RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(k.toString(),v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }
}
