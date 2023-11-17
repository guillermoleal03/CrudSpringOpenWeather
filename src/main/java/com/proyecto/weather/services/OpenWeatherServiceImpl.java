package com.proyecto.weather.services;
import com.proyecto.weather.interfaces.OpenWeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {
    @Value("${openweather.apikey}")
    private String apiKey;

    private final String openWeatherUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final String fiveDayForecastUrl = "https://api.openweathermap.org/data/2.5/forecast";
    private final String airPollutionUrl = "https://api.openweathermap.org/data/2.5/air_pollution";
    private final RestTemplate restTemplate;

    public OpenWeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getTemperatureByCity(String city) {
        String apiUrl = String.format("%s?q=%s&APPID=%s&units=metric", openWeatherUrl, city, apiKey);
        return restTemplate.getForEntity(apiUrl, Object.class);
    }

    public ResponseEntity<Object> get5DayForecast(String city) {
        String apiUrl = String.format("%s?q=%s&APPID=%s&units=metric", fiveDayForecastUrl, city, apiKey);
        return restTemplate.getForEntity(apiUrl, Object.class);
    }

    @Override
    public ResponseEntity<Object> getAirPollution(String city) {
        // Se llama a la API de geocoding ( de el mismo Openweather)
        String geocodingUrl = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", city, apiKey);
        ResponseEntity<Object[]> geocodingResponseEntity = restTemplate.getForEntity(geocodingUrl, Object[].class);

        if (geocodingResponseEntity.getStatusCode().is2xxSuccessful()) {
            Object[] geocodingResponse = geocodingResponseEntity.getBody();
            if (geocodingResponse != null && geocodingResponse.length > 0) {
                Map<String, Object> locationData = (Map<String, Object>) geocodingResponse[0];
                Double lat = (Double) locationData.get("lat");
                Double lon = (Double) locationData.get("lon");

                // Se llama a la API de Pollution
                String airPollutionUrl = String.format("http://api.openweathermap.org/data/2.5/air_pollution?lat=%s&lon=%s&appid=%s", lat, lon, apiKey);
                ResponseEntity<Object> airPollutionResponseEntity = restTemplate.getForEntity(airPollutionUrl, Object.class);

                if (airPollutionResponseEntity.getStatusCode().is2xxSuccessful()) {
                    return airPollutionResponseEntity;
                } else {
                    return airPollutionResponseEntity;
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la solicitud de geocodificación");
    }




}
