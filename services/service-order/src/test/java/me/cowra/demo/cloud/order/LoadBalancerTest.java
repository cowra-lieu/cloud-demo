package me.cowra.demo.cloud.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@Slf4j
@SpringBootTest
public class LoadBalancerTest {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    void testLoadBalancer() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");
        log.debug("choose={}:{}", serviceInstance.getHost(), serviceInstance.getPort());

        serviceInstance = loadBalancerClient.choose("service-product");
        log.debug("choose={}:{}", serviceInstance.getHost(), serviceInstance.getPort());

        serviceInstance = loadBalancerClient.choose("service-product");
        log.debug("choose={}:{}", serviceInstance.getHost(), serviceInstance.getPort());
    }

}
