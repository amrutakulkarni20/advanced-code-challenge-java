package service;

import com.statista.code.challenge.model.BookingIdResponse;
import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.Currencies;
import com.statista.code.challenge.repository.BookingDataRepository;
import com.statista.code.challenge.service.BookingService;
import com.statista.code.challenge.service.BookingServiceImpl;
import com.statista.code.challenge.service.NotificationService;
import com.statista.code.challenge.util.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private BookingDataRepository bookingDataRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    public void createsNewBookingAndVerifiesDepartment() {
        BookingModel booking = createBookingRequest();
        bookingService.createBooking(booking);
        ArgumentCaptor<BookingModel> bookingModelArgumentCaptor = ArgumentCaptor.forClass(BookingModel.class);
        verify(bookingDataRepository, times(1)).save(bookingModelArgumentCaptor.capture());
        BookingModel bookingResponse = bookingModelArgumentCaptor.getValue();
        assertNotNull(bookingResponse);
        assertSame("Pharma",bookingResponse.getDepartment());
    }

    private BookingModel createBookingRequest() {
        BookingModel bookingModel = new BookingModel();
        bookingModel.setDepartment("Pharma");
        bookingModel.setPrice(10.00);
        bookingModel.setCurrency(Currency.valueOf("USD"));
        bookingModel.setSubscriptionStartDate(1621544631000L);
        bookingModel.setEmail("abc@gmail.com");
        bookingModel.setDescription("Cool description!");
        return bookingModel;
    }

    @Test
    public void getBookingByBookingIdSuccessfully(){
        BookingModel bookingModel = new BookingModel(1,"Cool Description",10.00, Currency.valueOf("USD"),1621544631000L,"abc@gmail.com","Pharma");
        when(bookingDataRepository.findByBookingId(anyInt())).thenReturn(bookingModel);
        BookingModel booking = bookingService.getBookingById(1);
        assertNotNull(booking);
        assertSame("Pharma",booking.getDepartment());
    }

    @Test
    public void getBookingByDepartmentSuccessfully(){
        List<Integer> bookingIdList = new ArrayList<>();
        bookingIdList.add(1);
        bookingIdList.add(2);
        when(bookingDataRepository.findByDepartment(anyString())).thenReturn(bookingIdList);
        BookingIdResponse bookingIdResponse = bookingService.getBookingByDepartment("Pharma");
        assertNotNull(bookingIdResponse);
        assertSame(bookingIdList,bookingIdResponse.getBookingIds());
    }

    @Test
    public void getUsedCurrenciesSuccessfully(){
        Set<String> currencyList = new HashSet<>();
        currencyList.add("USD");
        currencyList.add("INR");
        currencyList.add("EUR");
        when(bookingDataRepository.getUsedCurrencies()).thenReturn(currencyList);
        Currencies currencies = bookingService.getUsedCurrencies();
        assertNotNull(currencies);
        assertSame(currencyList,currencies.getCurrencySet());
    }

    @Test
    public void getSumByCurrencySuccessfully(){
        double totalAmount = 90.0;
        String currency = "INR";
        double delta = 0.000001;
        when(bookingDataRepository.getSumByCurrency(currency)).thenReturn(totalAmount);
        double amountResponse = bookingService.getSumByCurrency(currency);
        assertNotNull(amountResponse);
        assertEquals(totalAmount,amountResponse,delta);
    }

}
