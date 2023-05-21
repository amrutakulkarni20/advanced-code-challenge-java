package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BookingModel;

import java.util.List;
import java.util.Set;

public interface BookingDataRepository {

    BookingModel save(BookingModel bookingModel);

    BookingModel update(int bookingId, BookingModel bookingModel);

    BookingModel findByBookingId(int bookingId);

    List<Integer> findByDepartment(String department);

    double getSumByCurrency(String currency);

    Set<String> getUsedCurrencies();



    }
