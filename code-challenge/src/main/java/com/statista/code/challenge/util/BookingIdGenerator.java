package com.statista.code.challenge.util;

public class BookingIdGenerator {

    private static int counter = 0;
    public static synchronized int generateBookingId() {
        counter++;
        return counter;
    }
}




