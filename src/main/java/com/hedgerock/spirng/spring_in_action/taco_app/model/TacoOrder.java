package com.hedgerock.spirng.spring_in_action.taco_app.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {

    private Long id;

    private LocalDate createdAt;

    @NotBlank(message = "Name of receiver is required")
    private String deliveryName;

    @NotBlank(message = "Street of receiver is required")
    private String deliveryStreet;

    @NotBlank(message = "Message of receiver is required")
    private String deliveryCity;

    @NotBlank(message = "State of receiver is required")
    private String deliveryState;

    @NotBlank(message = "Zip code of receiver is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String creditCardNumber;

    @Pattern(regexp = "^(0[1-9]|[0-2])/([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String creditCardExpirationDate;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String creditCardCvv;

    private List<Taco>tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
