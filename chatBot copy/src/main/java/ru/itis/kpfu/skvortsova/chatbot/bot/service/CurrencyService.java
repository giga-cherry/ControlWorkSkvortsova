package ru.itis.kpfu.skvortsova.chatbot.bot.service;

import ru.itis.kpfu.skvortsova.chatbot.bot.client.HttpClientGoRest;
import ru.itis.kpfu.skvortsova.chatbot.bot.dto.CurrencyDto;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyService {

    private final String URL_1 = "https://api.currencyapi.com/v3/latest";
    private final String APIKEY="cur_live_wprauju3dUxe6wgxNUWoYLyWmHaw9esogeJmz2TN";

    public String getCurrencyRate() throws IOException, ParseException {
        String json = getCurrency();
        if (json.length() > 3) {
            CurrencyDto currency = new CurrencyDto(json);
            return currency.toString();
        }
        return null;
    }

    private String getCurrency() {
        HttpClientGoRest hc = new HttpClientGoRest();
        Map<String, String> mp = new HashMap<>();

        mp.put("apikey", APIKEY);
        return hc.get(URL_1, mp);
    }
}
