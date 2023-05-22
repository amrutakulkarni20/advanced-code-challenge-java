package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BookingModel;
import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.model.Notification;
import com.statista.code.challenge.model.BookingIds;
import com.statista.code.challenge.model.Currencies;
import com.statista.code.challenge.model.Notification;
import com.statista.code.challenge.repository.BookingRepository;
import com.statista.code.challenge.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private NotificationService notificationService;
    private BusinessRepository businessRepository;

    private static final String SUBJECT_BOOKING = "Thank you for Booking!";

    private static final String BODY_BOOKING = "Dear Customer\n\n"
            +"Thank you for Booking.\n\n"
            +"We're excited to have you! Below, you'll find the information regarding your Booking\n\n"
            + "Booking Details: \n\n";

    private static final String SUBJECT_ACTIVATION = "Welcome to Statista !!!";

    private static final String BODY_ACTIVATION = "Dear Customer\n\n"
            +"Thank you for your interest in Statista. Welcome to the Statista Family\n\n"
            +"You're ready to start enjoying the Statista's services \n\n"
            + "Your Account Information: \n\n";

    public BookingServiceImpl(BookingRepository bookingRepository, NotificationService notificationService, BusinessRepository businessRepository){
        this.bookingRepository = bookingRepository;
        this.notificationService = notificationService;
        this.businessRepository = businessRepository;
    }

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.email}")
    private String senderEmail;

    @Override
    public BookingModel createBooking(BookingModel bookingModel) {
        BookingModel booking = bookingRepository.save(bookingModel);
        Notification notification = createBookingConfirmationEmail(bookingModel);
        notificationService.sendNotification(notification);
        return booking;
    }

    @Override
    public BookingModel updateBooking(int bookingId, BookingModel bookingModel) {
        return bookingRepository.update(bookingId, bookingModel);
    }

    @Override
    public BookingModel getBookingById(int bookingId) {
        return bookingRepository.findByBookingId(bookingId);
    }

    @Override
    public BookingIds getBookingByDepartment(String department) {
        return bookingRepository.findByDepartment(department);
    }

    @Override
    public Currencies getUsedCurrencies() {
        return bookingRepository.getUsedCurrencies();
    }

    @Override
    public double getSumByCurrency(String currency) {
        return bookingRepository.getSumByCurrency(currency);
    }

    @Override
    public void doBusiness(int bookingId, BusinessModel businessModel) {
        businessRepository.doBusiness(bookingId,businessModel);
        Notification notification = createBookingActivationEmail(businessModel);
        notificationService.sendNotification(notification);
    }

    private Notification createBookingConfirmationEmail(BookingModel bookingModel) {
        StringBuilder emailBody = new StringBuilder();
        Notification notification = new Notification();
        emailBody.append(BODY_BOOKING)
                .append("Booking Id: ").append(bookingModel.getBookingId()).append("\n")
                .append("Department: ").append( bookingModel.getDepartment()).append("\n");
        notification.setEmailFrom(senderEmail);
        notification.setEmailTo(bookingModel.getEmail());
        notification.setSubject(SUBJECT_BOOKING);
        notification.setBody(emailBody.toString());
        notification.setHost(host);
        return notification;
    }

    private Notification createBookingActivationEmail(BusinessModel businessModel) {
        StringBuilder emailBody = new StringBuilder();
        Notification notification = new Notification();
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearFromNow = currentDate.plusYears(1);
        emailBody.append(BODY_ACTIVATION)
                .append("Email: ").append(businessModel.getEmail()).append("\n")
                .append("Department: ").append(businessModel.getDepartment()).append("\n")
                .append("Business Plan: ").append(businessModel.getBusinessPlan()).append("\n")
                .append("Region: ").append(businessModel.getRegion()).append("\n")
                .append("Subscription start date: ").append(currentDate).append("\n")
                .append("Subscription end date: ").append(oneYearFromNow).append("\n");
        notification.setEmailFrom(senderEmail);
        notification.setEmailTo(businessModel.getEmail());
        notification.setSubject(SUBJECT_ACTIVATION);
        notification.setBody(emailBody.toString());
        notification.setHost(host);
        return notification;
    }

    @Override
    public BusinessModel getBusiness(int bookingId) {
        return businessRepository.getBusiness(bookingId);
    }
}
