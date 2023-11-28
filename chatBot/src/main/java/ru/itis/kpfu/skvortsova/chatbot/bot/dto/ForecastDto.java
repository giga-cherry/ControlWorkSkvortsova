package ru.itis.kpfu.skvortsova.chatbot.bot.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ForecastDto {

    double temp=0;
    double humidity=0;
    String condition;
    double feelslike=0;

    private double convertToCelsius(double degree){
        return Math.round((degree-273.15) * 100.0) / 100.0;
    }

    public ForecastDto(String str) {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(str);
        JsonObject rootObject = jsonElement.getAsJsonObject();

        JsonObject childObject_weather = rootObject.getAsJsonArray("weather").get(0).getAsJsonObject();
        condition=childObject_weather.get("main").getAsString();

        JsonObject childObject_main = rootObject.getAsJsonObject("main");
        temp = convertToCelsius(childObject_main.get("temp").getAsDouble());
        humidity = childObject_main.get("humidity").getAsDouble();
        feelslike = convertToCelsius(childObject_main.get("feels_like").getAsDouble());
    }

    @Override
    public String toString() {
        return "Temperature in C: " + temp+ "\n"+
                "Temperature feels like in C: " + feelslike+ "\n"+
                "Humidity: " + humidity+ "\n"+
                "Condition: " +condition +"\n";
    }
//
//    public List<String> getData(){
//        return List.of("Temperature in C: " + temp,
//                "Temperature feels like in C: " + feelslike,
//                "Humidity: " + humidity,
//                "Condition: " +condition
//        );
//    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }

    public double getFeelslike() {
        return feelslike;
    }
}
