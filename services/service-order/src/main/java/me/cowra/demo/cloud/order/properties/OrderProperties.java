package me.cowra.demo.cloud.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")  //! 配置的批量绑定；集成 nacos 时，无需 @RefreshScope 可自动刷新
@Data
public class OrderProperties {

    String timeout;
    String autoConfirm;
    String dbUrl;

}
