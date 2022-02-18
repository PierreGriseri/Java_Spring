package com.TFT.ShowWeather.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @Autowired
    RestTemplate restTemplate;

    public String fallbackMethod(String value){

        return "Fallback response:: No weather details available for this city";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/weatherDetails")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeather()
    {
        System.out.println("Getting weathers details");

        String response = restTemplate.exchange("http://localhost:8011/",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Body " + response);

        return "Weathers [ Weather Details " + response+" ]";
    }

    @RequestMapping(value = "/city/{city_name}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeathersByCity(@PathVariable String city_name)
    {
        System.out.println("Getting Weather details for the city : " + city_name);

        String response = restTemplate.exchange("http://localhost:8011/city/{city_name}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, city_name).getBody();

        System.out.println("Response Body " + response);

        return "City Name  -  " + city_name + " [ Weather Details " + response + " ]";
    }

    @RequestMapping(value = "weatherDetails/zipcode/{zipcode}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getWeathersByZipcode(@PathVariable String zipcode)
    {
        System.out.println("Getting Weather details for city zipcode" + zipcode);

        String response = restTemplate.exchange("http://localhost:8011/zip/{zip}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, zipcode).getBody();

        System.out.println("Response Body " + response);

        return "Zipcode of the city  -  " + zipcode + " [ Weather Details " + response + " ]";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
