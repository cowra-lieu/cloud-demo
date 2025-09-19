package me.cowra.demo.cloud.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client", url="https://api.open-meteo.com")
public interface WeatherFeignClient {

    @GetMapping("/v1/forecast?current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m")
    String getWeather(@RequestParam String latitude,
                      @RequestParam String longitude);

}
