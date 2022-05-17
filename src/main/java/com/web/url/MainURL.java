package com.web.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainURL {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://test.com/path-to-file/a/file.pdf");
            System.out.println("Protocol: "+url.getProtocol());
            System.out.println("Host: "+url.getHost());
            System.out.println("Path: "+url.getPath());
            System.out.println("File: "+url.getFile());
            System.out.println("Port: "+url.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
