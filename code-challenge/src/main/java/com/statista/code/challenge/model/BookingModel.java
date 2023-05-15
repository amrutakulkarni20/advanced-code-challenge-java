package com.statista.code.challenge.model;

import com.statista.code.challenge.util.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingModel {

    private int bookingId;

    private String description;

    private double price;

    private String currency;

    private long subscriptionStartDate;

    private String email;

    private String department;

}
