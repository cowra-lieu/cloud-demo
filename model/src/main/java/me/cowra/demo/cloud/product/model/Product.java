package me.cowra.demo.cloud.product.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;
    private BigDecimal price;
    private String name;
    private int count;

}
