package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.repository.BookingRepository;
import com.statista.code.challenge.repository.BookingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public void createBooking(BookingModel bookingModel) {
        bookingRepository.save(bookingModel);
    }

    @Override
    public void updateBooking(BookingModel bookingModel, int bookingId) {
        bookingRepository.update(bookingModel, bookingId);
    }

    @Override
    public BookingModel getBookingById(int bookingId) {
        return bookingRepository.findByBookingId(bookingId);
    }
    @Override
    public List<Integer> getBookingByDepartment(String department) {
        return bookingRepository.findByDepartment(department);
    }
    @Override
    public Set<String> getUsedCurrencies() {
        return bookingRepository.getUsedCurrencies();
    }

    @Override
    public double getSumByCurrency(String currency) {
        return bookingRepository.getSumByCurrency(currency);
    }


}
