package com.example.luckybank.WeatherApi;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForecastResponse {
    private List<Forecast> list;
    private City city;
}
