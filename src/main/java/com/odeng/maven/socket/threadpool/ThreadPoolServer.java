/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on Jun 1, 2019 10:45:37 PM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.socket.threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author od3ng
 */
public class ThreadPoolServer {

    private ServerSocket serverSocket;
    private ThreadPoolExecutor executor;

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void start(int port, int nthreads) {
        try {
            System.out.println("Starting server on port " + port);
            serverSocket = new ServerSocket(port);
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nthreads);
            while (true) {
                executor.execute(new EchoClientHandler(serverSocket.accept()));
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadPoolServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        try {
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ThreadPoolServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class EchoClientHandler implements Runnable {

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
