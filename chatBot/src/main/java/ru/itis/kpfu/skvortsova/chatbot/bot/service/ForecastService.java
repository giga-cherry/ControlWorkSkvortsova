package ru.itis.kpfu.skvortsova.chatbot.bot.service;

import ru.itis.kpfu.skvortsova.chatbot.bot.client.HttpClientGoRest;
import ru.itis.kpfu.skvortsova.chatbot.bot.dto.ForecastDto;

import java.util.HashMap;
import java.util.Map;

public class ForecastService {

    private static final String URL_1 = "http://api.openweathermap.org/data/2.5/weather";
    private static final String AUTH_CODE = "2be71cc6f6e6e56027fed183a07713a0";


    public String getCityForecast(String city){
        if(city!=null){
            String json=getForecast(city);
            if (json.length()>3) {
                ForecastDto forecast = new ForecastDto(json);
                return forecast.toString();
            }
        }
        return null;
    }

    private String getForecast(String city){
        HttpClientGoRest hc = new HttpClientGoRest();
        Map<String, String> mp = new HashMap<>();

        mp.put("q", city);
        mp.put("appid", AUTH_CODE);
        return hc.get(URL_1,mp);
    }
}
