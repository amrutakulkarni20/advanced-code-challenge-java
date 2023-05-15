package com.statista.code.challenge.controller;

import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bookingservice")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @PostMapping("/booking")
    public ResponseEntity createBooking(@RequestBody BookingModel bookingModel) {
        bookingService.createBooking(bookingModel);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/bookings/{bookingId}")
    public ResponseEntity updateBooking(@RequestBody BookingModel bookingModel,@PathVariable("bookingId") int bookingId) {
        bookingService.updateBooking(bookingModel,bookingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/booking/{bookingId}")
    public BookingModel getBookingById(@PathVariable("bookingId") int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/bookings/department/{department}")
    public List<Integer> getBookingByDepartment(@PathVariable("department") String department){
        return bookingService.getBookingByDepartment(department);
    }
    @GetMapping("/bookings/currencies")
    public Set<String> getUsedCurrencies(){
        return bookingService.getUsedCurrencies();
    }
    @GetMapping("/sum/{currency}")
    public double getSumByCurrency(@PathVariable("currency") String currency){
        return bookingService.getSumByCurrency(currency);
    }
}
