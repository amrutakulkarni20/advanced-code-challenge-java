package com.statista.code.challenge.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.statista.code.challenge.util.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

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
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime subscriptionStartDate;

    @Email(message = "Please enter a valid email address.")
    private String email;

    @NotNull(message = "Department cannot be left blank.")
    private String department;

}
