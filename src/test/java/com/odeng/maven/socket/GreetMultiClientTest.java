/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on Jun 1, 2019 4:12:33 PM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author od3ng
 */
public class GreetMultiClientTest {

    EchoMultiClient server;
    private final int PORT = 9000;
    private final String IP = "localhost";

    public GreetMultiClientTest() {
    }

    @Before
    public void setUp() {
        server = new EchoMultiClient();
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testSendMessage() {
        new Thread(() -> {
            server.start(PORT);
        }).start();
        GreetClient client = new GreetClient();
        client.startConnection(IP, PORT);
        String message = "What'up!";
        assertEquals(client.sendMessage(message), message);
        message = "Ada apa?";
        assertEquals(client.sendMessage(message), message);
        String endMessage = "Jazzakalloh ^_^";
        assertEquals(client.sendMessage("."), endMessage);
        
        GreetClient client1 = new GreetClient();
        client1.startConnection(IP, PORT);
        message = "Py kabare?";
        assertEquals(client1.sendMessage(message), message);
        endMessage = "Jazzakalloh ^_^";
        assertEquals(client1.sendMessage("."), endMessage);
    }
}
