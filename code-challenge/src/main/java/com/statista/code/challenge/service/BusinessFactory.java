package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessFactory {

    @Autowired
    private ECommerceDepartmentImpl ECommerceDepartmentImpl;

    @Autowired
    private PharmaDepartmentImpl pharmaDepartmentImpl;

    public Business getImplementation(Departments department){

        switch (department){
            case ECommerce -> {
                return ECommerceDepartmentImpl;
            } case Pharma -> {
                return pharmaDepartmentImpl;
            } default -> throw new IllegalStateException("Unexpected value of department" +department);
        }
    }
}
