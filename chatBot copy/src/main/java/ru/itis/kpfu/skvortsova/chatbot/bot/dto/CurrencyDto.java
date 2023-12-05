package ru.itis.kpfu.skvortsova.chatbot.bot.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyDto {

    private final String USD;

    private final String EUR;

    private final String RUB;

    public CurrencyDto(String json){
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(json);
        JsonObject rootObject = jsonElement.getAsJsonObject().getAsJsonObject("data");


        JsonObject usdObj = rootObject.getAsJsonObject("USD");
        USD = usdObj.get("value").getAsString();
        JsonObject eutObj = rootObject.getAsJsonObject("EUR");
        EUR = eutObj.get("value").getAsString();
        JsonObject rubObj = rootObject.getAsJsonObject("RUB");
        RUB = rubObj.get("value").getAsString();
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "USD='" + USD + '\'' +
                ", EUR='" + EUR + '\'' +
                ", RUB='" + RUB + '\'' +
                '}';
    }
}

