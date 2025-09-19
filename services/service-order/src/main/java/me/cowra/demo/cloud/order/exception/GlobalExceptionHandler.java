package me.cowra.demo.cloud.order.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


//    @ExceptionHandler(Throwable.class)
//    public String error(Throwable e) {
//        log.warn(e.getMessage(), e);
//        return "";
//    }
}
