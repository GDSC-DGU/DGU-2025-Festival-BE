package gdg.festa.infrastructure.redis;

import gdg.festa.core.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisUtilImpl implements RedisUtil {

    private final StringRedisTemplate stringRedisTemplate;

    // 1) 접두어를 저장할 필드
    private String keyPrefix = "";

    @Override
    public void setKeyPrefix(String prefix) {
        this.keyPrefix = (prefix != null ? prefix : "");
    }

    @Override
    public boolean delete(String key) {
        return stringRedisTemplate.delete(keyPrefix + key);
    }
}
