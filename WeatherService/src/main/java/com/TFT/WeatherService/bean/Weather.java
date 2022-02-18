package com.TFT.WeatherService.bean;

import io.swagger.annotations.ApiModelProperty;

public class Weather {


    @ApiModelProperty(notes = "Name of the city", name = "city_name", required = true, value = "Marseille")
    private String city_name;
    @ApiModelProperty(notes = "Zipcode of the city", name = "zipcode", required = true, value = "13000")
    private String zipcode;
    @ApiModelProperty(notes = "Country Code", name = "countryCode", required = true, value = "FR")
    private String countryCode;
    @ApiModelProperty(notes = "Current weather of the city", name = "weather", required = true, value = "Il fait beau")
    private String weather;

    public Weather(String city, String zcde, String country, String wth) {
        this.city_name = city;
        this.zipcode = zcde;
        this.countryCode = country;
        this.weather = wth;
    }
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }


    public String toString() {
        return "Singer{" +
                "Nom de la ville=" + city_name +
                ", Code postal ='" + zipcode + '\'' +
                ", Pays ='" + countryCode + '\'' +
                ", Temps extérieur='" + weather + '\'' +
                '}';
    }
}
