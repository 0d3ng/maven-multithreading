/*
 * maven-multithreading

 * Copyright (c) 2019
 * All rights reserved.
 * Written by od3ng created on May 31, 2019 7:24:51 AM
 * Email   : lepengdados@gmail.com
 * Github  : 0d3ng
 * Hp      : 085878554150
 */
package com.odeng.maven.multithreading.util;

import java.util.Random;

/**
 *
 * @author od3ng
 */
public class NumberGenerator {

    private final int low;
    private final int high;

    public NumberGenerator(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public int randomNumber() {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        return result;
    }
}
