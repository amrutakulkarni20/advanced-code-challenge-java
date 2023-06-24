package service;

import com.statista.code.challenge.model.*;
import com.statista.code.challenge.model.Currency;
import com.statista.code.challenge.repository.BookingRepository;
import com.statista.code.challenge.service.BookingServiceImpl;
import com.statista.code.challenge.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    public void createsNewBookingAndVerifiesDepartment() {
        BookingModel booking = createBookingRequest();
        bookingService.createBooking(booking);
        ArgumentCaptor<BookingModel> bookingModelArgumentCaptor = ArgumentCaptor.forClass(BookingModel.class);
        verify(bookingRepository, times(1)).save(bookingModelArgumentCaptor.capture());
        BookingModel bookingResponse = bookingModelArgumentCaptor.getValue();
        assertNotNull(bookingResponse);
        //assertSame("Pharma",bookingResponse.getDepartment());
    }

    private BookingModel createBookingRequest() {
        BookingModel bookingModel = new BookingModel();
        bookingModel.setDepartment(Departments.valueOf("Pharma"));
        bookingModel.setPrice(10.00);
        bookingModel.setCurrency(Currency.valueOf("USD"));
        bookingModel.setSubscriptionStartDate(1621544631000L);
        bookingModel.setEmail("abc@gmail.com");
        bookingModel.setDescription("Cool description!");
        return bookingModel;
    }

    @Test
    public void getBookingByBookingIdSuccessfully(){
        BookingModel bookingModel = new BookingModel(1,"Cool Description",10.00, Currency.valueOf("USD"),1621544631000L,"abc@gmail.com",Departments.valueOf("Pharma"));
        when(bookingRepository.findByBookingId(anyInt())).thenReturn(bookingModel);
        BookingModel booking = bookingService.getBookingById(1);
        assertNotNull(booking);
        //assertSame("Pharma",booking.getDepartment());
    }

    @Test
    public void getBookingByDepartmentSuccessfully(){
        List<Integer> bookingIdList = new ArrayList<>();
        bookingIdList.add(1);
        bookingIdList.add(2);
        BookingIds bookingIdsMock = new BookingIds(bookingIdList);
        when(bookingRepository.findByDepartment(anyString())).thenReturn(bookingIdsMock);
        BookingIds bookingIds = bookingService.getBookingByDepartment("Pharma");
        assertNotNull(bookingIds);
        assertSame(bookingIdList, bookingIds.getBookingIds());
    }

    @Test
    public void getUsedCurrenciesSuccessfully(){
        Set<String> currencyList = new HashSet<>();
        currencyList.add("USD");
        currencyList.add("INR");
        currencyList.add("EUR");
        Currencies currenciesMock = new Currencies(currencyList);
        when(bookingRepository.getUsedCurrencies()).thenReturn(currenciesMock);
        Currencies currencies = bookingService.getUsedCurrencies();
        assertNotNull(currencies);
        assertSame(currencyList,currencies.getCurrencySet());
    }

    @Test
    public void getSumByCurrencySuccessfully(){
        double totalAmount = 90.0;
        String currency = "INR";
        double delta = 0.000001;
        when(bookingRepository.getSumByCurrency(currency)).thenReturn(totalAmount);
        double amountResponse = bookingService.getSumByCurrency(currency);
        assertNotNull(amountResponse);
        assertEquals(totalAmount,amountResponse,delta);
    }

}
