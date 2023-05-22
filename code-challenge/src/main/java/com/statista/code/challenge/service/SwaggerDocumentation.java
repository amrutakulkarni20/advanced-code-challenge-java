package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.model.Currencies;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(tags = "Booking Service Application", value = "")
public interface SwaggerDocumentation {

    @ApiOperation(value = "Create new Booking", notes = "This API creates a new booking and sends an e-mail with the details.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public BookingModel createBooking(@RequestBody BookingModel bookingModel);

    @ApiOperation(value = "Update existing Booking", notes = "This API Update or replace the existing booking if already exists.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public BookingModel updateBooking(@PathVariable("bookingId") int bookingId, @RequestBody BookingModel bookingModel);

    @ApiOperation(value = "Get Booking by given bookingId", notes = "This API returns the specified booking as JSON.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public BookingModel getBookingById(@PathVariable("bookingId") int bookingId);

    @ApiOperation(value = "Get Booking Ids from given Department", notes = "This API returns a JSON list of all bookings ids with the given department.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public BookingIds getBookingByDepartment(@PathVariable("department") String department);

    @ApiOperation(value = "Get List of used currencies", notes = "This API returns a JSON list with all used currencies in the existing bookings.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public Currencies getUsedCurrencies();

    @ApiOperation(value = "Get Sum of Price from given currency", notes = "This API returns the sum of all bookings prices with the given currency.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public double getSumByCurrency(@PathVariable("currency") String currency);

    @ApiOperation(value = "Create Business", notes = "This API creates the Business for the given booking corresponding department.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public void doBusiness(@PathVariable("bookingId") int bookingId, @RequestBody BusinessModel businessModel);

    @ApiOperation(value = "get Business", notes = "This API returns the result of Business for the given booking corresponding department.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully.")})
    @ApiResponse(code = 400, message = "Input request Validation Failed.")
    public BusinessModel getBusiness(@PathVariable("bookingId") int bookingId);
}
