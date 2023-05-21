package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.repository.BookingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public List<Integer> getBookingByDepartment(String department) {
        return bookingDataRepository.findByDepartment(department);
    }

    @Override
    public Set<String> getUsedCurrencies() {
        return bookingDataRepository.getUsedCurrencies();
    }

    @Override
    public double getSumByCurrency(String currency) {
        return bookingDataRepository.getSumByCurrency(currency);
    }


}
