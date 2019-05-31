/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on May 31, 2019 7:26:07 AM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.multithreading;

import com.odeng.maven.multithreading.util.NumberGenerator;

/**
 *
 * @author od3ng
 */
public class ThreadNumber extends Thread {

    private final NumberGenerator ng;

    public ThreadNumber(NumberGenerator ng) {
        this.ng = ng;
    }

    @Override
    public void run() {
        callGenerator();
    }

    private void callGenerator() {
        synchronized (ng) {
            for (int i = 0; i < 3; i++) {
                System.out.println(getName() + " " + ng.randomNumber());

            }
        }

    }
}
