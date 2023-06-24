package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.model.Currencies;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    BookingModel createBooking(BookingModel bookingModel);

    BookingModel updateBooking(int bookingId, BookingModel bookingModel);

    BookingModel getBookingById(int bookingId);

    BookingIds getBookingByDepartment(String department);

    double getSumByCurrency(String currency);

    Currencies getUsedCurrencies();

    void doBusiness(int bookingId, BusinessModel businessModel);

    BusinessModel getBusiness(int bookingId);

}
