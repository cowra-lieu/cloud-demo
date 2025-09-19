package me.cowra.demo.cloud.product.service.impl;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import me.cowra.demo.cloud.product.model.Product;
import me.cowra.demo.cloud.product.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("9.99"));
        product.setName("apple - " + productId);
        product.setCount(2);

        //! FeignClient 默认两个超时:
        //! 1. 连接超时: 10s
        //! 2. 读取超时: 60s
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        return product;
    }
}
