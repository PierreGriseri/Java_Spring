package com.TFT.ApiGateway.controller;

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
public class SingerController {

    @Autowired
    RestTemplate restTemplate;

    public String  fallbackMethodInteger(int singerid){

        return "Fallback response:: No singer details available temporarily";
    }
    public String  fallbackMethod(){

        return "Fallback response:: No singer details available temporarily";
    }
    public String  fallbackMethodBoolean(boolean isAlive){

        return "Fallback response:: No singer details available temporarily";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/singerDetails")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getSingers()
    {
        System.out.println("Getting Singers details");

        String response = restTemplate.exchange("http://singer-service/singers",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        System.out.println("Response Body " + response);

        return "Singers [ Singer Details " + response+" ]";
    }

    @RequestMapping(value = "singerDetails/{singerid}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethodInteger")
    public String getSingersById(@PathVariable int singerid)
    {
        System.out.println("Getting Singer details for " + singerid);

        String response = restTemplate.exchange("http://singer-service/singers/{singerid}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, singerid).getBody();

        System.out.println("Response Body " + response);

        return "Singer Id -  " + singerid + " [ Singer Details " + response+" ]";
    }

    @RequestMapping(value = "singerDetails/alive/{isAlive}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackMethodBoolean")
    public String getSingersByAliveness(@PathVariable boolean isAlive)
    {
        System.out.println("Getting Singer details for Aliveness : " + isAlive);

        String response = restTemplate.exchange("http://singer-service/singers/alive/{isAlive}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, isAlive).getBody();

        System.out.println("Response Body " + response);

        return "Singer isAlive -  " + isAlive + " [ Singer Details " + response+" ]";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
