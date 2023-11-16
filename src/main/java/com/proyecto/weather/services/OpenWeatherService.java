package com.proyecto.weather.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class OpenWeatherService {
    @Value("${openweather.apikey}")
    private String apiKey;

    private final String openWeatherUrl = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;
    @Autowired
    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getTemperatureByCity(String city) {
        String apiUrl = String.format("%s?q=%s&APPID=%s&units=metric", openWeatherUrl, city, apiKey);
        return restTemplate.getForEntity(apiUrl, Object.class);
    }
}
