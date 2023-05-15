package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.util.BookingIdGenerator;
import com.statista.code.challenge.util.SingletonBookingMap;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookingRepositoryImpl implements BookingRepository{

    public void save (BookingModel bookingModel){
        saveBookingByBookingId(bookingModel);
        saveBookingByDepartment(bookingModel);
        saveBookingByCurrency(bookingModel);
    }

    private void saveBookingByBookingId(BookingModel bookingModel){
        int  bookingId = BookingIdGenerator.generateBookingId();
        bookingModel.setBookingId(bookingId);
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<Integer, BookingModel> bookingMap = singletonMap1.getBookingMap();
        bookingMap.put(bookingId, bookingModel);
    }
    private void saveBookingByDepartment(BookingModel bookingModel) {
        String department = bookingModel.getDepartment();
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<String, List<Integer>> departmentMap = singletonMap1.getDepartmentMap();
        if(departmentMap.containsKey(department)){
            List<Integer>bookingModelList = departmentMap.get(department);
            bookingModelList.add(bookingModel.getBookingId());
        }else{
            departmentMap.put(department,new ArrayList<>(List.of(bookingModel.getBookingId())));
        }
    }
    private void saveBookingByCurrency(BookingModel bookingModel){
        String currency = bookingModel.getCurrency();
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<String, Double> currencyMap = singletonMap1.getCurrencyMap();
        if(currencyMap.containsKey(currency)){
            double amount = currencyMap.get(currency) + bookingModel.getPrice();
            currencyMap.put(currency,amount);
        }else{
            currencyMap.put(currency,bookingModel.getPrice());
        }
    }

    public void update(BookingModel bookingModel, int bookingId) {
        bookingModel.setBookingId(bookingId);
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<Integer, BookingModel> bookingMap = singletonMap1.getBookingMap();
        bookingMap.put(bookingId, bookingModel);
    }
    public BookingModel findByBookingId(int id) {
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<Integer, BookingModel> bookingMap = singletonMap1.getBookingMap();
        BookingModel booking = bookingMap.entrySet().stream()
                .filter(n -> n.getKey().equals(id))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
        return booking;
    }

    public List<Integer> findByDepartment(String department) {
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<String, List<Integer>> departmentMap = singletonMap1.getDepartmentMap();
        List<Integer> bookingIds = departmentMap.get(department);
        return bookingIds;
    }

    public double getSumByCurrency(String currency) {
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<String, Double> currencyMap = singletonMap1.getCurrencyMap();
        return currencyMap.get(currency);
    }

    public Set<String> getUsedCurrencies() {
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<String, Double> currencyMap = singletonMap1.getCurrencyMap();
        return currencyMap.keySet();
    }

}
