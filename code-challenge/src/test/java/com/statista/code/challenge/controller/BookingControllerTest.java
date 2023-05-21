/*
package com.statista.code.challenge.controller;

import com.statista.code.challenge.BookingApplication;
import com.statista.code.challenge.constant.Constant;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.service.BookingService;
import com.statista.code.challenge.util.Currency;
import org.junit.Assert;

import org.junit.Ignore;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {BookingApplication.class})
@TestPropertySource(locations = "classpath:test.properties")
@Ignore
 public class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Value("${server.port}")
    private String port;

    @Value("${application.test.host}")
    private String host;

    private HttpHeaders httpHeaders;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void createsNewBooking() {
        BookingModel booking = createMockBooking();
        HttpEntity<BookingModel> bookingRequest = new HttpEntity<>(booking,getHttpHeader());
        ResponseEntity<Integer> response = restTemplate.exchange(createURLWithPort("/bookingService/booking",host,port),HttpMethod.POST,bookingRequest,Integer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private HttpHeaders getHttpHeader(){
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediatypeList = new ArrayList<>();
        mediatypeList.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediatypeList);
        return httpHeaders;
    }

    public static String createURLWithPort(final String uri, final String host,
                                           final String port) {
        return "http://" + host + ":" + port + uri;
    }

    @Test
    public void testGetBookingsByBookingId() throws Exception {

        BookingModel booking = createMockBooking();
        Mockito.when(bookingService.getBookingById(1)).thenReturn(booking);
        mockMvc.perform(MockMvcRequestBuilders.get(Constant.URL, 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookingId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("CS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("USD"));
    }
    private BookingModel createMockBooking(){

        BookingModel booking = new BookingModel(1,"description",10.00, Currency.USD, LocalDateTime.now(),"valid4@email.ok","CS");
        return booking;
    }

    @Test
    void testGetBookingsByDepartment() throws Exception{
        List<Integer> bookings = createMockBookingIdList();
        Mockito.when(bookingService.getBookingByDepartment("CS")).thenReturn(bookings);
        List<Integer> bookingModelList = bookingService.getBookingByDepartment("CS");
        Assert.assertTrue(!bookingModelList.isEmpty());
    }
    private List<Integer> createMockBookingIdList(){
        List<Integer> bookingList = new ArrayList<>();
        bookingList.add(1);
        bookingList.add(2);
        return bookingList;
    }

}
*/
