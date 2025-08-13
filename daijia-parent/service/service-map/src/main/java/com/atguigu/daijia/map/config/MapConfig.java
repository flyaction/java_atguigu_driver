package com.atguigu.daijia.map.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: action
 * @create: 2025/8/13 10:54
 **/
@Configuration
public class MapConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
