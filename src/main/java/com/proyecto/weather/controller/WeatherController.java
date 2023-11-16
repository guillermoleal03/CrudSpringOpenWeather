package com.proyecto.weather.controller;
import com.proyecto.weather.services.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private OpenWeatherService openWeatherService;

    @GetMapping("/forecast")
    public ResponseEntity<Object> getTemperatureByCity(@RequestParam String city) {
        return openWeatherService.getTemperatureByCity(city);
    }
}
