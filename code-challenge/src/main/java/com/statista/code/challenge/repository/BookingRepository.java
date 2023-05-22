package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.Currencies;

import java.util.List;
import java.util.Set;

public interface BookingRepository {

    BookingModel save(BookingModel bookingModel);

    BookingModel update(int bookingId, BookingModel bookingModel);

    BookingModel findByBookingId(int bookingId);

    BookingIds findByDepartment(String department);

    double getSumByCurrency(String currency);

    Currencies getUsedCurrencies();
}
