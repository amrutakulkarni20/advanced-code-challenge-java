package com.statista.code.challenge.controller;

import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bookingService")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public BookingModel createBooking(@Valid @RequestBody BookingModel bookingModel) {
        return bookingService.createBooking(bookingModel);

    }

    @PostMapping("/bookings/{bookingId}")
    public BookingModel updateBooking(@PathVariable("bookingId") int bookingId, @RequestBody BookingModel bookingModel) {
        return bookingService.updateBooking(bookingId,bookingModel);

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
