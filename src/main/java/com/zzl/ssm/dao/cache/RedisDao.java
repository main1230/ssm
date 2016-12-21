package com.zzl.ssm.dao.cache;

import com.zzl.ssm.entity.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zzl on 16-12-21.
 */
public class RedisDao {
    private JedisPool jedisPool;

    public RedisDao(String ip, int post) {
        jedisPool = new JedisPool(ip, post);
    }

    public User getUserById(int id) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
