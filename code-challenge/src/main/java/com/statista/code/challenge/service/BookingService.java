package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface BookingService {
    void createBooking(BookingModel bookingModel);

    BookingModel getBookingById(int bookingId);

    List<Integer> getBookingByDepartment(String department);

    double getSumByCurrency(String currency);

    Set<String> getUsedCurrencies();

    void updateBooking(BookingModel bookingModel, int bookingId);
}
