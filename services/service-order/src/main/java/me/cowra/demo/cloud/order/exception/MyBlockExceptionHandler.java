package me.cowra.demo.cloud.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.cowra.demo.cloud.common.R;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@RequiredArgsConstructor
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       String resourceName,
                       BlockException e) throws Exception {

        R error = R.error(500, String.format("%s 被 Sentinel 限制, 原因: %s", resourceName, e));
        String json = objectMapper.writeValueAsString(error);

        //! 必须位于 response.getWriter() 之前, 否则 charset=utf-8 不会影响到返回的 writer
        httpServletResponse.setContentType("application/json;charset=utf-8");

        try (PrintWriter writer = httpServletResponse.getWriter()) {
            writer.write(json);
            writer.flush();
        }

    }
}
