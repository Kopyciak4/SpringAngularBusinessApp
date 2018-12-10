package babackend.BABackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class TokenService {

    private RedisTemplate<String, String> redisTemplate;

    /*
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
     */

    @Autowired
    public TokenService(RedisTemplate<String, String> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    public String getToken(String username) {

        return redisTemplate.opsForValue().get(username);

    }

    public void saveToken(String username, String token, long expireTime) {
        redisTemplate.opsForValue().set(username, token);
        redisTemplate.expire(username, expireTime, TimeUnit.MILLISECONDS);
    }

}


