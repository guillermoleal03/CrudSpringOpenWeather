package com.proyecto.weather.controller;
import com.proyecto.weather.services.OpenWeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Link de swagger : http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private OpenWeatherServiceImpl openWeatherService;
    @GetMapping("/temperature")
    public ResponseEntity<Object> getTemperatureByCity(@RequestParam String city) {
        return openWeatherService.getTemperatureByCity(city);
    }

    @GetMapping("/forecast")
    public ResponseEntity<Object> get5DayForecast(@RequestParam String city) {
        return openWeatherService.get5DayForecast(city);
    }

    @GetMapping("/pollution")
    public ResponseEntity<Object> getAirPollutionByCity(@RequestParam String city) {
        return openWeatherService.getAirPollution(city);
    }
}
