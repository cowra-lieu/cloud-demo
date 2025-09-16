package me.cowra.demo.cloud.order.model;

import lombok.Data;
import me.cowra.demo.cloud.product.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {

    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nickName;
    private String address;
    private List<Product> productList;

}
