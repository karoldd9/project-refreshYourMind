package com.web.innetAddress;

import java.net.InetAddress;

public class MainInnetAddress {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("google.com");
            System.out.println(ip.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
