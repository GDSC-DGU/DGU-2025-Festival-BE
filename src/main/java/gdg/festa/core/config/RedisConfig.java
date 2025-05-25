package gdg.festa.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // 1) 서버 정보
        RedisStandaloneConfiguration serverConfig =
                new RedisStandaloneConfiguration(host, port);

        // 2) 클라이언트 쪽 SSL/TLS 설정
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl()                          // TLS 사용
                .disablePeerVerification()         // 테스트용: 서버 인증서 검증 생략
                .build();

        return new LettuceConnectionFactory(serverConfig, clientConfig);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        // String 값을 쓰신다면 <String,String> 으로 선언하시면 더 깔끔합니다
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        // 키/값 모두 문자열 직렬화
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}