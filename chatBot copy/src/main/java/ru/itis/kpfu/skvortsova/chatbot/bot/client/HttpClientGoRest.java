package ru.itis.kpfu.skvortsova.chatbot.bot.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


//in the case of a get request, the Map arguments are treated as parameters for the request,
//in the other cases, they are treated as information to be posted(edited, deleted)
public class HttpClientGoRest implements HttpClient {

    private String displayContent(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String input;
            while ((input = br.readLine()) != null) {
                content.append(input);
            }
        }
        return content.toString();
    }

    private void writeContent(HttpURLConnection connection, String information) throws IOException {
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = information.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    @Override
    public String get(String url, Map<String, String> params) {
        try {
            URLBuilder urlBuilder = new URLBuilder(url).addParams(params, false);
            URL url_1 = new URL(urlBuilder.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url_1.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            int code=connection.getResponseCode();
            String content;
            if (code==HttpURLConnection.HTTP_OK) {
                content = displayContent(connection);
            }else{
                content=Integer.toString(code);
            }
            connection.disconnect();
            return content;
        } catch (IOException e) {
            return e.toString();
        }
    }

    @Override
    public String post(String url, Map<String, String> params) {
        try {
            URLBuilder urlBuilder = new URLBuilder(url).addParams(params, true);
            URL url_1 = new URL(urlBuilder.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url_1.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            writeContent(connection, urlBuilder.getPostInf());

            String content = displayContent(connection);
            connection.disconnect();
            return content;
        } catch (IOException e) {
            return e.toString();
        }
    }

    @Override
    public String put(String url, Map<String, String> params) {
        try {
            URLBuilder urlBuilder = new URLBuilder(url).addParams(params, true);
            URL url_1 = new URL(urlBuilder.getId().getUrl());
            HttpURLConnection connection = (HttpURLConnection) url_1.openConnection();

            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            writeContent(connection, urlBuilder.getPostInf());

            String content = displayContent(connection);
            connection.disconnect();
            return content;
        } catch (IOException e) {
            return e.toString();
        }
    }

    @Override
    public String delete(String url, Map<String, String> params) {
        try {
            URLBuilder urlBuilder = new URLBuilder(url).addParams(params, true);
            URL url_1 = new URL(urlBuilder.getId().getUrl());
            HttpURLConnection connection = (HttpURLConnection) url_1.openConnection();

            connection.setRequestMethod("DELETE");

            String content = displayContent(connection);
            connection.disconnect();
            return content;
        } catch (IOException e) {
            return e.toString();
        }
    }

    private class URLBuilder {
        private final String REQUIRED_INF = "id";
        private String url;
        private StringBuilder params = new StringBuilder();
        private Map<String, String> map = new HashMap<>();

        public URLBuilder(String url) {
            this.url = url;
        }

        //isEditing=false-for get
        //isEditing=true-for post, put, delete
        public URLBuilder addParams(Map<String, String> mp, boolean isEditing) {
            this.map = mp;
            if (!map.keySet().isEmpty()) {
                for (String i : map.keySet()) {
                    params.append(i + "=" + map.get(i) + "&");
                }
                params.deleteCharAt(params.length() - 1);
                if (!isEditing) {
                    url += "?" + params;
                }
            }
            return this;
        }

        public String getPostInf() {
            return params.toString();
        }

        public URLBuilder getId() {
            url += "/" + map.get(REQUIRED_INF);
            return this;
        }

        public String getUrl() {
            return url;
        }
    }
}
