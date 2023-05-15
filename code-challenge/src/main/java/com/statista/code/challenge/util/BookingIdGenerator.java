package com.statista.code.challenge.util;

import java.time.LocalDateTime;
import java.util.Random;

public class BookingIdGenerator {

    private static int counter = 0;
    public static synchronized int generateBookingId() {
        counter++;
        return counter;
    }
}




