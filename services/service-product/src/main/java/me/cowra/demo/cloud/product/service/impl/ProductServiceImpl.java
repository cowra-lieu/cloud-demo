package me.cowra.demo.cloud.product.service.impl;
import java.math.BigDecimal;

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

        
        return product;
    }
}
