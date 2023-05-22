package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.Currencies;
import com.statista.code.challenge.util.BookingIdGenerator;
import com.statista.code.challenge.util.SingletonBookingMap;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class BookingRepositoryImpl implements BookingRepository {

    public BookingModel save(BookingModel bookingModel){
        saveBookingByBookingId(bookingModel);
        saveBookingByDepartment(bookingModel);
        saveBookingByCurrency(bookingModel);
        return bookingModel;
    }

    private void saveBookingByBookingId(BookingModel bookingModel){
        int bookingId = BookingIdGenerator.generateBookingId();
        bookingModel.setBookingId(bookingId);
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<Integer, BookingModel> bookingMap = bookings.getBookingMap();
        bookingMap.put(bookingId, bookingModel);
    }

    private void saveBookingByDepartment(BookingModel bookingModel) {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<String, List<Integer>> departmentMap = bookings.getDepartmentMap();
        if (departmentMap.containsKey(bookingModel.getDepartment().name())) {
            List<Integer> bookingModelList = departmentMap.get(bookingModel.getDepartment().name());
            bookingModelList.add(bookingModel.getBookingId());
        } else {
            departmentMap.put(bookingModel.getDepartment().name(), new ArrayList<>(Arrays.asList(bookingModel.getBookingId())));
        }
    }
    private void saveBookingByCurrency(BookingModel bookingModel){
        String currency = bookingModel.getCurrency().name();
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<String, Double> currencyMap = bookings.getCurrencyMap();
        if(currencyMap.containsKey(currency)){
            double amount = currencyMap.get(currency) + bookingModel.getPrice();
            currencyMap.put(currency,amount);
        }else{
            currencyMap.put(currency,bookingModel.getPrice());
        }
    }

    public BookingModel update(int bookingId, BookingModel bookingModel) {
        bookingModel.setBookingId(bookingId);
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<Integer, BookingModel> bookingMap = bookings.getBookingMap();
        bookingMap.put(bookingId, bookingModel);
        return bookingModel;
    }

    public BookingModel findByBookingId(int id) {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<Integer, BookingModel> bookingMap = bookings.getBookingMap();
        return bookingMap.get(id);
    }

    public BookingIds findByDepartment(String department) {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<String, List<Integer>> departmentMap = bookings.getDepartmentMap();
        return new BookingIds(departmentMap.get(department));
    }

    public double getSumByCurrency(String currency) {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<String, Double> currencyMap = bookings.getCurrencyMap();
        return currencyMap.get(currency);
    }

    public Currencies getUsedCurrencies() {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<String, Double> currencyMap = bookings.getCurrencyMap();
        return new Currencies(currencyMap.keySet());
    }
}
