package com.courseproject.loyaltyservice.configuration;

import com.courseproject.loyaltyservice.models.LoyaltyAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    RedisTemplate<Long, LoyaltyAccount> loyaltyAccountRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<Long, LoyaltyAccount> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
