package me.cowra.demo.cloud.order.feign;

import me.cowra.demo.cloud.order.feign.fallback.ProductFeignClientFallback;
import me.cowra.demo.cloud.product.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

//! 仅仅配置 fallback 是不够的, 还需要整合 sentinel
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    //! SpringMVC 注解的两套逻辑:
    //! 1. 做服务端, 标注在 controller 上
    //! 2. 做客户端, 标注在 FeignClient 上
    //! 有个小技巧:
    //! 写 FeignClient 时, 如果不是访问第三方的服务, 可以直接把服务端的方法签名拷贝过来.
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id, @RequestHeader("token") String token);

}
