package com.java.backend.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    //    @Value("${spring.redis.host}")
    //    private String redisHost;
    //
    //    @Value("${spring.redis.port}")
    //    private int redisPort;
    //
    //    @Bean
    //    public LettuceConnectionFactory redisConnectionFactory() {
    //        // Tạo Standalone Connection tới Redis
    //        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    //    }
    //
    //    @Bean
    //    @Primary
    //    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    //        RedisTemplate<Object, Object> template = new RedisTemplate<>();
    //        template.setConnectionFactory(redisConnectionFactory);
    //        return template;
    //    }
}
