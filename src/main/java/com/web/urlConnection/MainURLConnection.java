package com.web.urlConnection;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainURLConnection {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://imdb.com");
            URLConnection urlConnection = url.openConnection();

            BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            int i=0;

            while((i=inputStream.read()) > 0) {
                char ch = (char) i;
                System.out.print(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
