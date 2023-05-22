package com.statista.code.challenge.service;

import com.statista.code.challenge.model.BusinessModel;
import com.statista.code.challenge.util.SingletonBookingMap;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ECommerceDepartmentImpl implements Business {

    @Override
    public void doBusiness(int bookingId, BusinessModel businessModel) {
        SingletonBookingMap bookings = SingletonBookingMap.getInstance();
        Map<Integer, BusinessModel> businessMap = bookings.getBusinessMap();
        businessMap.put(bookingId,businessModel);
    }
}
