package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.util.SingletonBookingMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PharmaDepartmentImpl implements Business {
    @Override
    public void doBusiness(int bookingId, BusinessModel businessModel) {
        SingletonBookingMap singletonMap1 = SingletonBookingMap.getInstance();
        Map<Integer, BusinessModel> businessMap = singletonMap1.getBusinessMap();
        businessMap.put(bookingId,businessModel);
    }

}
