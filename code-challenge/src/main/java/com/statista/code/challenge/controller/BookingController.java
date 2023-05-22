package com.statista.code.challenge.controller;

import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.model.Currencies;
import com.statista.code.challenge.service.BookingService;
import javax.validation.Valid;
import com.statista.code.challenge.service.IBookingController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookingService")
public class BookingController implements IBookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public BookingModel createBooking(@Valid @RequestBody BookingModel bookingModel) {
        return bookingService.createBooking(bookingModel);
    }

    @PutMapping("/bookings/{bookingId}")
    public BookingModel updateBooking(@PathVariable("bookingId") int bookingId, @RequestBody BookingModel bookingModel) {
        return bookingService.updateBooking(bookingId,bookingModel);
    }

    @GetMapping("/booking/{bookingId}")
    public BookingModel getBookingById(@PathVariable("bookingId") int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/bookings/department/{department}")
    public BookingIds getBookingByDepartment(@PathVariable("department") String department){
        return bookingService.getBookingByDepartment(department);
    }

    @GetMapping("/bookings/currencies")
    public Currencies getUsedCurrencies(){
        return bookingService.getUsedCurrencies();
    }

    @GetMapping("/sum/{currency}")
    public double getSumByCurrency(@PathVariable("currency") String currency){
        return bookingService.getSumByCurrency(currency);
    }

    @PostMapping("/doBusiness/{bookingId}")
    public void doBusiness(@PathVariable("bookingId") int bookingId, @Valid @RequestBody BusinessModel businessModel) {
        bookingService.doBusiness(bookingId, businessModel);
    }

    @GetMapping("/getBusiness/{bookingId}")
    public BusinessModel getBusiness(@PathVariable("bookingId") int bookingId) {
       return bookingService.getBusiness(bookingId);
    }
}
