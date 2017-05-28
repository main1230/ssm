package com.zzl.ssm.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zzl.ssm.entity.User;
import com.zzl.ssm.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zzl on 16-12-21.
 */
public class UserRedisDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
    private RuntimeSchema<String> schemaString = RuntimeSchema.createFrom(String.class);

    private final JedisPool jedisPool;
    private static int timeout;

    public UserRedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
        String defaultTimeout = "3600";
        String timeKey = "redis.timeout";
        timeout = Integer.parseInt(PropertiesUtil.getProperty(timeKey, defaultTimeout));
    }

    public User getUserByKey(String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    User user = schema.newMessage();
                    ProtobufIOUtil.mergeFrom(bytes, user, schema);
                    return user;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putUser(String key, User user) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] bytes = ProtobufIOUtil.toByteArray(user, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存时间
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public String putKeyValue(String key, Object value) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] bytes = null;
                if (value instanceof User) {
                    bytes = ProtobufIOUtil.toByteArray((User) value, schema,
                            LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                } else if (value instanceof String) {
                    bytes = ProtobufIOUtil.toByteArray((String) value, schemaString,
                            LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                }
                if (bytes != null) {
                    // 超时缓存时间
                    String result = jedis.setex(key.getBytes(), timeout, bytes);
                    return result;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

   public String getValueByKey(String key) {
       try {
           Jedis jedis = jedisPool.getResource();
           try {
               byte[] bytes = jedis.get(key.getBytes());
               if (bytes != null) {
                   String value = schemaString.newMessage();
                   ProtobufIOUtil.mergeFrom(bytes, value, schemaString);
                   return value;
               }
           } finally {
               jedis.close();
           }
       } catch (Exception e) {
           logger.error(e.getMessage(), e);
       }
       return null;
    }

    public void delete(String ... key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.del(key);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
