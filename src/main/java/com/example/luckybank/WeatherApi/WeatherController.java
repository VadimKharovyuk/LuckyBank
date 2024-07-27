package com.example.luckybank.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String showWeatherPage() {
        return "api/wether/wether";
    }

    @GetMapping("/weather-data")
    @ResponseBody
    public WeatherResponse getWeather(@RequestParam(required = false) String city,
                                      @RequestParam(required = false) Double lat,
                                      @RequestParam(required = false) Double lon) {
        if (city != null && !city.isEmpty()) {
            return weatherService.getWeatherByCity(city);
        } else if (lat != null && lon != null) {
            return weatherService.getWeatherByCoordinates(lat, lon);
        } else {
            throw new IllegalArgumentException("City name or coordinates are required");
        }
    }
}
