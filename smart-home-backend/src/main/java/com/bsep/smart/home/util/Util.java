package com.bsep.smart.home.util;

import java.util.Random;

public class Util {
    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
