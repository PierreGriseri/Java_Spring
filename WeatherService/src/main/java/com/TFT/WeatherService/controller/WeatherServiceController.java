package com.TFT.WeatherService.controller;

import com.TFT.WeatherService.bean.Weather;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class WeatherServiceController {
    public static final List<Weather> weathersList = new ArrayList<Weather>() {
        private static final long serialVersionUID = -3970206781360313502L;

        {
            add(new Weather("Marseille", "13000", "FR", "ça tape le soleil"));
            add(new Weather("Rennes", "35000", "FR", "Toujours autant de pluie chez moi"));
            add(new Weather("Los Angeles", "90001", "US", "Il fait beau il fait beau"));
        }
    };

    @ApiOperation(value = "Get list of weather with the city, zipcode and coutry", response = Iterable.class, tags = "getWeather")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @RequestMapping(
            name = "getWeather",
            method = RequestMethod.GET
    )
    public List<Weather> getWeather() {
        try {
            return weathersList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ApiOperation(value = "Get specific weather depending on the city name ", response = Weather.class, tags = "getWeatherByCity")
    @RequestMapping(
            name = "getWeatherByCity",
            method = RequestMethod.GET,
            value = "city/{city}"
    )
    public Weather getWeatherByCity(@PathVariable String city) {
        try {
            return weathersList.stream()
                    .filter(weather -> city.equals(weather.getCity_name()))
                    .findAny()
                    .orElse(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @ApiOperation(value = "Get specific weather depending on the zipcode", response = Weather.class, tags = "getWeatherByZipcode")
    @RequestMapping(
            name = "getWeatherByZipcode",
            method = RequestMethod.GET,
            value = "zip/{zipcode}"
    )
    public Weather getWeatherByZipcode(@PathVariable String zipcode) {
        try {
            return weathersList.stream()
                    .filter(weather -> zipcode.equals(weather.getZipcode()))
                    .findAny()
                    .orElse(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

