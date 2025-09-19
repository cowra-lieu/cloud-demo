package me.cowra.demo.cloud.order.feign;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WeatherFeignClientTest {

    @Autowired
    WeatherFeignClient weatherFeignClient;

    @Test
    public void testGetWeather() {
        String resp = weatherFeignClient.getWeather(
                "30.553235",
                "114.316223");

        log.debug("resp: {}", resp);
    }


}