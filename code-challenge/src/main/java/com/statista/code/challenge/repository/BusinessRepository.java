package com.statista.code.challenge.repository;

import com.statista.code.challenge.model.BusinessModel;

public interface BusinessRepository {

    void doBusiness(int bookingId, BusinessModel businessModel);

    BusinessModel getBusiness(int bookingId);
}
