package com.example.luckybank.WeatherApi;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WeatherResponse {
    private String name; // Город
    private Main main; // Основные данные о температуре
    private Weather[] weather; // Массив погоды

    @Getter
    @Setter
    public static class Main {
        private double temp; // Температура (в Цельсиях)
    }

    @Getter
    @Setter
    public static class Weather {
        private String description; // Описание погоды
    }
}
