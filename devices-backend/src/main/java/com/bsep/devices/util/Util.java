package com.bsep.devices.util;

import java.util.Random;

public class Util {
    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
