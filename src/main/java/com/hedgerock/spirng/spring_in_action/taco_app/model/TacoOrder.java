package com.hedgerock.spirng.spring_in_action.taco_app.model;

import com.hedgerock.spirng.spring_in_action.taco_app.model.security.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="taco_order")
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name="created_at")
    private LocalDate createdAt = LocalDate.now();

    @NotBlank(message = "Name of receiver is required")
    @Column(name="delivery_name")
    private String deliveryName;

    @NotBlank(message = "Street of receiver is required")
    @Column(name="delivery_street")
    private String deliveryStreet;

    @NotBlank(message = "Message of receiver is required")
    @Column(name="delivery_city")
    private String deliveryCity;

    @NotBlank(message = "State of receiver is required")
    @Column(name="delivery_state")
    private String deliveryState;

    @NotBlank(message = "Zip code of receiver is required")
    @Column(name="delivery_zip")
    private String deliveryZip;

//    @CreditCardNumber(message = "Not a valid credit card number")
    @Column(name="credit_card_number")
    private String creditCardNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])/([2-9][0-9])$", message = "Must be formatted MM/YY")
    @Column(name="credit_card_expiration_date")
    private String creditCardExpirationDate;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column(name="credit_card_cvv")
    private String creditCardCvv;

    @ManyToMany(targetEntity = Taco.class,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Taco>tacos = new ArrayList<>();

    @Transient
    public void updateTacoOrder(TacoOrder patch) {
        this.deliveryName = patch.getDeliveryName() != null ? patch.getDeliveryName() : this.deliveryName;
        this.deliveryStreet = patch.getDeliveryStreet() != null ? patch.getDeliveryStreet() : this.deliveryStreet;
        this.deliveryCity = patch.getDeliveryCity() != null ? patch.getDeliveryCity() : this.deliveryCity;
        this.deliveryState = patch.getDeliveryState() != null ? patch.getDeliveryState() : this.deliveryState;
        this.deliveryZip = patch.getDeliveryZip() != null ? patch.getDeliveryZip() : this.deliveryZip;
        this.creditCardNumber = patch.getCreditCardNumber() != null ? patch.getCreditCardNumber() : this.creditCardNumber;
        this.creditCardExpirationDate = patch.getCreditCardExpirationDate() != null ? patch.getCreditCardExpirationDate() : this.creditCardExpirationDate;
        this.creditCardCvv = patch.getCreditCardCvv() != null ? patch.getCreditCardCvv() : this.creditCardCvv;
    }

    @Transient
    public void insertAuthorizedUserDetails(User user) {
        this.deliveryName = user.getFullName();
        this.deliveryStreet = user.getStreet();
        this.deliveryCity = user.getCity();
        this.deliveryState = user.getState();
        this.deliveryZip = user.getZip();
    }

    public void addTaco(Taco designedTaco) {
        this.tacos.add(designedTaco);
    }

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }
}
