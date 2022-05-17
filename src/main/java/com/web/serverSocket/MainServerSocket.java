package com.web.serverSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServerSocket {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(300);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        while(true) {
            final Socket socket = serverSocket.accept();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        writer.write("Hello there!");
                        writer.flush();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            executorService.submit(runnable);
        }

        /*
         Socket socket = serverSocket.accept();
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8;");
        writer.println("Connection: Keep-Alive\n");

        System.out.print("Type here: ");
        Scanner scanner = new Scanner(System.in);
        String scannedLine;

        while(!(scannedLine = scanner.nextLine()).equals("exit")) {
            writer.println(scannedLine);
            writer.flush();
        }

        scanner.close();
        socket.close();
         */
    }
}
