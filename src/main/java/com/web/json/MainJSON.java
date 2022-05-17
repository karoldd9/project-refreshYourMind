package com.web.json;

import org.codehaus.httpcache4j.uri.URIBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainJSON {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=PLN");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode() != 200) {
            System.out.println("Can't connect to "+url.getHost());
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuffer content = new StringBuffer();
        while((line = bufferedReader.readLine()) != null) {
            content.append(line+"\n");
        }

        String jsonStr = content.toString();
        System.out.println(jsonStr);
    }
}
