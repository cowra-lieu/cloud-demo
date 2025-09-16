package me.cowra.demo.cloud.product.service;

import me.cowra.demo.cloud.product.model.Product;

public interface ProductService {
    Product getProductById(Long productId);
}
