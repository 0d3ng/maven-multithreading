/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on Jun 1, 2019 2:32:20 PM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author od3ng
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GreetClientTest {

    GreetServer server;
    GreetClient instance;
    private final int PORT = 9000;
    private final String IP = "localhost";

    public GreetClientTest() {
    }

    @Before
    public void setUp() {
        server = new GreetServer();
        instance = new GreetClient();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of startConnection method, of class GreetClient.
     */
    @Test
    public void test1StartConnection() {
        System.out.println("startConnection");
        new Thread(() -> {
            server.start(PORT);
        }).start();
    }

    /**
     * Test of sendMessage method, of class GreetClient.
     */
    @Test
    public void test2SendMessage() {
        System.out.println("sendMessage");
        String msg = "hello server";
        String expResult = "hello client";
        instance.startConnection(IP, PORT);
        String result = instance.sendMessage(msg);
        assertEquals(expResult, result);
    }

    /**
     * Test of stopConnection method, of class GreetClient.
     */
    @Test
    public void test3StopConnection() {
        System.out.println("stopConnection");
        instance.stopConnection();
    }

}
