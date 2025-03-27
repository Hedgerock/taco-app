package com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class TacoOrder {

    @Id
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String ccCvv;
    private String ccNumber;
    private String ccExpirationDate;

    private Set<Long> tacoIds = new LinkedHashSet<>();

    @Transient
    private transient List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);

        if (taco.getId() != null) {
            this.tacoIds.add(taco.getId());
        }

    }

}
