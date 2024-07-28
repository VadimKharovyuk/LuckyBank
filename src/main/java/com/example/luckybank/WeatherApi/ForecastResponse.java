package com.example.luckybank.WeatherApi;

import com.example.luckybank.WeatherApi.model.City;
import com.example.luckybank.WeatherApi.model.Forecast;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForecastResponse {
    private List<Forecast> list;
    private City city;
}
