package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BookingModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public interface BookingRepository {

    void save(BookingModel bookingModel);

    void update(BookingModel bookingModel, int bookingId);

    BookingModel findByBookingId(int bookingId);

    List<Integer> findByDepartment(String department);

    double getSumByCurrency(String currency);

    Set<String> getUsedCurrencies();



    }
