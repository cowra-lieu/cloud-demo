package me.cowra.demo.cloud.product.controller;

import lombok.RequiredArgsConstructor;
import me.cowra.demo.cloud.product.model.Product;
import me.cowra.demo.cloud.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

}
