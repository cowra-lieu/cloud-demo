package me.cowra.demo.cloud.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@EnableFeignClients //! 开启 Feign 远程调用功能
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
            //! 1st param: Nacos' Data ID
            //! 2nd param: Nacos' Data Group
            configService.addListener("commons.yml", "order", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(1);
                }

                @Override
                public void receiveConfigInfo(String s) {
                    log.debug("Changed ConfigInfo: {}", s);
                }
            });
        };
    }


}
