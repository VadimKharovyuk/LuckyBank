package com.example.luckybank.WeatherApi.model;

import com.example.luckybank.WeatherApi.model.Clouds;
import com.example.luckybank.WeatherApi.model.Main;
import com.example.luckybank.WeatherApi.model.Weather;
import com.example.luckybank.WeatherApi.model.Wind;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Forecast {
    private long dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
}
