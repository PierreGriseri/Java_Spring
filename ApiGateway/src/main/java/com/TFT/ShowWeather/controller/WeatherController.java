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


    /*@RequestMapping(method = RequestMethod.GET, value = "/weatherDetails")
    public String getWeather()
    {
        System.out.println("Getting weathers details");

        String response = restTemplate.exchange("http://localhost:8011/",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Body " + response);

        return "Weathers [ Weather Details " + response+" ]";
    }*/

    @RequestMapping(value = "/city/{city_name}", method = RequestMethod.GET)
    public String getWeathersByCity(@PathVariable String city_name)
    {
        System.out.println("Going to call weather service to get data!");
        return delegate.callStudentServiceAndGetData(city_name);
    }

   /*@RequestMapping(value = "weatherDetails/zipcode/{zipcode}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeathersByZipcode(@PathVariable String zipcode)
    {
        System.out.println("Getting Weather details for city zipcode" + zipcode);

        String response = restTemplate.exchange("http://localhost:8011/zip/{zip}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, zipcode).getBody();

        System.out.println("Response Body " + response);

        return "Zipcode of the city  -  " + zipcode + " [ Weather Details " + response + " ]";
    }*/
    
}
