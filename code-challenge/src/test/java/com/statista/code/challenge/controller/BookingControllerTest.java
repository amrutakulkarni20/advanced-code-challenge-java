package com.statista.code.challenge.controller;

import Model.BookingModelTest;
import com.statista.code.challenge.BookingApplication;
import com.statista.code.challenge.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {BookingApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
public class BookingControllerTest {

    @Value("${server.port}")
    private String testPort;

    @Value("${application.test.host}")
    private String host;

    private HttpHeaders httpHeaders;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createsNewBookingAndVerifiesBookingIdWithStatusCode() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "USD", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingResponse = createNewBooking(booking);
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBody());
        assertNotNull(bookingResponse.getStatusCode());
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
        assertSame(1, bookingResponse.getBody().getBookingId());
    }

    @Test
    void createsNewBookingWithInvalidCurrencyAndVerifiesResponse() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "UUSSDD", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingResponse = createNewBooking(booking);
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getStatusCode());
        assertSame(HttpStatus.BAD_REQUEST, bookingResponse.getStatusCode());
    }

    @Test
    void createBookingWithInvalidEmailIdAndVerifiesResponse() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "USD", 1621544631000L, "email", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingResponse = createNewBooking(booking);
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBody());
        assertNotNull(bookingResponse.getStatusCode());
        assertSame(HttpStatus.BAD_REQUEST, bookingResponse.getStatusCode());
    }

    @Test
    void createBookingWithInvalidPriceAndVerifiesResponse() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 0, "USD", 1621544631000L, "email@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingResponse = createNewBooking(booking);
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBody());
        assertNotNull(bookingResponse.getStatusCode());
        assertSame(HttpStatus.BAD_REQUEST, bookingResponse.getStatusCode());
    }

    @Test
    void createBookingWithoutDepartmentAndVerifiesResponse() {
        BookingModelTest booking = createBookingWithoutDepartment();
        ResponseEntity<BookingModel> bookingResponse = createNewBooking(booking);
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBody());
        assertNotNull(bookingResponse.getStatusCode());
        assertSame(HttpStatus.BAD_REQUEST, bookingResponse.getStatusCode());
    }

    private BookingModelTest createBookingWithoutDepartment() {
        BookingModelTest booking = new BookingModelTest();
        booking.setDescription("Cool description!");
        booking.setPrice(10.00);
        booking.setCurrency("USD");
        booking.setSubscriptionStartDate(1621544631000L);
        booking.setEmail("email@gmail.com");
        return new BookingModelTest();
    }

    @Test
    void createBookingWithoutSubscriptionStartDateAndVerifiesResponse() {
        BookingModelTest booking = createBookingWithoutSubscriptionStartDate();
        ResponseEntity<BookingModel> bookingResponse = createNewBooking(booking);
        assertNotNull(bookingResponse);
        assertNotNull(bookingResponse.getBody());
        assertNotNull(bookingResponse.getStatusCode());
        assertSame(HttpStatus.BAD_REQUEST, bookingResponse.getStatusCode());
    }

    private BookingModelTest createBookingWithoutSubscriptionStartDate() {
        BookingModelTest booking = new BookingModelTest();
        booking.setDescription("Cool description!");
        booking.setPrice(10.00);
        booking.setCurrency("USD");
        booking.setEmail("email@gmail.com");
        booking.setDepartment(Departments.valueOf("Pharma"));
        return new BookingModelTest();
    }

    @Test
    void getsBookingByIdSuccessfully() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "USD", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse = createNewBooking(booking);
        ResponseEntity<BookingModel> bookingResponse = getBooking(bookingCreationResponse.getBody().getBookingId());
        assertNotNull(bookingResponse);
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
        assertSame(bookingCreationResponse.getBody().getBookingId(), bookingResponse.getBody().getBookingId());
    }

    @Test
    void getsBookingByIdWithoutCreatingBooking() {
        ResponseEntity<BookingModel> bookingResponse = getBooking(102);
        assertNotNull(bookingResponse);
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
        assertNull(bookingResponse.getBody());
    }

    @Test
    void getsBookingByDepartmentSuccessfully() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "USD", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse1 = createNewBooking(booking);
        ResponseEntity<BookingModel> bookingCreationResponse2 = createNewBooking(booking);
        ResponseEntity<BookingIds> bookingResponse = getBookingByDepartment(bookingCreationResponse1.getBody().getDepartment().name());
        assertNotNull(bookingResponse);
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
    }

    @Test
    void getsEmptyBookingIdListWhenDepartmentNotFound() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "USD", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse1 = createNewBooking(booking);
        ResponseEntity<BookingModel> bookingCreationResponse2 = createNewBooking(booking);
        ResponseEntity<BookingIds> bookingResponse = getBookingByDepartment("test");
        assertNotNull(bookingResponse);
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
        assertNull(bookingResponse.getBody().getBookingIds());
    }

    @Test
    void getUsedCurrenciesListSuccessfully() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "USD", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse1 = createNewBooking(booking);
        BookingModelTest booking2 = new BookingModelTest("Cool description!", 10.00, "INR", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse2 = createNewBooking(booking2);
        ResponseEntity<Currencies> bookingResponse = getUsedCurrencyList();
        assertNotNull(bookingResponse);
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
    }


    @Test
    void getSumOfBookingPriceByGivenCurrencySuccessfully() {
        BookingModelTest booking = new BookingModelTest("Cool description!", 10.00, "INR", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse1 = createNewBooking(booking);
        BookingModelTest booking2 = new BookingModelTest("Cool description!", 10.00, "INR", 1621544631000L, "abc@gmail.com", Departments.valueOf("Pharma"));
        ResponseEntity<BookingModel> bookingCreationResponse2 = createNewBooking(booking2);
        ResponseEntity<Double> bookingResponse = getSumOfBookingPriceByGivenCurrency("INR");
        assertNotNull(bookingResponse);
        assertSame(HttpStatus.OK, bookingResponse.getStatusCode());
    }

    @Test
    void createsNewBusinessAndVerifiesStatusCode() {
        BusinessModel business = new BusinessModel(Departments.valueOf("Pharma"), "NRW", "Basic", "test@gmail.com");
        int bookingId = 1;
        ResponseEntity<BusinessModel> businessResponse = createNewBusiness(bookingId, business);
        assertNotNull(businessResponse);
        assertNotNull(businessResponse.getStatusCode());
        assertSame(HttpStatus.OK, businessResponse.getStatusCode());
    }

    @Test
    void getBusinessSuccessfully() {
        BusinessModel business = new BusinessModel(Departments.valueOf("Pharma"), "NRW", "Basic", "test@gmail.com");
        int bookingId = 1;
        ResponseEntity<BusinessModel> createBusinessResponse = createNewBusiness(bookingId, business);
        ResponseEntity<BusinessModel> getBusiness = getBusinessSuccessfullyFromBookingId(bookingId);
        assertNotNull(getBusiness);
        assertSame(HttpStatus.OK, getBusiness.getStatusCode());
    }


    private ResponseEntity<BusinessModel> createNewBusiness(int bookingId, BusinessModel business) {
        HttpEntity<BusinessModel> businessRequest = new HttpEntity<>(business, getHttpHeader());
        ResponseEntity<BusinessModel> businessResponse = restTemplate.postForEntity((String) createURLForDoBusiness("/bookingService/doBusiness/", host, testPort, bookingId), businessRequest, BusinessModel.class);
        return businessResponse;
    }

    private ResponseEntity<BusinessModel> getBusinessSuccessfullyFromBookingId(int bookingId) {
        HttpEntity<BusinessModel> entity = new HttpEntity<>(getHttpHeader());
        ResponseEntity<BusinessModel> business = restTemplate.exchange(createURLForGetRequest("/bookingService/getBusiness/", host, testPort, bookingId), HttpMethod.GET, entity, BusinessModel.class);
        return business;
    }

    private ResponseEntity<BookingModel> createNewBooking(BookingModelTest booking) {
        HttpEntity<BookingModelTest> bookingRequest = new HttpEntity<>(booking, getHttpHeader());
        ResponseEntity<BookingModel> bookingResponse = restTemplate.postForEntity(createURLWithPort("/bookingService/booking", host, testPort), bookingRequest, BookingModel.class);
        return bookingResponse;
    }

    private ResponseEntity<BookingModel> getBooking(int bookingId) {
        HttpEntity<BookingModel> entity = new HttpEntity<>(getHttpHeader());
        ResponseEntity<BookingModel> booking = restTemplate.exchange(createURLForGetRequest("/bookingService/booking/", host, testPort, bookingId), HttpMethod.GET, entity, BookingModel.class);
        return booking;
    }

    private ResponseEntity<BookingIds> getBookingByDepartment(String department) {
        HttpEntity<ArrayList<Integer>> entity = new HttpEntity<>(getHttpHeader());
        ResponseEntity<BookingIds> bookings = restTemplate.exchange(createURLForGetBookingByDepartment("/bookingService/bookings/department/", host, testPort, department), HttpMethod.GET, entity, BookingIds.class);
        return bookings;
    }

    private ResponseEntity<Currencies> getUsedCurrencyList() {
        HttpEntity<HashSet<String>> entity = new HttpEntity<>(getHttpHeader());
        ResponseEntity<Currencies> bookings = restTemplate.exchange(createURLForgetUsedCurrencies("/bookingService/bookings/currencies", host, testPort), HttpMethod.GET, entity, Currencies.class);
        return bookings;
    }

    private ResponseEntity<Double> getSumOfBookingPriceByGivenCurrency(String currency) {
        HttpEntity<Double> entity = new HttpEntity<>(getHttpHeader());
        ResponseEntity<Double> bookings = restTemplate.exchange(createURLToGetSumOfCurrency("/bookingService/sum/", host, testPort, currency), HttpMethod.GET, entity, Double.class);
        return bookings;
    }

    private HttpHeaders getHttpHeader() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediatypeList = new ArrayList<>();
        mediatypeList.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediatypeList);
        return httpHeaders;
    }

    public static String createURLWithPort(final String uri, final String host,
                                           final String port) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append(uri);
        String result = stringBuilder.toString();
        return result;
    }

    public static String createURLForGetRequest(final String uri, final String host,
                                                final String port, final int bookingId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append(uri)
                .append(bookingId);
        return stringBuilder.toString();
    }

    public static String createURLForGetBookingByDepartment(final String uri, final String host,
                                                            final String port, final String department) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append(uri)
                .append(department);
        return stringBuilder.toString();
    }

    public static String createURLForgetUsedCurrencies(final String uri, final String host,
                                                       final String port) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append(uri);
        return stringBuilder.toString();
    }

    private static String createURLToGetSumOfCurrency(final String uri, final String host,
                                                      final String port, final String currency) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append(uri)
                .append(currency);
        return stringBuilder.toString();
    }

    private Object createURLForDoBusiness(String uri, String host, String testPort, int bookingId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://")
                .append(host)
                .append(":")
                .append(testPort)
                .append(uri)
                .append(bookingId);
        return stringBuilder.toString();
    }


}
