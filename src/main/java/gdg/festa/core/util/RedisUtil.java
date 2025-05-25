package gdg.festa.core.util;

public interface RedisUtil {
    boolean delete(String key);

    void setKeyPrefix(String prefix);
}
