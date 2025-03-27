package com.hedgerock.spirng.spring_in_action.reactor.rsocket.fire_and_forget.server;


import com.hedgerock.spirng.spring_in_action.reactor.rsocket.fire_and_forget.model.Alert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AlertController {

    @MessageMapping("alert")
    public Mono<Void> setAlert(Mono<Alert> alertMono) {

        return alertMono
                .doOnNext(alert -> {
                    log.info("{} alert ordered by {} at {}",
                            alert.getAlertLevel(),
                            alert.getOrderBy(),
                            alert.getOrderAt());
                })
                .thenEmpty(Mono.empty());

    }

}
