//package com.example.luckybank.WeatherApi;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class WeatherService {
//
//    private final String apiKey = "7edb7af099975cd810018d918a347f18";
//    private final String weatherUrl = "http://api.openweathermap.org/data/2.5/weather";
//
//    public WeatherResponse getWeatherByCity(String city) {
//        String url = String.format("%s?q=%s&appid=%s&units=metric", weatherUrl, city, apiKey);
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, WeatherResponse.class);
//    }
//
//    public WeatherResponse getWeatherByCoordinates(Double lat, Double lon) {
//        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", weatherUrl, lat, lon, apiKey);
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url, WeatherResponse.class);
//    }
//}
package com.example.luckybank.WeatherApi.service;

import com.example.luckybank.WeatherApi.ForecastResponse;
import com.example.luckybank.WeatherApi.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String apiKey = "7edb7af099975cd810018d918a347f18";
    private final String weatherUrl = "http://api.openweathermap.org/data/2.5/weather";
    private final String forecastUrl = "http://api.openweathermap.org/data/2.5/forecast";

    public WeatherResponse getWeatherByCity(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", weatherUrl, city, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    public WeatherResponse getWeatherByCoordinates(Double lat, Double lon) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", weatherUrl, lat, lon, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    public ForecastResponse getForecastByCity(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", forecastUrl, city, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, ForecastResponse.class);
    }

    public ForecastResponse getForecastByCoordinates(Double lat, Double lon) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", forecastUrl, lat, lon, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, ForecastResponse.class);
    }
}
