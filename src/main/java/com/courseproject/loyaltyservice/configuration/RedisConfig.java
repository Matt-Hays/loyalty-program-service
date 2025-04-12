package com.courseproject.loyaltyservice.configuration;

import com.courseproject.loyaltyservice.models.Customer;
import com.courseproject.loyaltyservice.models.LoyaltyAccount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${redis.port}")
    private String redisPort;

    @Value("${redis.server}")
    private String redisServer;

    @Bean
    RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisServer, Integer.parseInt(redisPort)));
    }

    @Bean
    RedisTemplate<Long, LoyaltyAccount> loyaltyAccountRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<Long, LoyaltyAccount> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    RedisTemplate<Long, Customer> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Long, Customer> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
