package com.hedgerock.spirng.spring_in_action.reactor.rsocket.fire_and_forget.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Alert {

    private Level alertLevel;
    private String orderBy;
    private Instant orderAt;

    public enum Level {
        GREEN, ORANGE, RED, BLACK
    }
}
