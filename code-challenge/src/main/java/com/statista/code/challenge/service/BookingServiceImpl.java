package com.statista.code.challenge.service;

import com.statista.code.challenge.constant.Constant;
import com.statista.code.challenge.model.*;
import com.statista.code.challenge.repository.BookingDataRepository;
import com.statista.code.challenge.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDataRepository bookingDataRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    BusinessRepository businessRepository;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.email}")
    private String senderEmail;

    @Override
    public BookingModel createBooking(BookingModel bookingModel) {
        BookingModel booking = bookingDataRepository.save(bookingModel);
        Notification notification = createBookingConfirmationEmail(bookingModel);
        notificationService.sendNotification(notification);
        return booking;
    }

    @Override
    public BookingModel updateBooking(int bookingId, BookingModel bookingModel) {
        return bookingDataRepository.update(bookingId, bookingModel);
    }

    @Override
    public BookingModel getBookingById(int bookingId) {
        return bookingDataRepository.findByBookingId(bookingId);
    }

    @Override
    public BookingIds getBookingByDepartment(String department) {
        return new BookingIds(bookingDataRepository.findByDepartment(department));
    }

    @Override
    public Currencies getUsedCurrencies() {
        return new Currencies(bookingDataRepository.getUsedCurrencies());
    }

    @Override
    public double getSumByCurrency(String currency) {
        return bookingDataRepository.getSumByCurrency(currency);
    }

    @Override
    public void doBusiness(int bookingId, BusinessModel businessModel) {
        businessRepository.doBusiness(bookingId,businessModel);
        Notification notification = createBookingActivationEmail(bookingId, businessModel);
        notificationService.sendNotification(notification);
    }

    private Notification createBookingConfirmationEmail(BookingModel bookingModel) {
        StringBuilder body = new StringBuilder();
        Notification notification = new Notification();
        body.append(Constant.BODY_BOOKING)
                .append("Booking Id: " + bookingModel.getBookingId() + "\n")
                .append("Department: " + bookingModel.getDepartment());
        notification.setEmailFrom(senderEmail);
        notification.setEmailTo(bookingModel.getEmail());
        notification.setSubject(Constant.SUBJECT_BOOKING);
        notification.setBody(body.toString());
        notification.setHost(host);
        return notification;
    }

    private Notification createBookingActivationEmail(int bookingId, BusinessModel businessModel) {
        StringBuilder body = new StringBuilder();
        Notification notification = new Notification();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearFromNow = currentDate.plusYears(1);
        body.append(Constant.BODY_ACTIVATION)
                .append("Email: " + businessModel.getEmail() + "\n")
                .append("Department: " + businessModel.getDepartment()+ "\n")
                .append("Business Plan: " + businessModel.getBusinessPlan()+ "\n")
                .append("Region: "+ businessModel.getRegion()+ "\n")
                .append("Subscription start date: "+ currentDate+ "\n")
                .append("Subscription end date: "+ oneYearFromNow+ "\n");
        notification.setEmailFrom(senderEmail);
        notification.setEmailTo(businessModel.getEmail());
        notification.setSubject(Constant.SUBJECT_ACTIVATION);
        notification.setBody(body.toString());
        notification.setHost(host);
        return notification;
    }

    @Override
    public BusinessModel getBusiness(int bookingId) {
        return businessRepository.getBusiness(bookingId);
    }
}
