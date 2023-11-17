package com.proyecto.weather.interfaces;

import org.springframework.http.ResponseEntity;

public interface OpenWeatherService {
    ResponseEntity<Object> getTemperatureByCity(String city);
    ResponseEntity<Object> get5DayForecast(String city);
    ResponseEntity<Object> getAirPollution(String city);
}

