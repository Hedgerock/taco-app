package com.hedgerock.spirng.spring_in_action.reactor.rsocket.channel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GratuityOut {

    private BigDecimal bigTotal;
    private int percent;
    private BigDecimal gratuity;

}
