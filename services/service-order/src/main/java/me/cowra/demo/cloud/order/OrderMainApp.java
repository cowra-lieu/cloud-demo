package me.cowra.demo.cloud.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@EnableDiscoveryClient  //! 开启服务发现客户端
@SpringBootApplication
public class OrderMainApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderMainApp.class, args);
    }

    //1. 服务启动时就监听配置变化
    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager) {
        return args -> {
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener("service-order.yml", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(2);
                }

                @Override
                public void receiveConfigInfo(String s) {
                    log.debug("Changed ConfigInfo: {}", s);
                }
            });
        };
    }
}
