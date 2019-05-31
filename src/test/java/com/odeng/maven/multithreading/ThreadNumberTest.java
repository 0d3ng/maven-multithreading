/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on May 31, 2019 7:30:35 AM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.multithreading;

import com.odeng.maven.multithreading.util.NumberGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author od3ng
 */
public class ThreadNumberTest {

    public ThreadNumberTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class ThreadNumber.
     */
    @Test
    public void testRun() {
        NumberGenerator generator = new NumberGenerator(1000, 2000);
        for (int i = 0; i < 3; i++) {
            new ThreadNumber(generator).start();
        }
    }

}
