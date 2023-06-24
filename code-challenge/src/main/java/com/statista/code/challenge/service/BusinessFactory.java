package com.statista.code.challenge.service;

import com.statista.code.challenge.model.Departments;
import org.springframework.stereotype.Component;

@Component
public class BusinessFactory {

    private ECommerceDepartmentImpl eCommerceDepartmentImpl;

    private PharmaDepartmentImpl pharmaDepartmentImpl;

    public BusinessFactory(ECommerceDepartmentImpl eCommerceDepartmentImpl, PharmaDepartmentImpl pharmaDepartmentImpl){
        this.eCommerceDepartmentImpl = eCommerceDepartmentImpl;
        this.pharmaDepartmentImpl = pharmaDepartmentImpl;
    }

    public Business getImplementation(Departments department){

        switch (department){
            case ECommerce : {
                return eCommerceDepartmentImpl;
            } case Pharma : {
                return pharmaDepartmentImpl;
            } default : throw new IllegalStateException("Unexpected value of department" +department);
        }
    }
}
