package me.cowra.demo.cloud.order.controller;

import lombok.RequiredArgsConstructor;
import me.cowra.demo.cloud.order.model.Order;
import me.cowra.demo.cloud.order.properties.OrderProperties;
import me.cowra.demo.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope   //! 激活配置属性的自动刷新（配置中心修改后）
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

/*    @Value("${order.timeout}")
    String orderTimeout;

    @Value("${order.auto-confirm}")
    String orderAutoConfirm;*/

    private final OrderProperties orderProperties;

    @PostMapping("/order")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    @GetMapping("/config")
    public String getConfig() {
        return "order.timeout=%s; order.auto-confirm=%s; order.db-url=%s"
                .formatted(orderProperties.getTimeout(),
                        orderProperties.getAutoConfirm(),
                        orderProperties.getDbUrl());
    }

}
