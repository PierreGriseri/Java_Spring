package com.TFT.ShowWeather.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@Service
public class ShowWeatherDelegate {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "weatherNotFoundFallback")
    public String getWeather() {
        System.out.println("Getting all Weather details for ");
        String response = restTemplate
                .exchange("http://localhost:8011/"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }).getBody();

        System.out.println("Response Received as " + response);

        return "NORMAL FLOW !!! :::  Weather Details " + response;
    }


    @HystrixCommand(fallbackMethod = "weatherNotFoundFallback")
    public String getWeatherByCity(String city) {
        System.out.println("Getting Weather details for " + city);
        String response = restTemplate
                .exchange("http://localhost:8011/city/{city}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, city).getBody();

        System.out.println("Response Received as " + response);

        return "NORMAL FLOW !!! - City Name -  " + city + " :::  Weather Details " + response;
    }

    @HystrixCommand(fallbackMethod = "weatherNotFoundFallback")
    public String getWeatherByZipcode(String zipcode) {
        System.out.println("Getting Weather details for " + zipcode);
        String response = restTemplate
                .exchange("http://localhost:8011/zip/{zipcode}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, zipcode).getBody();

        System.out.println("Response Received as " + response);

        return "NORMAL FLOW !!! - Zip Code -  " + zipcode + " :::  Weather Details " + response;
    }

    @SuppressWarnings("unused")
    private String weatherNotFoundFallback(String value) {
        System.out.println("Weather Service is down!!! fallback route enabled...");
        return "CIRCUIT BREAKER ENABLED!!!No Response From Weather Service at this moment. Service will be back shortly - ";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}