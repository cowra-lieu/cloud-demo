package me.cowra.demo.cloud.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    @LoadBalanced   // 注解后，restTemplate 就有了负载均衡的能力
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
