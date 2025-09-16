package me.cowra.demo.cloud.order.service;

import me.cowra.demo.cloud.order.model.Order;

public interface OrderService {

    Order createOrder(Long productId, Long userId);

}
