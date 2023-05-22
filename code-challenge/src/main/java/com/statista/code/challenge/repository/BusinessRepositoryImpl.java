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
    BusinessFactory businessFactory;

    @Override
    public void doBusiness(int bookingId, BusinessModel businessModel) {
        String department = businessModel.getDepartment().name();
        Business businessImplementation = businessFactory.getImplementation(Departments.valueOf(department));
        businessImplementation.doBusiness(bookingId,businessModel);
    }

    @Override
    public BusinessModel getBusiness(int bookingId) {
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<Integer, BusinessModel> businessMap = singletonMap1.getBusinessMap();
        return businessMap.get(bookingId);
    }
}
