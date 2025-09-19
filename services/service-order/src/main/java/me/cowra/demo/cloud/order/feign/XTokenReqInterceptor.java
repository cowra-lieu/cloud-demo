package me.cowra.demo.cloud.order.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

//! 如果把拦截器交给Spring应用上下文, 所有FeignClient都会用到它
//! 如果不想这样, 可以在配置文件对每个 FeignClient 单独配置 request-interceptors
@Slf4j
@Component
public class XTokenReqInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        log.debug("apply");
        if (!template.headers().containsKey("X-Token"))
            //! 添加请求头, 无论该请求头是否存在, 当存在重试逻辑时, 拦截器的多次执行, 会导致后续重试请求中的出现越来越多的 X-Token 请求头
            template.header("X-Token", UUID.randomUUID().toString());

    }


}
