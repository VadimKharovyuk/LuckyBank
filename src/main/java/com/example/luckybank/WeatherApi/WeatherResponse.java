package com.example.luckybank.WeatherApi;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WeatherResponse {
    private String name;
    private Main main;
    private Weather[] weather; // Массив погоды

    @Getter
    @Setter
    public static class Main {
        private double temp;
    }

    @Getter
    @Setter
    public static class Weather {
        private String description;
    }
}
