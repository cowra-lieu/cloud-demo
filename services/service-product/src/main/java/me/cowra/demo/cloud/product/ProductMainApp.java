package me.cowra.demo.cloud.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient  //! 开启服务发现客户端
@SpringBootApplication
public class ProductMainApp {

    public static void main(String[] args) {
        SpringApplication.run(ProductMainApp.class, args);
    }

}
