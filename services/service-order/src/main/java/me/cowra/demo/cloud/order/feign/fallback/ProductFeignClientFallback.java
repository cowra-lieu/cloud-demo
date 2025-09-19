package me.cowra.demo.cloud.order.feign.fallback;
import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import me.cowra.demo.cloud.order.feign.ProductFeignClient;
import me.cowra.demo.cloud.product.model.Product;
import org.springframework.stereotype.Component;

//! FeignClient 的兜底回调
@Slf4j
@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    @Override
    public Product getProductById(Long id, String token) {
        log.warn("执行兜底处理");
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("0"));
        product.setName("<未知>");
        product.setCount(0);
        return product;
    }

}
