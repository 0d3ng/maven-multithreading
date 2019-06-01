/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on Jun 1, 2019 4:04:06 PM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author od3ng
 */
public class EchoMultiClient {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            System.out.println("Starting server on port " + port);
            serverSocket = new ServerSocket(port);
            while (true) {
                new EchoClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoMultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class EchoClientHandler extends Thread {

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
            System.out.println("Request from " + socket.getInetAddress().getHostAddress());
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("Jazzakalloh ^_^");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(EchoClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
