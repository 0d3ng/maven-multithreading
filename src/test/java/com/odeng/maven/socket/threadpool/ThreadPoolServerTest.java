/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on Jun 1, 2019 11:07:30 PM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.socket.threadpool;

import com.odeng.maven.socket.GreetClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author od3ng
 */
public class ThreadPoolServerTest {

    ThreadPoolServer server;
    private static final int NTHREADS = 20;
    private final int PORT = 9000;
    private final String IP = "localhost";

    public ThreadPoolServerTest() {
    }

    @Before
    public void setUp() {
        server = new ThreadPoolServer();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testSendMessage() {
        new Thread(() -> {
            server.start(PORT, NTHREADS);
        }).start();
        for (int i = 0; i < 100; i++) {
            GreetClient client = new GreetClient();
            client.startConnection(IP, PORT);

            String message = "What'up!";
            assertEquals(client.sendMessage(message), message);
            String endMessage = "Jazzakalloh ^_^";
            assertEquals(client.sendMessage("."), endMessage);
            System.out.println("Number of core pool : " + server.getExecutor().getCorePoolSize());
            System.out.println("Number of pool      : " + server.getExecutor().getPoolSize());
            System.out.println("Number of task count: " + server.getExecutor().getTaskCount());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadPoolServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
