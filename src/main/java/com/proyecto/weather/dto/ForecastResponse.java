package com.proyecto.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class ForecastResponse {
    private List<WeatherForecast> list;

    @Data
    public static class WeatherForecast {
        private long dt;
        private MainInfo main;
        private List<Weather> weather;

        @Data
        public static class MainInfo {
            private double temp;
            private double temp_min;
            private double temp_max;
        }

        @Data
        public static class Weather {
            private String main;
            private String description;
        }
    }
}
