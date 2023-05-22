package com.statista.code.challenge.model;

import com.statista.code.challenge.util.Currency;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Price cannot be left blank.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0.")
    private double price;

    @NotNull(message = "Currency cannot be left blank.")
    private Currency currency;

    @NotNull(message = "Subscription date cannot be left blank.")
    private long subscriptionStartDate;

    @Email(message = "Please enter a valid email address.")
    private String email;

    @NotNull(message = "Department cannot be left blank.")
    private String department;

}
