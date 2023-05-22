package com.statista.code.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class BookingIds {

    public BookingIds(){}

    public BookingIds(List<Integer> bookingIds) {
        this.bookingIds = bookingIds;
    }

    public List<Integer> getBookingIds() {
        return bookingIds;
    }

    public void setBookingIds(List<Integer> bookingIds) {
        this.bookingIds = bookingIds;
    }

    private List<Integer> bookingIds = new ArrayList<>();
}
