package com.web.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;

public class MainSocket {
    public static void main(String[] args) throws Exception{
        //Download web page

        Socket socket = new Socket("facebook.com", 80);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        bw.write("GET / HTTP /1.1\n\n");
        bw.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
