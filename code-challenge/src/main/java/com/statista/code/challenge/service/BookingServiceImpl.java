package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.Currencies;
import com.statista.code.challenge.repository.BookingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDataRepository bookingDataRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public BookingModel createBooking(BookingModel bookingModel) {
         BookingModel booking = bookingDataRepository.save(bookingModel);
        notificationService.sendEmailNotification(bookingModel);
        return booking;
    }

    @Override
    public BookingModel updateBooking(int bookingId, BookingModel bookingModel) {
        return bookingDataRepository.update(bookingId, bookingModel);
    }

    @Override
    public BookingModel getBookingById(int bookingId) {
        return bookingDataRepository.findByBookingId(bookingId);
    }

    @Override
    public BookingIds getBookingByDepartment(String department) {
        return new BookingIds(bookingDataRepository.findByDepartment(department));
    }

    @Override
    public Currencies getUsedCurrencies() {
        return new Currencies(bookingDataRepository.getUsedCurrencies());
    }

    @Override
    public double getSumByCurrency(String currency) {
        return bookingDataRepository.getSumByCurrency(currency);
    }


}
