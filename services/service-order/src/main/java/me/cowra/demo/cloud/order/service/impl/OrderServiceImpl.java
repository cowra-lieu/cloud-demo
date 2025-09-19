package me.cowra.demo.cloud.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cowra.demo.cloud.order.feign.ProductFeignClient;
import me.cowra.demo.cloud.order.model.Order;
import me.cowra.demo.cloud.order.service.OrderService;
import me.cowra.demo.cloud.product.model.Product;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    private final ProductFeignClient productFeignClient;


    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemote(productId);
//        Product product = getProductFromRemoteWithLoadBalancer(productId);
//        Product product = getProductFromRemoteWithLoadBalancedAnnotation(productId);

        Product product = productFeignClient.getProductById(productId, "just a test");

        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getCount())));
        order.setUserId(userId);
        order.setNickName("考拉");
        order.setAddress("somewhere");
        order.setProductList(List.of(product));

        return order;
    }

    //! 负载均衡同步远程调用 version1
    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        Random random = new Random();
        int randomIndex = random.nextInt(instances.size()); //! 生成 [0, size) 的随机整数
        ServiceInstance serviceInstance = instances.get(randomIndex);
        String url = String.format("http://%s:%s/product/%s",
                serviceInstance.getHost(),
                serviceInstance.getPort(),
                productId);
        log.info("Accessing remote path: {}", url);
        return restTemplate.getForObject(url, Product.class);
    }

    //! 负载均衡同步远程调用 version2
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        ServiceInstance instances = loadBalancerClient.choose("service-product");
        String url = String.format("http://%s:%s/product/%s",
                instances.getHost(),
                instances.getPort(),
                productId);
        log.info("Accessing remote path: {}", url);
        return restTemplate.getForObject(url, Product.class);
    }

    //! 负载均衡同步远程调用 version3
    private Product getProductFromRemoteWithLoadBalancedAnnotation(Long productId) {
        //! 添加了 @LoadBalanced 注解后，restTemplate 可以自动（通过注册中心）处理微服务名 service-product 到实际host:port
        String url = String.format("http://service-product/product/%s", productId);
        log.info("Accessing remote path: {}", url);
        return restTemplate.getForObject(url, Product.class);
    }
}
