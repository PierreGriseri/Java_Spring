package com.TFT.ShowWeather.controller;

import com.TFT.ShowWeather.delegate.ShowWeatherDelegate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class WeatherController {

    @Autowired
    ShowWeatherDelegate delegate;



    @RequestMapping(value = "/city/{city_name}", method = RequestMethod.GET)
    public String getWeathersByCity(@PathVariable String city_name)
    {
        System.out.println("Going to call weather service to get data!");
        return delegate.getWeatherByCity(city_name);
    }

    @RequestMapping(value = "/allWeather", method = RequestMethod.GET)
    public String getWeathers()
    {
        System.out.println("Going to call weather service to get data!");
        return delegate.getWeather();
    }

    @RequestMapping(value = "/zip/{zipcode}", method = RequestMethod.GET)
    public String getWeathersByZipCode(String zipcode)
    {
        System.out.println("Going to call weather service to get data!");
        return delegate.getWeatherByZipcode(zipcode);
    }


}
