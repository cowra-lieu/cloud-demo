package me.cowra.demo.cloud.order.config;

import feign.Retryer;
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

    //! 如果在 Spring 应用上下文中配置 FeignClient 重试器, 所有 FeignClient 都会自动用到它
    //! 如果不想这样, 可以在配置文件对每个 FeignClient 单独配置 retryer
    @Bean
    Retryer retryer() {
        //! 默认每隔100ms重试一次(后续每次是上一次的1.5倍), 所有[重试间隔]时间累加不超过1秒, 最多尝试5次
        return new Retryer.Default();
    }
}
