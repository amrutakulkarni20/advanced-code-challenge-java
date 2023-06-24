package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.model.Departments;
import com.statista.code.challenge.service.Business;
import com.statista.code.challenge.service.BusinessFactory;
import com.statista.code.challenge.util.SingletonBookingMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class BusinessRepositoryImpl implements BusinessRepository{

    @Autowired
    private BusinessFactory businessFactory;

    @Override
    public void doBusiness(int bookingId, BusinessModel businessModel) {
        Business businessImplementation = businessFactory.getImplementation(businessModel.getDepartment());
        businessImplementation.doBusiness(bookingId,businessModel);
    }

    @Override
    public BusinessModel getBusiness(int bookingId) {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<Integer, BusinessModel> businessMap = bookings.getBusinessMap();
        return businessMap.get(bookingId);
    }
}
